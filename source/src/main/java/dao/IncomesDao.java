package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Incomes;

public class IncomesDao {

    // ✅ ユーザーごとのデータ取得
    public List<Incomes> selectByUser(String user_id) {
        List<Incomes> incomeList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/webapp2?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password"
            );

            String sql = "SELECT * FROM incomes WHERE user_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, user_id);

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                Incomes income = new Incomes(
                    rs.getInt("id"),
                    rs.getString("user_id"),
                    rs.getString("created_at"),
                    rs.getInt("amount"),
                    rs.getString("emotion"),
                    rs.getString("category")
                );

                incomeList.add(income);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return incomeList;
    }

    // ✅ データ登録
    public boolean insert(Incomes income) {
        boolean result = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/webapp2?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password"
            );

            String sql = "INSERT INTO incomes (user_id, created_at, amount, emotion, category) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setString(1, income.getUser_id());
            pStmt.setString(2, income.getCreated_at());
            pStmt.setInt(3, income.getAmount());
            pStmt.setString(4, income.getEmotion());
            pStmt.setString(5, income.getCategory());

            if (pStmt.executeUpdate() == 1) {
                result = true;
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // ✅ 更新
    public boolean update(Incomes income) {
        boolean result = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/webapp2?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password"
            );

            String sql = "UPDATE incomes SET created_at=?, amount=?, emotion=?, category=? WHERE id=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setString(1, income.getCreated_at());
            pStmt.setInt(2, income.getAmount());
            pStmt.setString(3, income.getEmotion());
            pStmt.setString(4, income.getCategory());
            pStmt.setInt(5, income.getId());

            if (pStmt.executeUpdate() == 1) {
                result = true;
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // ✅ 削除
    public boolean delete(int id) {
        boolean result = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/webapp2?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password"
            );

            String sql = "DELETE FROM incomes WHERE id=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setInt(1, id);

            if (pStmt.executeUpdate() == 1) {
                result = true;
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
