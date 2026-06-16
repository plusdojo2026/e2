package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BudgetDto;

public class BudgetDao {
	//登録
	public boolean insert(BudgetDto budget) {

        Connection conn = null;
        boolean result = false;

        try {
            // JDBCドライバ読み込み
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB接続
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/kakemi_db?"
                + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root",
                "password"
            );

            // SQL
            String sql = "INSERT INTO budgets (budget_amount, goal_amount, user_id) "
                  + "VALUES (?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // パラメータ設定
            pStmt.setInt(1, budget.getBudget_amount());
            pStmt.setInt(2, budget.getGoal_amount());
            pStmt.setString(3, budget.getUser_id());

            // 実行
            if (pStmt.executeUpdate() == 1) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
	
	//更新
	public boolean update(BudgetDto budget) {

	    Connection conn = null;
	    boolean result = false;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/kakemi_db?"
	            + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root",
	            "password"
	        );

	        String sql = "UPDATE budgets SET budget_amount = ?, goal_amount = ? "
	                + "WHERE user_id = ?";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        pStmt.setInt(1, budget.getBudget_amount());
            pStmt.setInt(2, budget.getGoal_amount());
            pStmt.setString(3, budget.getUser_id());

	        if (pStmt.executeUpdate() == 1) {
	            result = true;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
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
	
	//表示
	public List<BudgetDto> select(String userId) {

	    Connection conn = null;
	    List<BudgetDto> budgetList = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/kakemi_db?"
	            + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root",
	            "password"
	        );

	        String sql =
	            "SELECT budget_amount, goal_amount " +
	            "FROM budgets WHERE user_id = ?";

	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, userId);

	        ResultSet rs = pStmt.executeQuery();

	        while (rs.next()) {
	            BudgetDto budget = new BudgetDto();
	            budget.setBudget_amount(rs.getInt("budget_amount"));
	            budget.setGoal_amount(rs.getInt("goal_amount"));
	            budget.setUser_id(userId);

	            budgetList.add(budget);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return budgetList;
	}
}

