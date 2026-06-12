package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Incomes;

public class IncomesDao {
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
	public List<Incomes> select(Incomes card) {
		Connection conn = null;
		List<Incomes> cardList = new ArrayList<Incomes>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT created_at, amount, emotion_id, category_id, situation_id, "
					+ "item_name, memo, tag_id, "
					+ "FROM Incomes "
					+ "WHERE created_at LIKE ? AND amount LIKE ? AND emotion_id LIKE ? "
					+ "AND category_id LIKE ? AND situation_id LIKE ? AND item_name LIKE ? AND memo LIKE ? "
					+ "AND tag_id LIKE ?"
					+ "ORDER BY number";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getCreated_at() != null) {
				pStmt.setString(1, "%" + card.getCreated_at() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			if (card.getAmount() != null) {
				pStmt.setString(2, "%" + card.getAmount() + "%");
			} else {
				pStmt.setString(2, "%");
			}
			if (card.getEmotion_id() != null) {
				pStmt.setString(3, "%" + card.getEmotion_id() + "%");
			} else {
				pStmt.setString(3, "%");
			}
			if (card.getCategory_id() != null) {
				pStmt.setString(4, "%" + card.getCategory_id() + "%");
			} else {
				pStmt.setString(4, "%");
			}
			if (card.getSituation_id() != null) {
				pStmt.setString(5, "%" + card.getSituation_id() + "%");
			} else {
				pStmt.setString(5, "%");
			}
			if (card.getItem_name() != null) {
				pStmt.setString(6, "%" + card.getItem_name() + "%");
			} else {
				pStmt.setString(6, "%");
			}
			if (card.getMemo() != null) {
				pStmt.setString(7, "%" + card.getMemo() + "%");
			} else {
				pStmt.setString(7, "%");
			}
			if (card.getTag_id() != null) {
				pStmt.setString(8, "%" + card.getTag_id() + "%");
			} else {
				pStmt.setString(8, "%");
			}
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Incomes Incomes = new Incomes(
						rs.getInt("number"), 
						rs.getString("created_at"),
						rs.getInt("amount"),
						rs.getInt("emotion_id"),
						rs.getInt("category_id"),
						rs.getInt("situation_id"),
						rs.getString("item_name"),
						rs.getString("memo"),
						rs.getInt("tag_id")
						);
				cardList.add(Incomes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;
	}

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Incomes card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO Bc VALUES (0, ?, ?, ?, ?, ?, ?, ?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getCreated_at() != null) {
				pStmt.setString(1, "%" + card.getCreated_at() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			if (card.getAmount() != null) {
				pStmt.setString(2, "%" + card.getAmount() + "%");
			} else {
				pStmt.setString(2, "%");
			}
			if (card.getEmotion_id() != null) {
				pStmt.setString(3, "%" + card.getEmotion_id() + "%");
			} else {
				pStmt.setString(3, "%");
			}
			if (card.getCategory_id() != null) {
				pStmt.setString(4, "%" + card.getCategory_id() + "%");
			} else {
				pStmt.setString(4, "%");
			}
			if (card.getSituation_id() != null) {
				pStmt.setString(5, "%" + card.getSituation_id() + "%");
			} else {
				pStmt.setString(5, "%");
			}
			if (card.getItem_name() != null) {
				pStmt.setString(6, "%" + card.getItem_name() + "%");
			} else {
				pStmt.setString(6, "%");
			}
			if (card.getMemo() != null) {
				pStmt.setString(7, "%" + card.getMemo() + "%");
			} else {
				pStmt.setString(7, "%");
			}
			if (card.getTag_id() != null) {
				pStmt.setString(8, "%" + card.getTag_id() + "%");
			} else {
				pStmt.setString(8, "%");
			}

			// SQL文を実行する
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

		// 結果を返す
		return result;
	}

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Incomes card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "UPDATE Incomes SET created_at=?, amount=?, emotion_id=?, "
					+ "category_id=?, situation_id=?, item_name=?, memo=?, tag_id=?, "
					+ "WHERE number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getCreated_at() != null) {
				pStmt.setString(1, "%" + card.getCreated_at() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			if (card.getAmount() != null) {
				pStmt.setString(2, "%" + card.getAmount() + "%");
			} else {
				pStmt.setString(2, "%");
			}
			if (card.getEmotion_id() != null) {
				pStmt.setString(3, "%" + card.getEmotion_id() + "%");
			} else {
				pStmt.setString(3, "%");
			}
			if (card.getCategory_id() != null) {
				pStmt.setString(4, "%" + card.getCategory_id() + "%");
			} else {
				pStmt.setString(4, "%");
			}
			if (card.getSituation_id() != null) {
				pStmt.setString(5, "%" + card.getSituation_id() + "%");
			} else {
				pStmt.setString(5, "%");
			}
			if (card.getItem_name() != null) {
				pStmt.setString(6, "%" + card.getItem_name() + "%");
			} else {
				pStmt.setString(6, "%");
			}
			if (card.getMemo() != null) {
				pStmt.setString(7, "%" + card.getMemo() + "%");
			} else {
				pStmt.setString(7, "%");
			}
			if (card.getTag_id() != null) {
				pStmt.setString(8, "%" + card.getTag_id() + "%");
			} else {
				pStmt.setString(8, "%");
			}

			// SQL文を実行する
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

		// 結果を返す
		return result;
	}

	// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す
	public boolean delete(Incomes card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "DELETE FROM Incomes WHERE number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, card.getNumber());

			// SQL文を実行する
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

		// 結果を返す
		return result;
	}
}
