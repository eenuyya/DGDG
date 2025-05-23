package model;

import util.DBUtil;
import util.ConsoleStyle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class MyMenuManager {
	
	
	public static void menuHandler(int userId) {
		Scanner sc = new Scanner(System.in);
		
		String sql = """
    		    SELECT
    		        u.user_name, ug.group_name, u.is_available,
    		        AVG(s.rating) AS avg_rating
    		    FROM User u
    		    LEFT JOIN User_group ug ON u.group_id = ug.group_id
    		    LEFT JOIN Star s ON u.user_id = s.user_id
    		    WHERE u.user_id = ?
    		    GROUP BY u.user_id, u.user_name, ug.group_name, u.is_available
    		""";

    	
    	String username = "";
        String groupName = "";
        boolean isAvailable = false;
        double avgRating = 0.0;
        while (true) {
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pmtst = conn.prepareStatement(sql)
            ) {
        	pmtst.setInt(1, userId);
        	
                try (ResultSet rs = pmtst.executeQuery()) {
                    if (rs.next()) {
                        username        = rs.getString("user_name");
                        groupName   = rs.getString("group_name");
                        isAvailable = rs.getBoolean("is_available");
                        avgRating   = rs.getDouble("avg_rating");
                    } else {
                        System.out.println("사용자 정보를 찾을 수 없습니다.");
                        return;
                    }
                }
            } catch (SQLException e) {
                System.err.println("사용자 정보 조회 중 오류:");
                e.printStackTrace();
                return;
            }
        String availabilityText = isAvailable
        		? ConsoleStyle.apply(ConsoleStyle.EWHA_GREEN, "가능")
        				: ConsoleStyle.apply(ConsoleStyle.DARK_RED, "불가능");
        groupName = (groupName == null) ? " 없음" : ": "+groupName;
        	System.out.println();
        	System.out.println();
        	System.out.println();
	        System.out.printf("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
	    			+ "┃  %-25s  ┃\n"
	    			+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n", username+"님의 MENU");
	        System.out.println();
	        System.out.println(" 👤 닉네임: "+username);
	        System.out.println(" 👥 속해있는 그룹"+groupName);
	        System.out.println(" 🍚 현재 밥 "+ availabilityText);
	        System.out.println(" ⭐️ 내가 준 별점 평균: "+avgRating);
	        System.out.println();
		    System.out.println("┌─────────────────────────────────────────────────────────────────────┐");
		    System.out.printf ("│ %-67s │\n", "");
		    System.out.printf ("│ %-58s │\n", "[1] 즐겨찾기한 식당 보기");
		    System.out.printf ("│ %-58s │\n", "[2] 밥 가능 여부 수정하기");
		    System.out.printf ("│ %-58s │\n", "[3] 홈화면으로 돌아가기");
		    System.out.printf ("│ %-67s │\n", "");
		    System.out.println("└─────────────────────────────────────────────────────────────────────┘");
		    System.out.print("> ");
	        int menuChoice=sc.nextInt();
	        sc.nextLine();
	        
	       if (menuChoice == 3) {
	           System.out.println("홈화면으로 돌아갑니다.");
	           return;
	       }
	
	       switch (menuChoice) {
	           case 1 -> showFavorites(userId, username);
	           case 2 -> changeAvailibility(userId);
	           default -> System.out.println("1~3 중에서 선택해주세요.");
	       }
	       System.out.println();
       }
		
		} // MenuHandler
		
		//1을 눌렀을 때 즐겨찾기한 식당 보여주기
		private static void showFavorites(int userId, String username) {
		//즐겨찾기한 식당 보여주기
			System.out.println();
			System.out.println(username+"님의 즐겨찾기 목록");
			String sql = """
		                SELECT r.rest_name
		                  FROM Favorites f
		                  JOIN Restaurant r ON f.rest_id = r.rest_id
		                 WHERE f.user_id = ?
		                """;
			 try (Connection conn = DBUtil.getConnection();
					 PreparedStatement favpstmt = conn.prepareStatement(sql)
		     ){
				 favpstmt.setInt(1, userId);
				 try (ResultSet rs = favpstmt.executeQuery()) {
	                    boolean found = false;
                    	int num=1;
	                    while (rs.next()) {
	                        found = true;
	                        String name = rs.getString("rest_name");
	                        System.out.printf(num+". "+ name+"\n");
	                        num++;
	                    }
	                    if (!found) {
	                    	System.out.println(" ____________________________________\n"
	                                + "/                                    \\\n"
	                                + "|    즐겨찾기한 식당이 없습니다.       |\n"
	                                + "\\                                    /\n"
	                                + " ------------------------------------\n"
	                                + "    \\   ^__^\n"
	                                + "     \\  (oo)\\_______\n"
	                                + "        (__)\\       )\\/\\\n"
	                                + "            ||----w |\n"
	                                + "            ||     ||");
	                        System.out.println();
	                    }
	                }
				 
				 
			 }catch (SQLException e) {
	                System.err.println("즐겨찾기 조회 중 오류 발생:");
	                e.printStackTrace();
	            }

		}
		
		// 2를 눌렀을 때 밥가능 여부 수정할 수 있게 하기
		private static void changeAvailibility(int userId) { //밥가능 여부 수정하기
			 boolean currentStatus = false;
			 String selectSql = "SELECT is_available FROM `User` WHERE user_id = ?";
			 
			 try (Connection conn = DBUtil.getConnection();
					 PreparedStatement onoffpstmt = conn.prepareStatement(selectSql)
			 ) {
				 onoffpstmt.setInt(1, userId);
				 try (ResultSet rs = onoffpstmt.executeQuery()) {
					 if (rs.next()) {
						 currentStatus = rs.getBoolean("is_available");
						 } 
					 else {
						 System.out.println("사용자 정보를 찾을 수 없습니다.");
						 return;
						 }
					 }
				 } catch (SQLException e) {
					 System.err.println("현재 상태 조회 중 오류 발생:");
					 e.printStackTrace();
					 return;
					 }
			
			 String currentText = currentStatus
		        		? ConsoleStyle.apply(ConsoleStyle.EWHA_GREEN, "가능")
		        				: ConsoleStyle.apply(ConsoleStyle.DARK_RED, "불가능");
			 
			 Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out.println("현재 밥 "+currentText+" 상태입니다. 바꾸시겠습니까? (y/n)");
			System.out.print("> ");
			String onoffChoice = sc.nextLine().trim().toUpperCase();
			
			// Y를 눌렀을 때(밥가능 여부를 바꾸겠다는 의미)
			if(onoffChoice.equals("Y")) {
				boolean newStatus= !currentStatus;
				String updateSql="UPDATE `User` SET is_available = ? WHERE user_id = ?";
				
				try(Connection conn = DBUtil.getConnection();
						PreparedStatement reversepmtst = conn.prepareStatement(updateSql)
					){
					reversepmtst.setBoolean(1, newStatus);
					reversepmtst.setInt(2, userId);
					reversepmtst.executeUpdate();
                    
					String newText = newStatus
			        		? ConsoleStyle.apply(ConsoleStyle.EWHA_GREEN, "가능")
			        				: ConsoleStyle.apply(ConsoleStyle.DARK_RED, "불가능");
					System.out.println();
					System.out.println(currentText+"에서 "+newText+"으로 변경되었습니다.");
				}
				catch (SQLException e) {
					 System.err.println("상태 변경 중 오류 발생:");
					 e.printStackTrace();
					 return;
					 }
			}
			//N을 눌렀을 때(밥가능 여부를 바꾸지 않겠다는 의미)
			else if(onoffChoice.equals("N")) {
				System.out.println("밥가능 여부를 변경하지 않고 유지합니다!");
			}
			

	}
}	
