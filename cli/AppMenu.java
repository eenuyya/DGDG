package cli;

import model.SearchManager;
import model.SuggestManager;
//import model.MyMenuManager;
//import model.MyGroupManager;

import java.util.Scanner;

public class AppMenu {

    private final Scanner scanner;
    private final int userId;
    private final SearchManager searchManager;
    private final SuggestManager suggestManager;
    //private final MyMenuManager myMenuManager;
    //private final MyGroupManager myGroupManager;

    public AppMenu(int userId) {
        this.userId = userId;
        this.scanner = new Scanner(System.in);
        this.searchManager = new SearchManager();
        this.suggestManager = new SuggestManager();
        //this.myMenuManager = new MyMenuManager();
        //this.myGroupManager = new MyGroupManager();
    }

    public void start() {
        int choice = -1;

        while (choice != 0) {
            printMenu();
            choice = getUserChoice();

            switch (choice) {
                case 1:
                    handleSearch();
                    break;
                case 2:
                    handleSuggest();
                    break;
//                case 3:
//                    handleMyMenu();
//                    break;
//                case 4:
//                    handleMyGroup();
//                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n===== DGDG CLI 메뉴 =====");
        System.out.println("1. 식당 검색");
        System.out.println("2. 식당/메뉴 추천");
        System.out.println("3. 마이메뉴");
        System.out.println("4. 그룹");
        System.out.println("0. 종료");
        System.out.print("선택: ");
    }

    private int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ignored) {}
        return choice;
    }

    private void handleSearch() {
        System.out.print("검색할 식당 이름을 입력하세요: ");
        String keyword = scanner.nextLine();
        searchManager.searchByName(userId, keyword);  // 검색 + 후속 인터랙션(fav/star)은 내부에서 처리
    }

    private void handleSuggest() {
        suggestManager.suggestSelect(userId);
    }

//    private void handleMyMenu() {
//        myMenuManager.showFavorites(userId);
//    }
//
//    private void handleMyGroup() {
//        myGroupManager.manageGroups(userId);
//    }
}
