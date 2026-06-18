package dto;

import java.io.Serializable;

public class Incomes implements Serializable {

	private String user_id;
	private Integer amount;
	private String emotion;
	private String category;
	private String created_at;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Incomes(String user_id, Integer amount, String emotion, String category, String created_at) {
		this.user_id = user_id;
		this.amount = amount;
		this.emotion = emotion;
		this.category = category;
		this.created_at = created_at;
	}

	public Incomes() {
		this("", 0, "", "", "");
	}

	public Incomes(String user_id, String created_at, Integer amount, String emotion, String category) {
		this.user_id = user_id;
		this.created_at = created_at;
		this.amount = amount;
		this.emotion = emotion;
		this.category = category;
	}

	public Incomes(int id,String user_id, String created_at, Integer amount, String emotion, String category) {
		this.id = id;
		this.user_id = user_id;
		this.created_at = created_at;
		this.amount = amount;
		this.emotion = emotion;
		this.category = category;
	}
}