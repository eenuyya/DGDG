import java.sql.Connection;
import java.sql.SQLException;
import util.DBUtil;

public class ConnectionTest {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil.getConnection();
            if (conn != null) {
                System.out.println("✅ 데이터베이스 연결 성공!");
                conn.close(); // 연결 닫기
            }
        } catch (SQLException e) {
            System.out.println("❌ 연결 실패: " + e.getMessage());
        }
    }
}
