import cli.AppMenu;
// import util.Login;

public class Main {
    public static void main(String[] args) {
        System.out.println("==== DGDG CLI 앱 시작 ====");

        // 로그인 시도
        // int userId = Login.login();
        
//        public class Login {
//            public static User login() {
//                // 로그인 처리 후
//                int userId = ...;
//                String userName = ...;
//                return new User(userId, userName);
//            }
//        }

        
        int userId = 1001;	// login 구현되기 전까지는 코드 실행시 userId 1001 이용
        if (userId == -1) {
            System.out.println("로그인 실패. 프로그램을 종료합니다.");
            return;
        }

        // 로그인 성공 시 AppMenu 실행
        printHello();
        AppMenu appMenu = new AppMenu(userId); 
        appMenu.start();
    }
	private static void printHello() {
		System.out.println();
	    System.out.println("Bap But Bot에 오신 걸 환영합니다!"
	            + "\n친구들과 이화여대 근처 식당에서 맛있는 식사를 해보세요.");
	}
}

// 홈화면에서 안녕하세요 {user_name}님! 하려면 login 할때 userName까지
// 매개변수로 입력받아야 하고, 그러려면 User 객체를 생성해야함.
// 로그인 먼저 구현되고 나서 할 것