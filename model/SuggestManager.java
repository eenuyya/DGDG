package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.DBUtil;
import util.ConsoleStyle;

public class SuggestManager {

    public static void suggestSelect(int userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("선택: ");
        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("1")) {
            suggestRestaurant(scanner);
        } else if (choice.equalsIgnoreCase("2")) {
            suggestMenu(scanner);
        } else {
        	System.out.println(" _____________________________\n"
            		+ "/                             \\\n"
            		+ "|      잘못된 입력입니다.     |\n"
            		+ "\\                             /\n"
            		+ " -----------------------------\n"
            		+ "    \\   ^__^\n"
            		+ "     \\  (oo)\\_______\n"
            		+ "        (__)\\       )\\/\\\n"
            		+ "            ||----w |\n"
            		+ "            ||     ||");
            System.out.println();
        }
    }

    private static void suggestRestaurant(Scanner scanner) {
    	System.out.printf("┏━━━━━━━━━━━━━━━━━━┓\n"
    			+ "┃  %-10s  ┃\n"
    			+ "┗━━━━━━━━━━━━━━━━━━┛", "식당 추천");
    	printTopTen();
    	System.out.println();
    	System.out.println();
    	System.out.println("원하는 식당의 조건을 선택해주세요.\n");
        System.out.print("한식, 일식, 중식, 디저트, 기타 중 골라주세요: (건너뛰려면 Enter): ");
        String category = scanner.nextLine().trim();

        System.out.print("비건 메뉴를 파는 식당만 찾으시나요? (y/n, 건너뛰려면 Enter): ");
        String veganInput = scanner.nextLine().trim();

        System.out.print("현재 영업중인 식당만 찾기 (y/n, 건너뛰려면 Enter): ");
        String openInput = scanner.nextLine().trim();

        System.out.print("정문에서 몇 m 이내의 식당을 찾으시나요? (건너뛰려면 Enter): ");
        String distanceInput = scanner.nextLine().trim();

        System.out.print("최소 별점 (1~5, 건너뛰려면 Enter): ");
        String ratingInput = scanner.nextLine().trim();

        String sql = """
                SELECT r.rest_id, r.rest_name, r.category, r.open_time, r.close_time,
                       r.break_start, r.break_end, r.distance, r.has_vegan,
                       AVG(s.rating) AS avg_rating
                FROM Restaurant r
                LEFT JOIN Star s ON r.rest_id = s.rest_id
                WHERE 1=1
                """;

        if (!category.isEmpty()) sql += " AND r.category = ?";
        if (veganInput.equalsIgnoreCase("y")) sql += " AND r.has_vegan = ?";
        if (openInput.equalsIgnoreCase("y")) sql += " AND TIME(NOW()) BETWEEN r.open_time AND r.close_time "
                + "AND TIME(NOW()) NOT BETWEEN r.break_start AND r.break_end";
        if (!distanceInput.isEmpty()) sql += " AND r.distance <= ?";
        if (!ratingInput.isEmpty()) sql += " GROUP BY r.rest_id HAVING AVG(s.rating) >= ?";
        else sql += " GROUP BY r.rest_id";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idx = 1;
            if (!category.isEmpty()) pstmt.setString(idx++, category);
            if (veganInput.equalsIgnoreCase("y")) pstmt.setBoolean(idx++, true);
            if (!distanceInput.isEmpty()) pstmt.setInt(idx++, Integer.parseInt(distanceInput));
            if (!ratingInput.isEmpty()) pstmt.setInt(idx++, Integer.parseInt(ratingInput));

            ResultSet rs = pstmt.executeQuery();
            boolean hasResult = false;
            int count = 1;


            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            if (rs.next()) {
                hasResult = true;
                System.out.println("\n선택한 조건의 식당은 다음과 같습니다.\n");

                do {
                    int restId = rs.getInt("rest_id");
                    String name = rs.getString("rest_name");
                    String category_ = rs.getString("category");
                    Time openTime = rs.getTime("open_time");
                    Time closeTime = rs.getTime("close_time");
                    Time breakStart = rs.getTime("break_start");
                    Time breakEnd = rs.getTime("break_end");
                    int distance = rs.getInt("distance");
                    boolean hasVegan = rs.getBoolean("has_vegan");
                    double avgRating = rs.getDouble("avg_rating");

                    boolean isOpen = false;
                    Time now = new Time(System.currentTimeMillis());
                    if (now.after(openTime) && now.before(closeTime)
                            && (breakStart == null || breakEnd == null
                            || now.before(breakStart) || now.after(breakEnd))) {
                        isOpen = true;
                    }

                    String coloredCategory = ConsoleStyle.apply(ConsoleStyle.getCategoryColor(category_), category_);
                    String displayName = String.format(" %d. %s %s   ⭐ %.2f", count++, name, coloredCategory, avgRating);
                    System.out.println(displayName);
                    System.out.println("     영업시간 : " + timeFormat.format(openTime) + " ~ " + timeFormat.format(closeTime));
                    if (breakStart != null && breakEnd != null) {
                        System.out.println("     브레이크타임: " + timeFormat.format(breakStart) + " ~ " + timeFormat.format(breakEnd));
                    }
                    System.out.println("     현재 " + (isOpen ? "영업중" : "브레이크/마감"));
                    System.out.println("     정문에서 " + distance + "m");
                    System.out.println("     비건 옵션 " + (hasVegan ? "\u001B[32m있음\u001B[0m" : "없음"));

                    // 메뉴 목록 출력
                    List<String> menuList = new ArrayList<>();
                    try (PreparedStatement menuStmt = conn.prepareStatement(
                            "SELECT menu_name, is_vegan FROM Menu WHERE rest_id = ?")) {
                        menuStmt.setInt(1, restId);
                        ResultSet menuRs = menuStmt.executeQuery();
                        while (menuRs.next()) {
                            String menuName = menuRs.getString("menu_name");
                            if (menuRs.getBoolean("is_vegan")) menuName += "🌱";
                            menuList.add(menuName);
                        }
                    }

                    System.out.println("     메뉴: " + String.join(", ", menuList));
                    System.out.println();

                } while (rs.next());

                // ✅ 전체 출력 후 5초 대기
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return;
            }

            // ❌ 결과 없을 경우
            if (!hasResult) {
                System.out.println(" ____________________________________\n"
                        + "/                                    \\\n"
                        + "|  해당 조건에 맞는 식당이 없습니다.  |\n"
                        + "\\                                    /\n"
                        + " ------------------------------------\n"
                        + "    \\   ^__^\n"
                        + "     \\  (oo)\\_______\n"
                        + "        (__)\\       )\\/\\\n"
                        + "            ||----w |\n"
                        + "            ||     ||");
                System.out.println();

                try {
                    Thread.sleep(1500);  // 1.5초 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void suggestMenu(Scanner scanner) {
    	System.out.printf("┏━━━━━━━━━━━━━━━━━━┓\n"
    			+ "┃  %-10s  ┃\n"
    			+ "┗━━━━━━━━━━━━━━━━━━┛", "메뉴 추천");
    	printMenuRollupStats();
    	System.out.println();
    	System.out.println();
        System.out.print("메뉴 이름 키워드 (건너뛰려면 Enter): ");
        String menuName = scanner.nextLine().trim();

        System.out.print("한식, 일식, 중식, 디저트, 기타 중 골라주세요 (건너뛰려면 Enter): ");
        String category = scanner.nextLine().trim();

        System.out.print("최대 가격 (건너뛰려면 Enter): ");
        String priceInput = scanner.nextLine().trim();

        System.out.print("비건 메뉴만 찾기 (y/n, 건너뛰려면 Enter): ");
        String veganInput = scanner.nextLine().trim();

        System.out.print("국물 있는 음식만 찾기 (y/n, 건너뛰려면 Enter): ");
        String soupInput = scanner.nextLine().trim();

        System.out.print("최대 맵기 정도 (0~5, 건너뛰려면 Enter): ");
        String spicyInput = scanner.nextLine().trim();

        String sql = """
                SELECT m.menu_name, m.price, m.is_vegan, r.rest_name
                FROM Menu m
                JOIN Restaurant r ON m.rest_id = r.rest_id
                WHERE 1=1
                """;

        if (!menuName.isEmpty()) sql += " AND m.menu_name LIKE ?";
        if (!category.isEmpty()) sql += " AND m.category = ?";
        if (!priceInput.isEmpty()) sql += " AND m.price <= ?";
        if (veganInput.equalsIgnoreCase("y")) sql += " AND m.is_vegan = ?";
        if (soupInput.equalsIgnoreCase("y")) sql += " AND m.is_soup = ?";
        if (!spicyInput.isEmpty()) sql += " AND m.spicy <= ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idx = 1;
            if (!menuName.isEmpty()) pstmt.setString(idx++, "%" + menuName + "%");
            if (!category.isEmpty()) pstmt.setString(idx++, category);
            if (!priceInput.isEmpty()) pstmt.setInt(idx++, Integer.parseInt(priceInput));
            if (veganInput.equalsIgnoreCase("y")) pstmt.setBoolean(idx++, true);
            if (soupInput.equalsIgnoreCase("y")) pstmt.setBoolean(idx++, true);
            if (!spicyInput.isEmpty()) pstmt.setInt(idx++, Integer.parseInt(spicyInput));

            ResultSet rs = pstmt.executeQuery();
            boolean hasResult = false;
            int count = 1;

            if (rs.next()) {
                hasResult = true;
                System.out.println("\n선택 조건의 메뉴는 다음과 같습니다.\n");

                do {
                    String menu = rs.getString("menu_name");
                    if (rs.getBoolean("is_vegan")) menu += "🌱";
                    int price = rs.getInt("price");
                    String rest = rs.getString("rest_name");
                    System.out.printf(" %d. %s - %d원 (%s)\n", count++, menu, price, rest);
                } while (rs.next());

                try {
                    Thread.sleep(5000);  // 결과 출력 후 5초 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return;  // 홈화면 복귀
            }

            if (!hasResult) {
                System.out.println(" ____________________________________\n"
                        + "/                                    \\\n"
                        + "|  해당 조건에 맞는 메뉴가 없습니다. |\n"
                        + "\\                                    /\n"
                        + " ------------------------------------\n"
                        + "    \\   ^__^\n"
                        + "     \\  (oo)\\_______\n"
                        + "        (__)\\       )\\/\\\n"
                        + "            ||----w |\n"
                        + "            ||     ||");
                System.out.println();
                try {
                    Thread.sleep(1500);  // 1.5초 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printTopTen() {
        System.out.println();
        System.out.println(" ______________________________________________");
        System.out.println("/\\                                             \\");
        System.out.println("\\_|          별점 높은 식당 Top 10             |");

        String sql = """
            SELECT *
			FROM (
			    SELECT 
			        r.rest_name,
			        r.category,
			        ROUND(AVG(s.rating), 2) AS avg_rating,
			        RANK() OVER (
			            ORDER BY AVG(s.rating) DESC, r.rest_id ASC
			        ) AS rnk
			    FROM Restaurant r
			    JOIN Star s ON r.rest_id = s.rest_id
			    GROUP BY r.rest_id, r.rest_name, r.category
			) AS ranked
			WHERE rnk <= 10
			ORDER BY rnk;

            """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int rank = rs.getInt("rnk");
                String name = rs.getString("rest_name");
                String category = rs.getString("category");
                double rating = rs.getDouble("avg_rating");

                String coloredCategory = ConsoleStyle.apply(ConsoleStyle.getCategoryColor(category), category);
                String displayName = name + " " + coloredCategory;
                String alignedDisplay = ConsoleStyle.padRightVisualWidth(displayName, 28);

                System.out.printf("  |  %2d. %s ⭐%5.2f  |\n", rank, alignedDisplay, rating);
            }

            System.out.println("  |                                            |");
            System.out.println("  |                                            |");
            System.out.println("  |   _________________________________________|_");
            System.out.println("   \\_/__________________________________________/");

        } catch (SQLException e) {
            System.out.println("추천 식당 조회 오류: " + e.getMessage());
        }
    }
    
    private static void printMenuRollupStats() {
        String sql = """
            SELECT
                category,
                SUM(CASE WHEN is_vegan = TRUE THEN 1 ELSE 0 END) AS vegan_count,
                COUNT(*) AS total_count
            FROM Menu
            GROUP BY category WITH ROLLUP
            """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println();
            System.out.println(" ______________________________________________");
            System.out.println("/\\                                             \\");
            System.out.println("\\_|        다음과 같은 데이터를 바탕으로       |");
            System.out.println("  |                                            |");

            while (rs.next()) {
                String category = rs.getString("category");
                int veganCount = rs.getInt("vegan_count");
                int totalCount = rs.getInt("total_count");

                if (category == null) {
                    String line = String.format("총 %d개 (비건 %d개)", totalCount, veganCount);
                    String padded = ConsoleStyle.padRightVisualWidth(line, 42);
                    System.out.printf("  |  %s|\n", padded);
                } else {
                    String coloredCategory = ConsoleStyle.apply(ConsoleStyle.getCategoryColor(category), category);
                    String left = String.format("%s %d개 (비건 %d개)", coloredCategory, totalCount, veganCount);
                    String padded = ConsoleStyle.padRightVisualWidth(left, 42);
                    System.out.printf("  |  %s|\n", padded);
                }
            }

            System.out.println("  |                                            |");
            System.out.println("  |       딱 맞는 메뉴를 찾아 드립니다...      |");
            System.out.println("  |   _________________________________________|_");
            System.out.println("   \\_/__________________________________________/");
        } catch (SQLException e) {
            System.out.println("메뉴 통계 조회 오류: " + e.getMessage());
        }
    }


    

    

}