package cli;

import model.SearchManager;
import model.SuggestManager;
import model.MyMenuManager;
import model.GroupManager;
import util.DBUtil;
import util.ConsoleStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class AppMenu {

    private final Scanner scanner;
    private final int userId;
    private final SearchManager searchManager;
    private final SuggestManager suggestManager;
    private final MyMenuManager myMenuManager;
    private final GroupManager GroupManager;

    public AppMenu(int userId) {
        this.userId = userId;
        this.scanner = new Scanner(System.in);
        this.searchManager = new SearchManager();
        this.suggestManager = new SuggestManager();
        this.myMenuManager = new MyMenuManager();
        this.GroupManager = new GroupManager();
    }

    public void start() {
        int choice = -1;

        while (choice != 0) {
        	System.out.println();
        	System.out.println();
        	System.out.println();
        	System.out.println(Logo);

            showAvailableGroupmatesBox(userId);
            printMyAvailability(); // 현재 내 상태 출력
            System.out.println();
            System.out.println();
            printMenu();

            String input = scanner.nextLine().trim().toLowerCase();

            // 가능 상태 토글 입력 처리
            if (input.equals("on") || input.equals("off")) {
                toggleIsAvailable(userId, input);
                continue;
            }

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하거나 on/off 중 하나를 입력해주세요.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleSearch();
                    break;
                case 2:
                    handleSuggest();
                    break;
                case 3:
                     handleMyMenu();
                    break;
                case 4:
                    handleGroup();
                    break;
                case 0:
                	System.out.println();
                	printBye();
                    break;
                default:
                	System.out.println();
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }

        scanner.close();
    }


    String Logo = "   ____      _       ____          ____     _   _  _____         ____     U  ___ u _____   \n"
    		+ "U | __\")uU  /\"\\  u U|  _\"\\ u    U | __\")uU |\"|u| ||_ \" _|     U | __\")u    \\/\"_ \\/|_ \" _|  \n"
    		+ " \\|  _ \\/ \\/ _ \\/  \\| |_) |/     \\|  _ \\/ \\| |\\| |  | |        \\|  _ \\/    | | | |  | |    \n"
    		+ "  | |_) | / ___ \\   |  __/        | |_) |  | |_| | /| |\\        | |_) |.-,_| |_| | /| |\\   \n"
    		+ "  |____/ /_/   \\_\\  |_|           |____/  <<\\___/ u |_|U        |____/  \\_)-\\___/ u |_|U   \n"
    		+ " _|| \\\\_  \\\\    >>  ||>>_        _|| \\\\_ (__) )(  _// \\\\_      _|| \\\\_       \\\\   _// \\\\_  \n"
    		+ "(__) (__)(__)  (__)(__)__)      (__) (__)    (__)(__) (__)    (__) (__)     (__) (__) (__) ";



    
 
    private void showAvailableGroupmatesBox(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            // 1. 그룹 ID 확인
            String groupSql = "SELECT group_id FROM User WHERE user_id = ?";
            PreparedStatement groupStmt = conn.prepareStatement(groupSql);
            groupStmt.setInt(1, userId);
            ResultSet groupRs = groupStmt.executeQuery();

            if (!groupRs.next() || groupRs.getObject("group_id") == null) {
            	System.out.println();
                System.out.println("그룹에 가입하고 친구들과 밥 가능 상태를 공유해보세요.");
                System.out.println();
                return;
            }

            int groupId = groupRs.getInt("group_id");

            // 2. 가능한 그룹원 조회 (최대 6명)
            String mateSql = "SELECT user_id, user_name FROM User WHERE group_id = ? AND is_available = 1";
            PreparedStatement mateStmt = conn.prepareStatement(mateSql);
            mateStmt.setInt(1, groupId);
            ResultSet mateRs = mateStmt.executeQuery();

            // 그룹원 이름 저장
            String[] members = new String[6];  // 최대 6줄
            int idx = 0;
            while (mateRs.next() && idx < 6) {
                String name = mateRs.getString("user_name");
                String id = mateRs.getString("user_id");

                if (id.equals(String.valueOf(userId))) name += " (me)";

                members[idx] = name;
                idx++;
            }

            
            // 박스 출력
            System.out.println();
            System.out.println("╔════ 지금 같이 밥 먹을 수 있는 친구 목록🟢 ═════════════════════════╗");
            System.out.printf("║ %-66s ║\n","");
            for (int i = 0; i < 6; i++) {
                String content = (members[i] != null) ? " " + members[i] : "";
                // 40칸 맞춰서 공백 패딩
                System.out.printf("║ %-66s ║\n", content);
            }
            System.out.println("╚════════════════════════════════════════════════════════════════════╝");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private void printMyAvailability() {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT group_id, is_available FROM User WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	int groupId = rs.getInt("group_id");
            	if (rs.wasNull()) return;
                int available = rs.getInt("is_available");
                String status = (available == 1) ? "🍽  지금 같이 밥 먹을 수 있어요 (ON)" : "🙅‍♀️ 지금은 힘들어요 (OFF)";
                System.out.println("[내 상태] " + status);
                System.out.println("→ 상태를 바꾸고 싶다면 'on' 또는 'off'를 입력하세요.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   
    	private void printMenu() {
    	    System.out.println();
    	    System.out.println("┌───────────────────────────── 메뉴 이동 ─────────────────────────────┐");
    	    System.out.printf ("│ %-67s │\n", "");
    	    System.out.printf ("│ %-63s │\n", "[1] 식당 검색🔍");
    	    System.out.printf ("│ %-61s │\n", "[2] 식당/메뉴 추천💭");
    	    System.out.printf ("│ %-63s │\n", "[3] 마이메뉴👤");
    	    System.out.printf ("│ %-65s │\n", "[4] 그룹👥");
    	    System.out.printf ("│ %-65s │\n", "[0] 종료🚪");
    	    System.out.println("└─────────────────────────────────────────────────────────────────────┘");
    	    System.out.print("> ");

    	}

    
    public static void toggleIsAvailable(int userId, String input) {
        try (Connection conn = DBUtil.getConnection()) {
            String checkSql = "SELECT is_available FROM User WHERE user_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, userId);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("사용자를 찾을 수 없습니다.");
                return;
            }

            int current = rs.getInt("is_available");
            int updated = input.equals("on") ? 1 : 0;

            // 이미 같은 상태인 경우
            if (current == updated) {
                System.out.println("이미 현재 상태가 " + (updated == 1 ? "ON" : "OFF") + "입니다.");
                return;
            }

            String updateSql = "UPDATE User SET is_available = ? WHERE user_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, updated);
            updateStmt.setInt(2, userId);
            updateStmt.executeUpdate();

            System.out.println("상태가 " + (updated == 1 ? "ON (🍽 밥 가능)" : "OFF (🙅‍♀️ 밥 불가)") + "으로 변경되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ignored) {}
        return choice;
    }

    private void handleSearch() {
    	System.out.println();
        System.out.println();
        System.out.println();
    	System.out.printf("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
    			+ "┃  %-23s  ┃\n"
    			+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛", "식당 검색");
    	System.out.println();
        System.out.print("검색할 식당 이름을 입력하세요: ");
        String keyword = scanner.nextLine();
        searchManager.searchByName(userId, keyword);
    }

    private void handleSuggest() {
    	System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
        		+ "┃        식당/메뉴 추천         ┃\n"
        		+ "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n"
        		+ "┃ [1] 식당추천   [2] 메뉴추천   ┃\n"
        		+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

    	System.out.println();
        suggestManager.suggestSelect(userId);
    }
    
    private void handleMyMenu() {
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

    	
    	String name = "";
        String groupName = "";
        boolean isAvailable = false;
        double avgRating = 0.0;
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pmtst = conn.prepareStatement(sql)
            ) {
        	pmtst.setInt(1, userId);
        	
                try (ResultSet rs = pmtst.executeQuery()) {
                    if (rs.next()) {
                        name        = rs.getString("user_name");
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
        while (true) {
        	System.out.println();
        	System.out.println();
        	System.out.println();
	        System.out.printf("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n"
	    			+ "┃  %-25s  ┃\n"
	    			+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n", name+"님의 MENU");
	        System.out.println();
	        System.out.println(" 👤 닉네임: "+name);
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
	        int menuChoice=scanner.nextInt();
	       scanner.nextLine();
	        
	       if (menuChoice == 3) {
	           System.out.println("홈화면으로 돌아갑니다.");
	           return;
	       }
	
	       switch (menuChoice) {
	           case 1 -> myMenuManager.menuHandler(userId, 1);
	           case 2 -> myMenuManager.menuHandler(userId, 2);
	           default -> System.out.println("1~3 중에서 선택해주세요.");
	       }
	       System.out.println();
       }
    }
	private void handleGroup() {
	  GroupManager.manageGroup(userId);
	}
    private void printBye() {
    	System.out.println(" __________________\n"
        		+ "/                    \\\n"
        		+ "|       잘가요       |\n"
        		+ "\\                    /\n"
        		+ " --------------------\n"
        		+ "    \\   ^__^\n"
        		+ "     \\  (oo)\\_______\n"
        		+ "        (__)\\       )\\/\\\n"
        		+ "            ||----w |\n"
        		+ "            ||     ||");
        System.out.println();
    }
}