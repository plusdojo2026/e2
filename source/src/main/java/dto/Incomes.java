package dto;

import java.io.Serializable;

public class Incomes implements Serializable {

    private int id;             // ID（主キー）
    private String user_id;     // ユーザーID
    private String created_at;  // 日付
    private Integer amount;     // 金額
    private String emotion;     // 感情
    private String category;    // カテゴリ

    // ===== getter / setter =====

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    // ===== コンストラクタ =====

    public Incomes() {
        this.id = 0;
        this.user_id = "";
        this.created_at = "";
        this.amount = 0;
        this.emotion = "";
        this.category = "";
    }

    public Incomes(int id, String user_id, String created_at,
                   Integer amount, String emotion, String category) {

        this.id = id;
        this.user_id = user_id;
        this.created_at = created_at;
        this.amount = amount;
        this.emotion = emotion;
        this.category = category;
    }
}
