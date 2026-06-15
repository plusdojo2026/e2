package dto;

import java.io.Serializable;

public class ExpensesDto implements Serializable {
	private int user_id; // ユーザーID
	private Integer amount; // 金額
	private Integer emotion; // 感情
	private Integer category; // カテゴリ
	private Integer situation; // 状況
	private String item_name; // 商品名
	private String memo; // 備考
	private String created_at; // 日付
	private Integer tag; // タグ

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getEmotion() {
		return emotion;
	}

	public void setEmotion(Integer emotion) {
		this.emotion = emotion;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}	
	
	public Integer getSituation() {
		return situation;
	}

	public void setSituation(Integer situation) {
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

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public ExpensesDto(int user_id, Integer amount, Integer emotion, Integer category, Integer situation,
			String item_name, String memo, String created_at, Integer tag) {
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
		this.user_id = 0;
		this.amount = 0;
		this.emotion = 0;
		this.category = 0;
		this.situation = 0;
		this.item_name = "";
		this.memo = "";
		this.created_at = "";
		this.tag = 0;
	}

}