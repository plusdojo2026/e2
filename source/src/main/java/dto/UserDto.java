package dto;

import java.io.Serializable;

public class UserDto implements Serializable {
	private String id; //ユーザーID
    private String pw; //パスワード
    private String name; //ユーザーネーム
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return pw;
	}
	public void setPassword(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserDto(String id, String pw, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	public UserDto() {
		super();
		this.id ="";
		this.pw = "";
		this.name ="";
	}
}



