package dto;
import java.io.Serializable;
public class Incomes implements Serializable {
	
	private int number; 		// 番号
	private String created_at; 		// 日付
	private Integer amount;	// 金額
	private Integer emotion_id; 	//感情
	private Integer category_id; 	// カテゴリ
	private Integer situation_id; 		// 状況
	private String item_name; 	// 商品名
	private String memo; 	// 備考
	private Integer tag_id; 		// タグ
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getEmotion_id() {
		return emotion_id;
	}
	public void setEmotion_id(Integer emotion_id) {
		this.emotion_id = emotion_id;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Integer getSituation_id() {
		return situation_id;
	}
	public void setSituation_id(Integer situation_id) {
		this.situation_id = situation_id;
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
	public Integer getTag_id() {
		return tag_id;
	}
	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}
	
	public Incomes() {
		this.number = 0;
		this.created_at = "";
		this.amount = 0;
		this.emotion_id = 0;
		this.category_id = 0;
		this.situation_id = 0;
		this.item_name = "";
		this.memo = "";
		this.tag_id = 0;
	}
	public Incomes(int number, String created_at, Integer amount, Integer emotion_id, Integer category_id, Integer situation_id,
			String item_name, String memo, Integer tag_id) {
		this.number = number;
		this.created_at = created_at;
		this.amount = amount;
		this.emotion_id = emotion_id;
		this.category_id = category_id;
		this.situation_id = situation_id;
		this.item_name = item_name;
		this.memo = memo;
		this.tag_id = tag_id;
	}
}