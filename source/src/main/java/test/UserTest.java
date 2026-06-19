package test;


import dao.UserDao;
import dto.UserDto;


public class UserTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ


	UserDao dao = new UserDao();
	
	UserDto user = new UserDto();
	user.setId("testuser");
	user.setPw("1234");
	
	// ① 登録
	boolean insertResult = dao.insert(user);
	System.out.println(insertResult);
	
	// ② ログイン
	boolean loginResult = dao.Login(user);
	System.out.println(loginResult);
		}
}

