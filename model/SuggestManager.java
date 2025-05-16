package model;

import util.DBUtil;

import java.sql.*;
import java.util.Scanner;

public class SuggestManager {

    public static void suggestSelect(int userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("식당 추천을 원하시면 'rest', 메뉴 추천을 원하시면 'menu'를 입력하세요: ");
        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("rest")) {
            suggestRestaurant(scanner);
        } else if (choice.equalsIgnoreCase("menu")) {
            suggestMenu(scanner);
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }

    private static void suggestRestaurant(Scanner scanner) {

        System.out.print("카테고리 (Korean, Japanese 등, 건너뛰려면 Enter): ");
        String category = scanner.nextLine().trim();

        System.out.print("비건 옵션 필요? (true/false, 건너뛰려면 Enter): ");
        String veganInput = scanner.nextLine().trim();

        System.out.print("현재 영업중인 식당만 찾기 (true/false, 건너뛰려면 Enter): ");
        String openInput = scanner.nextLine().trim();
        
        System.out.print("최대 거리 (km, 건너뛰려면 Enter): ");
        String distanceInput = scanner.nextLine().trim();

        System.out.print("최소 별점 (1~5, 건너뛰려면 Enter): ");
        String ratingInput = scanner.nextLine().trim();

        String sql = """
                SELECT r.rest_name, r.category, r.distance, r.has_vegan, AVG(s.rating) AS avg_rating
        		FROM Restaurant r
        		LEFT JOIN Star s ON r.rest_id = s.rest_id
                WHERE 1=1
                """;

        if (!category.isEmpty()) sql += " AND r.category = ?";									// ?
        if (!veganInput.isEmpty()) sql += " AND r.has_vegan = ?";								// ?
        if (!distanceInput.isEmpty()) sql += " AND r.distance <= ?";							// ?
        if (!openInput.isEmpty()) sql += " AND TIME(NOW()) BETWEEN r.open_time AND r.close_time"
        		+ " AND TIME(NOW()) NOT BETWEEN r.break_start AND r.break_end";
        if (!ratingInput.isEmpty()) sql += " GROUP BY r.rest_id HAVING AVG(s.rating) >= ?";		// ?
        else sql += " GROUP BY r.rest_id";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (!category.isEmpty()) pstmt.setString(1, category);
            if (!veganInput.isEmpty()) pstmt.setBoolean(2, Boolean.parseBoolean(veganInput));
            if (!distanceInput.isEmpty()) pstmt.setDouble(3, Double.parseDouble(distanceInput));
            if (!ratingInput.isEmpty()) pstmt.setInt(4, Integer.parseInt(ratingInput));

            ResultSet rs = pstmt.executeQuery();
            boolean hasResult = false;
            while (rs.next()) {
            	hasResult = true;
                System.out.printf("이름: %s | 카테고리: %s | 거리: %.1fkm | 비건: %s | 평균 별점: %.1f\n",
                        rs.getString("rest_name"), rs.getString("category"),
                        rs.getDouble("distance"), rs.getBoolean("has_vegan"), rs.getDouble("avg_rating"));
            }
            if (!hasResult) {
            	System.out.println("해당하는 조건의 식당이 없습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void suggestMenu(Scanner scanner) {
        System.out.print("메뉴 이름 키워드 (건너뛰려면 Enter): ");
        String menuName = scanner.nextLine().trim();

        System.out.print("카테고리 (Korean, Japanese 등, 건너뛰려면 Enter): ");
        String category = scanner.nextLine().trim();

        System.out.print("비건 여부 (true/false, 건너뛰려면 Enter): ");
        String veganInput = scanner.nextLine().trim();

        System.out.print("최대 가격 (건너뛰려면 Enter): ");
        String priceInput = scanner.nextLine().trim();

        System.out.print("국물 여부 (true/false, 건너뛰려면 Enter): ");
        String soupInput = scanner.nextLine().trim();

        System.out.print("최대 맵기 정도 (1~5, 건너뛰려면 Enter): ");
        String spicyInput = scanner.nextLine().trim();

        String sql = """
                SELECT m.menu_name, m.price, r.rest_name
                FROM Menu m
                JOIN Restaurant r ON m.rest_id = r.rest_id
                WHERE 1=1
                """;

        if (!menuName.isEmpty()) sql += " AND m.menu_name LIKE ?";
        if (!category.isEmpty()) sql += " AND m.category = ?";
        if (!veganInput.isEmpty()) sql += " AND m.is_vegan = ?";
        if (!priceInput.isEmpty()) sql += " AND m.price <= ?";
        if (!soupInput.isEmpty()) sql += " AND m.is_soup = ?";
        if (!spicyInput.isEmpty()) sql += " AND m.spicy <= ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idx = 1;
            if (!menuName.isEmpty()) pstmt.setString(idx++, "%" + menuName + "%");
            if (!category.isEmpty()) pstmt.setString(idx++, category);
            if (!veganInput.isEmpty()) pstmt.setBoolean(idx++, Boolean.parseBoolean(veganInput));
            if (!priceInput.isEmpty()) pstmt.setInt(idx++, Integer.parseInt(priceInput));
            if (!soupInput.isEmpty()) pstmt.setBoolean(idx++, Boolean.parseBoolean(soupInput));
            if (!spicyInput.isEmpty()) pstmt.setInt(idx++, Integer.parseInt(spicyInput));

            ResultSet rs = pstmt.executeQuery();
            boolean hasResult = false;
            while (rs.next()) {
            	hasResult = true;
                System.out.printf("메뉴: %s | 가격: %d원 | 식당: %s\n",
                        rs.getString("menu_name"), rs.getInt("price"), rs.getString("rest_name"));
            }
            if (!hasResult) {
            	System.out.println("해당하는 조건의 메뉴가 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
