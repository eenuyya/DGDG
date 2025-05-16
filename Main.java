import cli.AppMenu;
// import util.Login;

public class Main {
    public static void main(String[] args) {
        System.out.println("==== DGDG CLI 앱 시작 ====");

        // 로그인 시도
        // int userId = Login.login();
        int userId = 1001;	// login 구현되기 전까지는 코드 실행시 userId 1001 이용
        if (userId == -1) {
            System.out.println("로그인 실패. 프로그램을 종료합니다.");
            return;
        }

        // 로그인 성공 시 AppMenu 실행
        AppMenu appMenu = new AppMenu(userId);
        appMenu.start();
    }
}
