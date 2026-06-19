package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ExpensesDto;

public class ExpensesDao {

	// ユーザーIDごとに表示させる
	public List<ExpensesDto> selectByUser(String user_id) {
		// 接続状態
		Connection conn = null;
		// 検索結果を入れるコレクション
		List<ExpensesDto> ExpensesList = new ArrayList<>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kakemi_db?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = " SELECT * FROM expenses WHERE user_id= ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, user_id);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

// 結果表をコレクションにコピーする
			while (rs.next()) {
				ExpensesDto expense = new ExpensesDto(rs.getInt("id"), rs.getString("user_id"), rs.getInt("amount"),
						rs.getString("emotion"), rs.getString("category"), rs.getString("situation"),
						rs.getString("item_name"), rs.getString("memo"), rs.getString("created_at"),
						rs.getString("tag"));
				ExpensesList.add(expense);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ExpensesList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			ExpensesList = null;
		} finally {
// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					ExpensesList = null;
				}
			}
		}
//結果を返す
		return ExpensesList;
	}

	// 日付で表示
	public List<ExpensesDto> selectByCalendar(String user_id, String date) {
		List<ExpensesDto> ExpensesList = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/kakemi_db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"root", "password");

			String sql = "SELECT * FROM expenses WHERE user_id = ? AND created_at LIKE ? ORDER BY created_at";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			pStmt.setString(2, date);

			ResultSet rs = pStmt.executeQuery();
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				ExpensesDto expense = new ExpensesDto(rs.getInt("id"), rs.getString("user_id"), rs.getInt("amount"),
						rs.getString("emotion"), rs.getString("category"), rs.getString("situation"),
						rs.getString("item_name"), rs.getString("memo"), rs.getString("created_at"),
						rs.getString("tag"));

				ExpensesList.add(expense);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ExpensesList;
	}

// 検索 (感情、カテゴリ、状況、日付、タグ）
	public List<ExpensesDto> selectByCalendar(ExpensesDto condition) {
		List<ExpensesDto> ExpensesList = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/kakemi_db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"root", "password");

			String sql = "SELECT * FROM expenses WHERE user_id = ?";
			List<Object> params = new ArrayList<>();

			params.add(condition.getUser_id());

			// 感情
			if (condition.getEmotion() != null) {
				sql += " AND emotion = ?";
				params.add(condition.getEmotion());
			}
			// カテゴリ
			if (condition.getCategory() != null && !condition.getCategory().isEmpty()) {
				sql += " AND category = ?";
				params.add(condition.getCategory());
			}
			// 状況
			if (condition.getSituation() != null) {
				sql += " AND situation = ?";
				params.add(condition.getSituation());
			}
			// 日付
			if (condition.getCreated_at() != null && !condition.getCreated_at().isEmpty()) {
				sql += " AND created_at = ?";
				params.add(condition.getCreated_at());
			}

			// タグ
			if (condition.getTag() != null && !condition.getTag().isEmpty()) {
				sql += " AND tag = ?";
				params.add(condition.getTag());

				PreparedStatement pStmt = conn.prepareStatement(sql);

				for (int i = 0; i < params.size(); i++) {
					pStmt.setObject(i + 1, params.get(i));
				}
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					ExpensesDto expense = new ExpensesDto(rs.getInt("id"), rs.getString("user_id"), rs.getInt("amount"),
							rs.getString("emotion"), rs.getString("category"), rs.getString("situation"),
							rs.getString("item_name"), rs.getString("memo"), rs.getString("created_at"),
							rs.getString("tag"));

					ExpensesList.add(expense);
				}

				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ExpensesList;
	}

//登録
	public boolean insert(ExpensesDto expense) {

		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kakemi_db?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			// SQL
			String sql = "INSERT  INTO expenses (amount,created_at,category,item_name,memo,emotion,situation,user_id)VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// パラメーター設定
			pStmt.setInt(1, expense.getAmount());
			pStmt.setString(2, expense.getEmotion());
			pStmt.setString(3, expense.getCategory());
			pStmt.setString(4, expense.getSituation());
			pStmt.setString(5, expense.getItem_name());
			pStmt.setString(6, expense.getMemo());
			pStmt.setString(7, expense.getCreated_at());
			pStmt.setString(8, expense.getTag());
			pStmt.setString(9, expense.getUser_id());
			// SQL文を実行して結果を取得する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			// データベースを切断
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
	public boolean update(ExpensesDto expense) {

		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kakemi_db?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL
			String sql = "UPDATE expenses SET  amount=?,created_at=?,category=?,item_name=?,memo=?,emotion=?,situation=?  + WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, expense.getAmount());
			pStmt.setString(2, expense.getEmotion());
			pStmt.setString(3, expense.getCategory());
			pStmt.setString(4, expense.getSituation());
			pStmt.setString(5, expense.getItem_name());
			pStmt.setString(6, expense.getMemo());
			pStmt.setString(7, expense.getCreated_at());
			pStmt.setString(8, expense.getTag());
			pStmt.setInt(9, expense.getId());

			// SQL文を実行して結果を取得する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			// データベースを切断
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

//削除
	public boolean delete(int id) {
		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kakemi_db?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL
			String sql = "DELETE FROM expenses WHERE id=?";
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
