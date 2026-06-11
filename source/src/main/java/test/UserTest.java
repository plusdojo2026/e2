package test;


import dao.UserDao;
import dto.UserDto;


public class UserTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

        UserDao dao = new UserDao();

        UserDto user = new UserDto();
        user.setId("user1");
        user.setPw("pass123");

        boolean result = dao.isLoginOK(user);

        if (result) {
            System.out.println("ログイン成功");
        } else {
            System.out.println("ログイン失敗");
        }
    }

}


