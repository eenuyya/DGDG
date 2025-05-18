package service;

import java.sql.*;
import java.util.Scanner;
import java.util.Random;
import db.DBConnector;


public class GroupService {

	public static void main(String[] args) {
		// 임시 userId 
		int userId = 1;
		
		System.out.println("밥친구를 찾아 즐거운 식사시간이 되시길 바래요! ");
		System.out.println("어떤 메뉴를 실행할까요? ");
		System.out.println("1. 그룹 만들기 ");
		System.out.println("2. 그룹 참여하기 ");
		System.out.println("3. 그룹 삭제하기 ");
		
		Scanner sc = new Scanner (System.in);
		int menu = sc.nextInt();
		
		switch(menu) {
		case 1 :{
			System.out.println("그룹 만들기 탭으로 이동합니다. ");
			createGroup(userId);
			break;
		}
		case 2 :{
			System.out.println("그룹 참여하기 탭으로 이동합니다. ");
			joinGroup(userId);
			break;
		}
		case 3 : {
			System.out.println("그룹 삭제하기 탭으로 이동합니다. ");
			break;
		}
			
		}
	}


	// 그룹에 참여하기 
	private static void joinGroup (int userId) {
		try (Connection conn = DBConnector.getConnection()){
			
			// 1. 해당 유저가 이미 그룹에 속해있는지부터 확인한다. 
			String checkSql = "SELECT group_id FROM user WHERE user_id = ?"; // 현재 사용자의 group_id를 가져오는 sql 쿼리 작성 
			PreparedStatement checkStmt = conn.prepareStatement(checkSql); // 쿼리를 실행할 PreparedStatement 객체 생성 
			checkStmt.setInt(1,  userId); // 해당쿼리의 ?에 userId 값을 넣음 
			ResultSet checkRs = checkStmt.executeQuery(); // 쿼리 실행 -> 결과를 rs로 받아옴 
			
			if(checkRs.next() && checkRs.getObject("group_id") != null) {
				System.out.println("이미 다른 그룹에 속해있습니다. 그룹에 들어갈 수 없습니다. ");
				return;
			}
			
			// 2. 사용자에게 그룹 이름 입력 받기 
			Scanner sc = new Scanner (System.in);
			String groupName = null;
			int groupId = -1;
			
			while(true) { // 그룹 이름이 유효할 때까지 반복한다. 
				
				System.out.println("들어가려 하는 그룹 이름을 입력하세요 : ");
				groupName = sc.nextLine ();
				
				String tempSql = "SELECT group_id FROM User_group WHERE group_name = ?";
				PreparedStatement pstmt = conn.prepareStatement(tempSql);
				pstmt.setString(1, groupName);
				ResultSet tempRs = pstmt.executeQuery();
				
				if(tempRs.next()) {
					// 그룹 이름이 유효하면 group_id 가져오고 반복을 탈출한다.
					groupId = tempRs.getInt("group_id");
					break;
				}
				else {
					System.out.println("해당 이름의 그룹이 존재하지 않습니다. 다시 입력해주세요.");
				}
			
			}
			
			// 3. 초대코드 입력받고, 올바른 초대코드인지 확인한다. 
			String inviteSql = "SELECT invite_code FROM User_group WHERE group_id = ?";
			PreparedStatement inviteStmt = conn.prepareStatement(inviteSql);
			inviteStmt.setInt(1, groupId);
			ResultSet inviteRs = inviteStmt.executeQuery();
			inviteRs.next();
			int inviteCode = inviteRs.getInt("invite_code");
			
			int inputCode = -1;
			
			while (true) {
				System.out.println(groupName + "그룹의 초대코드를 입력해주세요.");
				inputCode = sc.nextInt();
				
				if(inputCode == inviteCode) {
					System.out.println("초대코드가 일치합니다! ");
			        break;
				}
				else {
			        System.out.println("초대코드가 올바르지 않습니다. 다시 입력해주세요.\n");
			    }
			}
			
			// 4. 해당 user의 group_id update
			String updateSql  = "UPDATE User SET group_id = ?, is_leader =0 WHERE user_id=?";
			PreparedStatement updateStmt = conn.prepareStatement(updateSql);
			updateStmt.setInt(1, groupId);
			updateStmt.setInt(2, userId);
			int rows = updateStmt.executeUpdate();
			
			if (rows > 0) {
			    System.out.println(" 그룹에 성공적으로 가입되었습니다!");
			} else {
			    System.out.println("그룹 가입 중 문제가 발생했습니다.");
			}
	
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 그룹 생성하기 
	private static void createGroup (int userId) {
		
		try(Connection conn = DBConnector.getConnection()){
			
			// 1. 해당 유저가 이미 그룹에 속해있는지부터 확인한다. 

			String checkSql = "SELECT group_id FROM user WHERE user_id = ?"; // 현재 사용자의 group_id를 가져오는 sql 쿼리 작성 
			PreparedStatement checkStmt = conn.prepareStatement(checkSql); // 쿼리를 실행할 PreparedStatement 객체 생성 
			checkStmt.setInt(1,  userId); // 해당쿼리의 ?에 userId 값을 넣음 
			ResultSet rs = checkStmt.executeQuery(); // 쿼리 실행 -> 결과를 rs로 받아옴 
			
			if(rs.next() && rs.getObject("group_id") != null) {
				System.out.println("이미 다른 그룹에 속해있습니다. 그룹을 생성할 수 없습니다. ");
				return;
			}
			
			// 2. 사용자에게 그룹 이름 입력 받기
			Scanner sc = new Scanner (System.in);
			System.out.println("그룹 이름을 입력하세요 : ");
			String groupName = sc.nextLine ();
			
			// 3. 초대코드 생성
			int inviteCode = generateInviteCode(conn);
			
			// 4. 그룹 정보 Insert
			String insertSql = "INSERT INTO User_group (group_name, invite_code, leader) VALUES (?, ?, ?)";
			PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, groupName); // 
			insertStmt.setInt(2,  inviteCode);
			insertStmt.setInt(3, userId);
			insertStmt.execute();
			
			// 5. 생성된 group_id 가져오기
			ResultSet generatedKeys = insertStmt.getGeneratedKeys(); // 방금 INSERT 한 결과에서 생성된 기본키인 PK를 가져
			int newGroupId = -1; // 임시 변수 선언 
			if(generatedKeys.next()) { // 만약 생성된 키가 존재하면 (즉, INSERT 성공 및 자동 생성된 PK가 있다면) 
				newGroupId = generatedKeys.getInt(1); // 첫번째 열의 값을 가져와 newGroupId에 저장한다. 
			}
			
			// 6. 해당 user 정보 업데이트 
			String updateSql = "UPDATE User SET group_id = ?, is_leader =1 WHERE user_id =?";
			PreparedStatement updateStmt = conn.prepareStatement(updateSql);
			updateStmt.setInt(1, newGroupId);
			updateStmt.setInt(2, userId);
			updateStmt.executeUpdate();
			
			// 7. 결과 출력
			System.out.println();
			System.out.println(groupName + "이름의 그룹이 생성되었습니다. ");
			System.out.println("초대코드는 " + inviteCode + "입니다. ");
			
			
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	

	// 그룹 삭제 기능 
	private static void deleteGroup(int userId) {
		
		try (Connection conn = DBConnector.getConnection()) {
			
		      // 0. 현재 사용자가 리더인 경우에만 deleteGroup 호출
		       String checkLeaderSql = "SELECT is_leader, group_id FROM User WHERE user_id = ?";
		       PreparedStatement checkLeaderStmt = conn.prepareStatement(checkLeaderSql);
		       checkLeaderStmt.setInt(1, userId);
		       ResultSet checkLeaderRs = checkLeaderStmt.executeQuery();
		       
		       int isLeader = 0;
		       int groupId = -1;
		       
		       if (checkLeaderRs.next()) {
		    	   isLeader = checkLeaderRs.getInt("is_leader");
		    	   groupId = checkLeaderRs.getInt("group_id");
		    	   
			       if(isLeader == 1) {
			    	   System.out.println("리더에겐 그룹을 삭제할 수 있는 권한이 부여됩니다. ");
			    	   System.out.println("그룹을 삭제하시려면 YES를, 아니라면 YES를 제외한 아무 문자열을 입력해주세요. ");
			    	   
			    	   Scanner sc = new Scanner (System.in);
			    	   String yn = sc.nextLine ();
			    	   
			    	   if(yn.equals("YES")) {
			    		   System.out.println( groupId +"그룹을 삭제합니다. ");
				   			//1. 그룹에속한 모든 사용자 group_id, is_leader 초기화 
				   			String resetUsersSql = "UPDATE User SET group_id = NULL, is_leader=0 WHERE group_id = ?";
				   	        PreparedStatement resetUserStmt = conn.prepareStatement(resetUsersSql);
				   	        resetUserStmt.setInt(1, groupId);
				   	        resetUserStmt.executeUpdate();
				   	        
				   	        // 2. 그룹 삭제
				   	        String deleteGroupSql = "DELETE FROM User_group WHERE group_id = ? ";
				   	        PreparedStatement deleteGroupStmt = conn.prepareStatement(deleteGroupSql);
				   	        deleteGroupStmt.setInt(1, groupId);
				   	        deleteGroupStmt.executeUpdate();
				   	        
				   	        System.out.println("그룹이 성공적으로 삭제되었습니다!");
			    	   }
			    	   else {
			    		   	System.out.println("홈화면으로 돌아갑니다! ");
			    	   }
			       }
			       else {
			    	   System.out.println(" 리더가 아닙니다. 그룹을 삭제할 수 없습니다. ");
			    	   System.out.println("홈화면으로 돌아갑니다! ");
			       }

		       }

        
		}
		catch(SQLException e) {
	        e.printStackTrace();
		}
		
		
	}
	
	// invite code 생성기 
	private static int generateInviteCode(Connection conn) throws SQLException {
	    Random rand = new Random();
	    int code;

	    while (true) {
	        code = 10000000 + rand.nextInt(90000000); 

	        String checkSql = "SELECT 1 FROM User_group WHERE invite_code = ?";
	        PreparedStatement pstmt = conn.prepareStatement(checkSql);
	        pstmt.setInt(1, code);
	        ResultSet rs = pstmt.executeQuery();

	        if (!rs.next()) {
	            break;  // 중복 아님 → 탈출
	        }
	    }

	    return code;
	}
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
