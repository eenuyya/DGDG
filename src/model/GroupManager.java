package model;

import java.sql.*;
import java.util.Scanner;
import java.util.Random;

import util.ConsoleStyle;
import util.DBUtil;


public class GroupManager {

	public static void manageGroup(int userId) {
		Scanner sc = new Scanner (System.in);
		
		// 해당 유저가 이미 그룹에 속해있는지 / 리더인지 확인한다. 
				try(Connection conn = DBUtil.getConnection()){
					String checkSql = "SELECT group_id, is_leader FROM user WHERE user_id = ?"; // 현재 사용자의 group_id를 가져오는 sql 쿼리 작성 
					PreparedStatement checkStmt = conn.prepareStatement(checkSql); // 쿼리를 실행할 PreparedStatement 객체 생성 
					checkStmt.setInt(1,  userId); // 해당쿼리의 ?에 userId 값을 넣음 
					ResultSet checkRs = checkStmt.executeQuery(); // 쿼리 실행 -> 결과를 rs로 받아옴 
					
					
					if (checkRs.next()) {
						boolean isLeader = checkRs.getBoolean("is_leader");
						Object groupId = checkRs.getObject("group_id");

						if (groupId != null && !isLeader) {
							showGroup(userId, isLeader);
							System.out.println("그룹에 속해있는 상태에선 새로운 그룹 생성 및 그룹 들어가기가 제한됩니다!");
						} 
						else if (groupId != null && isLeader) {
							showGroup(userId, isLeader);
							System.out.println("그룹을 삭제하려면 'delete'를 입력해주세요.");
							System.out.println("홈화면으로 돌아가려면 아무 키나 누르세요.");

							String delete = sc.nextLine();

							if (delete.equals("delete")) {
								deleteGroup(userId);
							} else {
								System.out.println(" ____________________________________\n"
				                        + "/                                    \\\n"
				                        + "|       홈 화면으로 돌아갑니다.      |\n"
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
						} 
						else {
							System.out.println();
							System.out.println();
							System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
					        		+ "┃             그룹 관리              ┃\n"
					        		+ "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n"
					        		+ "┃ [1] 그룹 만들기  [2] 그룹 들어가기 ┃\n"
					        		+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
							System.out.print("> ");

							int menu = sc.nextInt();

							switch (menu) {
								case 1:
									// 그룹 만들기
									createGroup(userId);
									break;
								case 2:
									// 그룹 들어가기
									joinGroup(userId);
									break;
								default:
									System.out.println("잘못된 입력입니다.");
									break;
							}
						}
					} else {
						// 사용자의 정보 자체가 없다면?
						System.out.println("사용자 정보가 없습니다.");
					}

				}
				catch(SQLException e) {
					e.printStackTrace();
				}
		
		

		}




	// 그룹 생성하기 
	private static void createGroup (int userId) {

		try(Connection conn = DBUtil.getConnection()){

			// 1. 해당 유저가 이미 그룹에 속해있는지부터 확인한다. 

			String checkSql = "SELECT group_id FROM user WHERE user_id = ?"; // 현재 사용자의 group_id를 가져오는 sql 쿼리 작성 
			PreparedStatement checkStmt = conn.prepareStatement(checkSql); // 쿼리를 실행할 PreparedStatement 객체 생성 
			checkStmt.setInt(1,  userId); // 해당쿼리의 ?에 userId 값을 넣음 
			ResultSet rs = checkStmt.executeQuery(); // 쿼리 실행 -> 결과를 rs로 받아옴 

			// 2. 사용자에게 그룹 이름 입력 받기
			Scanner sc = new Scanner(System.in);
			String groupName = null;

			while (true) {
				System.out.println();
				System.out.println();
				System.out.printf("┏━━━━━━━━━━━━━━━━━━━━┓\n"
		    			+ "┃  %-11s  ┃\n"
		    			+ "┗━━━━━━━━━━━━━━━━━━━━┛", "그룹 만들기");
				System.out.println();
			    System.out.print("그룹 이름을 입력하세요: ");
			    groupName = sc.nextLine();

			    // 그룹명 중복 체크 쿼리
			    String checkGroupSql = "SELECT 1 FROM User_group WHERE group_name = ?";
			    PreparedStatement checkGroupStmt = conn.prepareStatement(checkGroupSql);
			    checkGroupStmt.setString(1, groupName);
			    ResultSet checkRs = checkGroupStmt.executeQuery();

			    if (checkRs.next()) {
			        // 이미 존재하는 그룹명
			        System.out.println("❌ 이미 존재하는 그룹 이름입니다. 다른 이름을 입력해주세요.\n");
			    } else {
			        // 중복 아님 → OK!
			        break;
			    }
			}
			
			
			

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
			System.out.println();
			String paddedGroup = ConsoleStyle.padRightVisualWidth(groupName, 20);
            System.out.println(" _______________________________________________________\n"
            		+ "/                                                       \\\n"
            		+ "|     " + paddedGroup+ " 그룹이 생성되었습니다.        |\n"
            		+ "|   친구들을 초대하고 밥 가능 상태를 공유해보세요!       |\n"
            		+ "|   초대코드: "+ inviteCode + "                                   |\n"
            		+ "\\                                                       /\n"
            		+ " -------------------------------------------------------\n"
            		+ "    \\   ^__^\n"
            		+ "     \\  (oo)\\_______\n"
            		+ "        (__)\\       )\\/\\\n"
            		+ "            ||----w |\n"
            		+ "            ||     ||");
            System.out.println();
            try {
                Thread.sleep(3000);  // 3초 대기
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}



	}

	// 그룹에 들어가기 
	private static void joinGroup (int userId) {
		try (Connection conn = DBUtil.getConnection()){
			
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
				System.out.println();
				System.out.println();
				System.out.printf("┏━━━━━━━━━━━━━━━━━━━━━━┓\n"
						+ "┃  %-12s  ┃\n"
						+ "┗━━━━━━━━━━━━━━━━━━━━━━┛", "그룹 들어가기");
				System.out.println();
				System.out.println("들어갈 그룹 이름을 입력하세요 : ");
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
				System.out.println(" ____________________________________\n"
                        + "/                                    \\\n"
                        + "|       그룹에 가입되었습니다!        |\n"
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
			} else {
				System.out.println("그룹 가입 중 문제가 발생했습니다.");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// 그룹 삭제 기능 
	private static void deleteGroup(int userId) {

		try (Connection conn = DBUtil.getConnection()) {

		      // 0. 현재 사용자가 리더인 경우에만 deleteGroup 호출
		       String deletegroupSql = "SELECT u.group_id, g.group_name FROM User u JOIN User_group g ON u.group_id=g.group_id WHERE u.user_id = ?";
		       PreparedStatement deletegroupStmt = conn.prepareStatement(deletegroupSql);
		       deletegroupStmt.setInt(1, userId);
		       ResultSet deletegroupRs = deletegroupStmt.executeQuery();

		       int groupId = -1;
		       String groupName = "";

		       if (deletegroupRs.next()) {
		    	   groupId = deletegroupRs.getInt("u.group_id");
		    	   groupName = deletegroupRs.getString("g.group_name");
		    	   System.out.println(" _______________________________________\n"
	                        + "/                                       \\\n"
	                        + "|     그룹을 정말 삭제하시겠습니까?     |\n"
	                        + "|     다시 되돌릴 수 없습니다. (y/n)    |\n"
	                        + "\\                                       /\n"
	                        + " ---------------------------------------\n"
	                        + "    \\   ^__^\n"
	                        + "     \\  (oo)\\_______\n"
	                        + "        (__)\\       )\\/\\\n"
	                        + "            ||----w |\n"
	                        + "            ||     ||");
	                System.out.println();
		    	   
			    	   System.out.print("> ");

			    	   Scanner sc = new Scanner (System.in);
			    	   String deleteyn = sc.nextLine ();

			    	   if(deleteyn.equalsIgnoreCase("y")) {
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
				   	        
				   	     String paddedGroup = ConsoleStyle.padRightVisualWidth(groupName, 20);

				   	     System.out.println(" ________________________________________________\n"
			                        + "/                                                \\\n"
			                        + "|  " + paddedGroup + " 그룹이 삭제되었습니다.   |\n"
			                        + "\\                                                /\n"
			                        + " ------------------------------------------------\n"
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
			    	   else {
			    		   System.out.println(" ____________________________________\n"
			                        + "/                                    \\\n"
			                        + "|       홈 화면으로 돌아갑니다.      |\n"
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
			       }
		}catch(SQLException e) {
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
	public static void showGroup(int userId, boolean isLeader) {
	    try (Connection conn = DBUtil.getConnection()) {
	        int groupId = -1;

	        // 1. 내 그룹 정보 확인하기
	        String myInfoSql = "SELECT g.group_id, g.group_name, g.invite_code, u2.user_name AS leader_name "
	                         + "FROM User u "
	                         + "JOIN User_group g ON u.group_id = g.group_id "
	                         + "JOIN User u2 ON u2.user_id = g.leader "
	                         + "WHERE u.user_id = ?";
	        PreparedStatement myInfoStmt = conn.prepareStatement(myInfoSql);
	        myInfoStmt.setInt(1, userId);
	        ResultSet myInfoRs = myInfoStmt.executeQuery();
	        String iAmLeader = isLeader? " (me)" : ""; 
	        if (myInfoRs.next()) {
	        	System.out.println();
	        	System.out.println();
	        	System.out.println();
		        System.out.printf("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
		    			+ "┃  %-22s  ┃\n"
		    			+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n", "내 그룹 정보");
		        System.out.println();
	            System.out.println(" 👥 그룹명 : " + myInfoRs.getString("group_name"));
	            groupId = myInfoRs.getInt("group_id");
	            System.out.println(" 💬 초대코드 : " + myInfoRs.getInt("invite_code"));
	            System.out.println(" 👑 그룹 리더 : " + myInfoRs.getString("leader_name") + iAmLeader);
	        } else {
	        	System.out.println(" _____________________________\n"
                        + "/                             \\\n"
                        + "|    속한 그룹이 없습니다.    |\n"
                        + "\\                             /\n"
                        + " -----------------------------\n"
                        + "    \\   ^__^\n"
                        + "     \\  (oo)\\_______\n"
                        + "        (__)\\       )\\/\\\n"
                        + "            ||----w |\n"
                        + "            ||     ||");
	        	try {
	    	        Thread.sleep(1500);
	    	    } catch (InterruptedException e) {
	    	        Thread.currentThread().interrupt();
	    	    }
	            return;
	        }

	        // 2. 내 그룹원들 확인하기 - User_view 사용
	        String mateSql = "SELECT user_id, user_name, is_available "
	                       + "FROM User_view "
	                       + "WHERE group_id = (SELECT group_id FROM User WHERE user_id = ?)";
	        PreparedStatement mateStmt = conn.prepareStatement(mateSql);
	        mateStmt.setInt(1, userId);
	        ResultSet mateRs = mateStmt.executeQuery();

	        System.out.println();
	        System.out.println("╔════ 그룹원 목록════════════════════════════════════════════════════╗");
	        System.out.printf("║ %-66s ║\n", "");
	        // 그룹원 정보 출력
	        int count = 0;
	        while (mateRs.next() && count < 6) {
	            String name = mateRs.getString("user_name");
	            int id = mateRs.getInt("user_id"); 
	            if (id == userId) name += " (me)";
	            boolean is_available = mateRs.getBoolean("is_available");
	            String availabilityText = is_available
	                    ? ConsoleStyle.apply(ConsoleStyle.EWHA_GREEN, "가능")
	                    : ConsoleStyle.apply(ConsoleStyle.DARK_RED, "불가능");

	            String content = "- " + name + " 밥 " + availabilityText;
	            System.out.println("║ " + ConsoleStyle.padRightVisualWidth(content, 66) + " ║");

	            count++;
	        }

	        // 남은 빈 줄 채우기 (최대 6명 고정)
	        for (int i = count; i < 6; i++) {
	            System.out.printf("║ %-66s ║\n", "");
	        }

	        System.out.println("╚════════════════════════════════════════════════════════════════════╝");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
