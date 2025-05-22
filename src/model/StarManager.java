package model;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StarManager {

    public static void addRating(int userId, int restId, int rating) {
        String sql = "INSERT INTO Star (user_id, rest_id, rating) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE rating = VALUES(rating)";		// 이미 해당 user_id, rest_id의 튜플이 있으면 별점값 업데이트
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, restId);
            pstmt.setInt(3, rating);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("별점 저장에 실패했습니다.");
            }

        } catch (SQLException e) {
            System.out.println("별점 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
