package model;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.DBUtil;
import util.ConsoleStyle;

public class SearchManager {

    private final Scanner scanner;

    public SearchManager() {
        this.scanner = new Scanner(System.in);
    }

    public void searchByName(int userId, String keyword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT r.rest_id, r.rest_name, r.category, r.open_time, r.close_time, " +
                         "r.break_start, r.break_end, r.distance, r.has_vegan, " +
                         "ROUND(AVG(s.rating), 2) AS avg_rating " +
                         "FROM Restaurant r " +
                         "LEFT JOIN Star s ON r.rest_id = s.rest_id " +
                         "WHERE r.rest_name = ? " +
                         "GROUP BY r.rest_id";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, keyword);
            rs = pstmt.executeQuery();

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            if (rs.next()) {
                int restId = rs.getInt("rest_id");
                String name = rs.getString("rest_name");
                String category = rs.getString("category");
                Time openTime = rs.getTime("open_time");
                Time closeTime = rs.getTime("close_time");
                Time breakStart = rs.getTime("break_start");
                Time breakEnd = rs.getTime("break_end");
                int distance = rs.getInt("distance");
                boolean hasVegan = rs.getBoolean("has_vegan");
                double avgRating = rs.getDouble("avg_rating");

                // 현재 영업 여부 계산
                Time now = new Time(System.currentTimeMillis());
                boolean isOpen = now.after(openTime) && now.before(closeTime)
                        && !(now.after(breakStart) && now.before(breakEnd));

                System.out.println("----------------------------------------------------------");
                String coloredCategory = ConsoleStyle.apply(ConsoleStyle.getCategoryColor(category), category);
                String displayName = String.format("%s %s   ⭐ %.2f", name, coloredCategory, avgRating);
                System.out.println(displayName);

                System.out.println("     영업시간 : " + timeFormat.format(openTime) + " ~ " + timeFormat.format(closeTime));
                if (breakStart != null && breakEnd != null) {
                    System.out.println("     브레이크타임: " + timeFormat.format(breakStart) + " ~ " + timeFormat.format(breakEnd));
                }
                System.out.println("     현재 " + (isOpen ? "영업중" : "브레이크/마감"));
                System.out.println("     정문에서 " + distance + "m");
                if (hasVegan) System.out.println("     비건 옵션 \u001B[32m있음\u001B[0m");
                else System.out.println("     비건 옵션 없음");


                // 메뉴 출력
                List<String> menuList = new ArrayList<>();
                try (PreparedStatement menuStmt = conn.prepareStatement(
                        "SELECT menu_name, is_vegan FROM Menu WHERE rest_id = ?")) {
                    menuStmt.setInt(1, restId);
                    ResultSet menuRs = menuStmt.executeQuery();
                    while (menuRs.next()) {
                        String menuName = menuRs.getString("menu_name");
                        boolean isVegan = menuRs.getBoolean("is_vegan");
                        if (isVegan) menuName += "🌱";
                        menuList.add(menuName);
                    }
                }
                System.out.println("     메뉴: " + String.join(", ", menuList));
                System.out.println();


                System.out.println("----------------------------------------------------------");
                System.out.println("[1] 별점 등록하기");
                System.out.println("[2] 즐겨찾기로 등록하기");
                System.out.println("[3] 홈화면으로 돌아가기");
                System.out.print("번호를 선택해주세요: ");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        System.out.print("1~5 사이의 별점을 입력해주세요: ");
                        try {
                            int rating = Integer.parseInt(scanner.nextLine());
                            if (rating < 1 || rating > 5) {
                                System.out.println("별점은 1에서 5 사이의 숫자여야 합니다.");
                                break;
                            }
                            StarManager.addRating(userId, restId, rating);
                            System.out.println("별점이 등록되었습니다.");
                        } catch (NumberFormatException e) {
                            System.out.println("숫자 형식이 아닙니다. 별점은 정수로 입력해야 합니다.");
                        }
                        break;
                    case "2":
                        FavoriteManager.addFavorite(userId, restId);
                        System.out.println("즐겨찾기에 추가되었습니다.");
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println(" _____________________________\n"
                		+ "/                             \\\n"
                		+ "|  일치하는 식당이 없습니다.  |\n"
                		+ "\\                             /\n"
                		+ " -----------------------------\n"
                		+ "    \\   ^__^\n"
                		+ "     \\  (oo)\\_______\n"
                		+ "        (__)\\       )\\/\\\n"
                		+ "            ||----w |\n"
                		+ "            ||     ||");
                System.out.println();
                try {
                    Thread.sleep(1500);  // 1초(1000 밀리초) 일시 정지
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // 인터럽트 상태 복원
                }
            }

        } catch (SQLException e) {
            System.out.println("검색 중 오류 발생: " + e.getMessage());
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
}
