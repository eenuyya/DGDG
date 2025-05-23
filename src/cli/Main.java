package cli;

import cli.AppMenu;
import util.Login;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	String Logo = "   ____      _       ____          ____     _   _  _____         ____     U  ___ u _____   \n"
    			+ "U | __\")uU  /\"\\  u U|  _\"\\ u    U | __\")uU |\"|u| ||_ \" _|     U | __\")u    \\/\"_ \\/|_ \" _|  \n"
    			+ " \\|  _ \\/ \\/ _ \\/  \\| |_) |/     \\|  _ \\/ \\| |\\| |  | |        \\|  _ \\/    | | | |  | |    \n"
    			+ "  | |_) | / ___ \\   |  __/        | |_) |  | |_| | /| |\\        | |_) |.-,_| |_| | /| |\\   \n"
    			+ "  |____/ /_/   \\_\\  |_|           |____/  <<\\___/ u |_|U        |____/  \\_)-\\___/ u |_|U   \n"
    			+ " _|| \\\\_  \\\\    >>  ||>>_        _|| \\\\_ (__) )(  _// \\\\_      _|| \\\\_       \\\\   _// \\\\_  \n"
    			+ "(__) (__)(__)  (__)(__)__)      (__) (__)    (__)(__) (__)    (__) (__)     (__) (__) (__) ";
    	
    	System.out.println();
    	System.out.println(Logo);
    	System.out.println();
    	
        Scanner sc = new Scanner(System.in);

        System.out.println("안녕하세요☺️\nBap But Bot에 오신 걸 환영합니다."
                + "\n친구들과 이화여대 근처 식당에서 맛있는 식사를 해보세요.");
        
	    System.out.println();
	    System.out.println("┌─────────────────────────────────────────────────────────────────────┐");
	    System.out.printf ("│ %-67s │\n", "");
	    System.out.printf ("│ %-63s │\n", "[1] 회원가입");
	    System.out.printf ("│ %-64s │\n", "[2] 로그인");
	    System.out.printf ("│ %-67s │\n", "");
	    System.out.println("└─────────────────────────────────────────────────────────────────────┘");
	    System.out.print("> ");
       
	    try {
	        int choice = Integer.parseInt(sc.nextLine());
	        if (choice == 1) {
	            Login.signup(sc);
	        }
	        if (choice != 1 && choice != 2) {
	        	System.out.println(" _____________________________\n"
	            		+ "/                             \\\n"
	            		+ "|         잘못된 선택         |\n"
	            		+ "|    프로그램을 종료합니다.   |\n"
	            		+ "\\                             /\n"
	            		+ " -----------------------------\n"
	            		+ "    \\   ^__^\n"
	            		+ "     \\  (oo)\\_______\n"
	            		+ "        (__)\\       )\\/\\\n"
	            		+ "            ||----w |\n"
	            		+ "            ||     ||");
	            return;
	        }} catch (NumberFormatException e) {
	        System.out.println(" _____________________________\n"
		            + "/                             \\\n"
		            + "|    숫자를 입력해주세요.     |\n"
		            + "|    프로그램을 종료합니다.   |\n"
		            + "\\                             /\n"
		            + " -----------------------------\n"
		            + "    \\   ^__^\n"
		            + "     \\  (oo)\\_______\n"
		            + "        (__)\\       )\\/\\\n"
		            + "            ||----w |\n"
		            + "            ||     ||");
		        return;
		    }
        // 로그인 반복 시도
	    int userId = -1;
        int count = 0;
        while (userId == -1 && count++ < 3) {
            userId = Login.login(sc);
        }
        
        
        if (userId == -1) {
        	System.out.println(" _____________________________\n"
            		+ "/                             \\\n"
            		+ "|         로그인 실패         |\n"
            		+ "|    프로그램을 종료합니다.   |\n"
            		+ "\\                             /\n"
            		+ " -----------------------------\n"
            		+ "    \\   ^__^\n"
            		+ "     \\  (oo)\\_______\n"
            		+ "        (__)\\       )\\/\\\n"
            		+ "            ||----w |\n"
            		+ "            ||     ||");
            return;
        }

        // 로그인 성공 시 AppMenu 실행
        printHello();
        AppMenu appMenu = new AppMenu(userId); 
        appMenu.start();
    }
	private static void printHello() {
		System.out.println();
    	System.out.println(" _____________________________________\n"
        		+ "/                                      \\\n"
        		+ "|  Bap But Bot에 오신 걸 환영합니다!   |\n"
        		+ "|   친구들과 이화여대 근처 식당에서    |\n"
        		+ "|      맛있는 식사를 해보세요.         |\n"
        		+ "\\                                     /\n"
        		+ " ------------------------------------\n"
        		+ "    \\   ^__^\n"
        		+ "     \\  (oo)\\_______\n"
        		+ "        (__)\\       )\\/\\\n"
        		+ "            ||----w |\n"
        		+ "            ||     ||");
		try {
	        Thread.sleep(1000);  // 1초 대기
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}
}