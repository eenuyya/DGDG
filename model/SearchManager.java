// SearchManager.java
package model;

import util.DBUtil;

import java.sql.*;
import java.util.Scanner;

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
            String sql = "SELECT rest_id, rest_name, category, open_time, close_time, distance, has_vegan " +
                         "FROM Restaurant WHERE rest_name LIKE ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            rs = pstmt.executeQuery();

            System.out.println("===== 검색 결과 =====");
            boolean found = false;

            while (rs.next()) {
                String name = rs.getString("rest_name");
                String category = rs.getString("category");
                Time openTime = rs.getTime("open_time");
                Time closeTime = rs.getTime("close_time");
                int distance = rs.getInt("distance");
                boolean hasVegan = rs.getBoolean("has_vegan");

                System.out.printf("이름: %s | 카테고리: %s | 영업시간: %s~%s | 거리: %dm | 비간 옵션: %s%n",
                        name, category, openTime, closeTime, distance, hasVegan ? "있음" : "없음");

                System.out.printf("\"%s\"을(를) 즐겨찾기에 추가하려면 fav, 별점을 매기려면 star을 입력해주세요 (\uc5d4터로 건너따른): ", name);
                String action = scanner.nextLine().trim().toLowerCase();

                switch (action) {
                    case "fav":
                        FavoriteManager.addFavorite(userId, rs.getInt("rest_id"));
                        System.out.println("즐겨찾기에 추가되었습니다.");
                        break;
                    case "star":
                        System.out.print("1~5 사이의 별점을 입력해주세요: ");
                        try {
                            int rating = Integer.parseInt(scanner.nextLine());
                            if (rating < 1 || rating > 5) {
                                System.out.println("별점은 1에서 5 사이의 숫자여야 합니다.");
                                break;
                            }
                            StarManager.addRating(userId, rs.getInt("rest_id"), rating);
                        } catch (NumberFormatException e) {
                            System.out.println("숫자 형식이 아니다. 별점은 정수로 입력해야 합니다.");
                        }
                        break;
                    default:
                        break;
                }
                found = true;
            }
            if (!found) {
                System.out.println("일치하는 식당이 없습니다.");
            }

        } catch (SQLException e) {
            System.out.println("검색 중 오류 발생: " + e.getMessage());
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
}
