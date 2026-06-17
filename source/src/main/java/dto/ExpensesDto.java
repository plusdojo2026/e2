package dto;

import java.io.Serializable;

public class ExpensesDto implements Serializable {
	private String user_id; // ユーザーID
	private Integer amount; // 金額
	private String emotion; // 感情
	private String category; // カテゴリ
	private String situation; // 状況
	private String item_name; // 商品名
	private String memo; // 備考
	private String created_at; // 日付
	private String tag; // タグ

	
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


	public String getSituation() {
		return situation;
	}


	public void setSituation(String situation) {
		this.situation = situation;
	}


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public ExpensesDto(String user_id, Integer amount, String emotion, String category, String situation,
			String item_name, String memo, String created_at, String tag) {
		super();
		this.user_id = user_id;
		this.amount = amount;
		this.emotion = emotion;
		this.category = category;
		this.situation = situation;
		this.item_name = item_name;
		this.memo = memo;
		this.created_at = created_at;
		this.tag = tag;
	}


	public ExpensesDto() {
		this.user_id = "";
		this.amount = 0;
		this.emotion = "";
		this.category = "";
		this.situation = "";
		this.item_name = "";
		this.memo = "";
		this.created_at = "";
		this.tag = "";
	}

}