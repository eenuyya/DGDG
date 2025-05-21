package model;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FavoriteManager {

    // 즐겨찾기 추가
    public static void addFavorite(int userId, int restId) {
        String sql = "INSERT INTO Favorites (user_id, rest_id) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, restId);
            pstmt.executeUpdate();
            System.out.println("✅ 즐겨찾기에 추가되었습니다.");

        } catch (SQLException e) {
            // 중복된 즐겨찾기를 추가하려는 경우 예외 처리
            if (e.getErrorCode() == 1062) { // MySQL의 duplicate entry 오류 코드
                System.out.println("이미 즐겨찾기에 추가된 식당입니다.");
            } else {
                e.printStackTrace();
            }
        }
    }
}
