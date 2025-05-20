package util;

import java.sql.*;
import java.util.Scanner;
import util.ConsoleStyle;

public class Login {

    public static int login(Scanner sc) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            
            System.out.printf("\n┏━━━━━━━━━━━━━━━━━━┓\n"
        			+ "┃  %-11s  ┃\n"
        			+ "┗━━━━━━━━━━━━━━━━━━┛\n", "로그인");
            System.out.print("닉네임을 입력해주세요\n> ");
            String userName = sc.nextLine();

            System.out.print("비밀번호를 입력해주세요\n> ");
            String password = sc.nextLine();

            String sql = "SELECT user_id FROM User WHERE user_name = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("로그인이 완료되었습니다.");
                return rs.getInt("user_id");
            } else {
                System.out.println("닉네임에 해당하는 회원이 없거나 비밀번호가 틀렸습니다.\n다시 확인해주세요!");
                return -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }

    public static void signup(Scanner sc) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            System.out.printf("┏━━━━━━━━━━━━━━━━━━┓\n"
        			+ "┃  %-10s  ┃\n"
        			+ "┗━━━━━━━━━━━━━━━━━━┛\n", "회원가입");
            
            String userName;
            while (true) {
                System.out.print("닉네임을 입력해주세요\n> ");
                userName = sc.nextLine();

                String checkSql = "SELECT * FROM User WHERE user_name = ?";
                pstmt = conn.prepareStatement(checkSql);
                pstmt.setString(1, userName);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("중복되는 닉네임입니다.\n다른 닉네임을 입력해주세요!");
                    rs.close();
                    pstmt.close();
                } else {
                    break;
                }
            }

            System.out.print("비밀번호를 입력해주세요\n> ");
            String password = sc.nextLine();

            String insertSql = "INSERT INTO User (user_name, password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            String message = userName + "님, BBB의 회원이 되신 것을 환영합니다!";
           // String paddedMessage = ConsoleStyle.padRightVisualWidth(message, 35);
            int bubbleWidth = 35;
            String paddedMessage = ConsoleStyle.padRightVisualWidth(message, bubbleWidth);

            // 말풍선
            System.out.println(" " + "_".repeat(bubbleWidth + 2));
            System.out.println("/" + " ".repeat(bubbleWidth + 2) + "\\");
            System.out.println("| " + paddedMessage + " |");
            System.out.println("\\" + " ".repeat(bubbleWidth + 2) + "/");
            System.out.println(" " + "-".repeat(bubbleWidth + 2));
            
            System.out.println(" __________________________________________________\n"
            		+ "/                                                  \\\n"
            		+ "|   " + paddedMessage + "    |\n"
            		+ "\\                                                  /\n"
            		+ " --------------------------------------------------\n"
            		+ "    \\   ^__^\n"
            		+ "     \\  (oo)\\_______\n"
            		+ "        (__)\\       )\\/\\\n"
            		+ "            ||----w |\n"
            		+ "            ||     ||");
            System.out.println();
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
    }
}
