package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PatienceDto;

public class PatienceDao {

	// 登録
	public boolean insert(PatienceDto patience) {

		boolean result = false;
		Connection conn = null;
		try {
			// JDBCドライバ読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kakemi_db?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			// SQL
			String sql = "INSERT  INTO patience (amount,created_at,category,item_name,memo,emotion,situation,user_id)VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// パラメーター設定
			pStmt.setInt(1, patience.getAmount());
			pStmt.setString(2, patience.getCreated_at());
			pStmt.setString(3, patience.getCategory());
			pStmt.setString(4, patience.getItem_name());
			pStmt.setString(5, patience.getMemo());
			pStmt.setString(6, patience.getEmotion());
			pStmt.setString(7, patience.getSituation());
			pStmt.setString(8, patience.getUser_id());

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

	// 検索
	public List<PatienceDto> selectByCondition(PatienceDto condition) {
		List<PatienceDto> patienceList = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/kakemi_db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"root", "password");

			String sql = "SELECT * FROM patience WHERE user_id = ?";
			List<Object> params = new ArrayList<>();

			params.add(condition.getUser_id());

			// 金額
			if (condition.getAmount() != null && !condition.getCreated_at().isEmpty()) {
				sql += " AND amount = ?";
				params.add(condition.getAmount());
			}

			// 日付
			if (condition.getCreated_at() != null && !condition.getCreated_at().isEmpty()) {
				sql += " AND created_at = ?";
				params.add(condition.getCreated_at());
			}

			// カテゴリ
			if (condition.getCategory() != null && !condition.getCategory().isEmpty()) {
				sql += " AND category = ?";
				params.add(condition.getCategory());
			}
			// 感情
			if (condition.getEmotion() != null) {
				sql += " AND emotion = ?";
				params.add(condition.getEmotion());
			}
			// 状況
			if (condition.getSituation() != null) {
				sql += " AND situation = ?";
				params.add(condition.getSituation());
			}

			PreparedStatement pStmt = conn.prepareStatement(sql);

			for (int i = 0; i < params.size(); i++) {
				pStmt.setObject(i + 1, params.get(i));
			}
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				PatienceDto patience = new PatienceDto(rs.getInt("id"), rs.getString("user_id"), rs.getInt("amount"),
						rs.getString("emotion"), rs.getString("category"), rs.getString("situation"),
						rs.getString("item_name"), rs.getString("memo"), rs.getString("created_at"));

				patienceList.add(patience);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return patienceList;
	}

	// 更新
	public boolean update(PatienceDto patience) {

		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/kakemi_db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"root", "password");

			String sql = "UPDATE patience SET amount=?, created_at=?, category=?, item_name=?, memo=?, emotion=?, situation=?, user_id=? "
					+ "WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, patience.getAmount());
			pStmt.setString(2, patience.getCreated_at());
			pStmt.setString(3, patience.getCategory());
			pStmt.setString(4, patience.getItem_name());
			pStmt.setString(5, patience.getMemo());
			pStmt.setString(6, patience.getEmotion());
			pStmt.setString(7, patience.getSituation());
			pStmt.setString(8, patience.getUser_id());
			pStmt.setInt(9, patience.getId());

			// 1件更新できたら
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

	// 削除
	public boolean delete(int id) {
		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/kakemi_db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"root", "password");

			String sql = "DELETE FROM patience WHERE id=?";
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

	// 我慢記録一覧表示
	public List<PatienceDto> select(String user_id) {
		List<PatienceDto> patienceList = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/kakemi_db?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"root", "password");

			String sql = "SELECT * FROM Patience WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				PatienceDto patience = new PatienceDto(rs.getInt("id"), rs.getString("user_id"), rs.getInt("amount"),
						rs.getString("emotion"), rs.getString("category"), rs.getString("situation"),
						rs.getString("item_name"), rs.getString("memo"), rs.getString("created_at"));

				patienceList.add(patience);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return patienceList;
	}

	public List<PatienceDto> selectByCalendar(String user_id, String date) {
		List<PatienceDto> patienceList = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/kakemi_db" + "?characterEncoding=utf8" + "&useSSL=false"
									+ "&serverTimezone=Asia/Tokyo" + "&allowPublicKeyRetrieval=true",
							"root", "password");

			String sql = "SELECT * FROM patience " + "WHERE user_id = ? " + "AND created_at LIKE ? "
					+ "ORDER BY created_at";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			pStmt.setString(2, date + "%");

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				PatienceDto patience = new PatienceDto(
				    rs.getString("user_id"),
				    rs.getInt("amount"),
				    rs.getString("created_at"),
				    rs.getString("category"),
				    rs.getString("emotion"),
				    rs.getString("situation")
				);

				patience.setId(rs.getInt("id"));
				patienceList.add(patience);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return patienceList;
	}

}
