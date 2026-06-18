package dto;

import java.io.Serializable;

public class PatienceDto implements Serializable {
	
	private Integer id;
	private String user_id;
    private Integer amount;
    private String emotion;
    private String category;
    private String situation;
    private String item_name;
    private String memo;
    private String created_at;

    // ===== Getter / Setter =====
    
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


    
        public PatienceDto(
        	Integer id,
            String user_id,
            Integer amount,
            String emotion,
            String category,
            String situation,
            String item_name,
            String memo,
            String created_at)
        {
            this.id = id;
        	this.user_id = user_id;
            this.amount = amount;
            this.emotion = emotion;
            this.category = category;
            this.situation = situation;
            this.item_name = item_name;
            this.memo = memo;
            this.created_at = created_at;
        }
       public PatienceDto() {
		    this.id = 0;
		    this.user_id = "";
		    this.amount = 0;
		    this.emotion = "";
		    this.category = "";
		    this.situation = "";
		    this.item_name = "";
		    this.memo = "";
		    this.created_at = "";
}

}