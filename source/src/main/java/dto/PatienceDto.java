package dto;

import java.io.Serializable;

public class PatienceDto implements Serializable {
	private Integer amount; // 金額
	private String created_at; // 日付
	private String category; // カテゴリ
	private String item_name; // 商品名
	private String memo; // 備考
	private String emotion; // 感情
	private String situation; // 状況
	private Integer id; // ID
	private String user_id; // ユーザーID

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public PatienceDto(Integer amount, String created_at, String category, String item_name, String memo,
			String emotion, String situation, Integer id, String user_id) {
		this.amount = amount;
		this.created_at = created_at;
		this.category = category;
		this.item_name = item_name;
		this.memo = memo;
		this.emotion = emotion;
		this.situation = situation;
		this.id = id;
		this.user_id = user_id;
	}

	public PatienceDto() {
		this(0, "", "", "", "", "", "", 0, "");
	}
}
