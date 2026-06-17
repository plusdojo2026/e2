package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.ExpensesDto;

public class ExpensesDao {

    // ✅ データ登録
    public boolean insert(ExpensesDto expense) {

        Connection conn = null;
        boolean result = false;

        try {
            // JDBCドライバ読み込み
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB接続
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/kakemi_db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password"
            );

            // SQL
            String sql = "INSERT INTO expenses "
                    + "(user_id, created_at, amount, emotion, category, situation, item_name, memo, tag) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pStmt = conn.prepareStatement(sql);

            // パラメータ設定
            pStmt.setString(1, expense.getUser_id());
            pStmt.setString(2, expense.getCreated_at());
            pStmt.setInt(3, expense.getAmount());
            pStmt.setString(4, expense.getEmotion());
            pStmt.setString(5, expense.getCategory());
            pStmt.setString(6, expense.getSituation());
            pStmt.setString(7, expense.getItem_name());
            pStmt.setString(8, expense.getMemo());
            pStmt.setString(9, expense.getTag());

            // 実行
            if (pStmt.executeUpdate() == 1) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // DB切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}