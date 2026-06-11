package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDto;

public class UserDao {
	// 引数で指定されたidpwでログイン成功ならtrueを返す
		public boolean isLoginOK(UserDto user) {
			Connection conn = null;
			boolean loginResult = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kakemi_db?"
						+ "characterEncoding=UTF8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");
				
				// SELECT文を準備する
				String sql = "SELECT count(*) FROM users WHERE id=? AND pw=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user.getId());
				pStmt.setString(2, user.getPw());
				
				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
				rs.next();
				if (rs.getInt("count(*)") == 1) {
					loginResult = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				loginResult = false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				loginResult = false;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						loginResult = false;
					}
				}
			}

			// 結果を返す
			return loginResult;
		}
		
		public boolean insert(UserDto users) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kakemi_db?"
						+ "characterEncoding=UTF8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");
				
				// SQL文を準備する
				String sql = "INSERT INTO usrs (id,pw,name) "
	           + "VALUES (?,?,?)";

				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				if (users.getId() != null) {
				    pStmt.setString(1, users.getId());
				} else {
				    pStmt.setString(1, "");
				}

				if (users.getPw() != null) {
				    pStmt.setString(2, users.getPw());
				} else {
				    pStmt.setString(2, "");
				}

				if (users.getName() != null) {
				    pStmt.setString(3, users.getName());
				} else {
				    pStmt.setString(3, "");
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
	}

