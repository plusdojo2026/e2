package dto;

import java.io.Serializable;

public class UserDto implements Serializable {
	private String id; //ユーザーID
    private String password; //パスワード
    private String name; //ユーザーネーム
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserDto(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}
	public UserDto() {
		super();
		this.id ="";
		this.password = "";
		this.name ="";
	}
}



