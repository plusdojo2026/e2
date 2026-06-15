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

	public List<ExpensesDto> select() {
		//接続状態
		Connection conn = null;
		//検索結果を入れるコレクション
		List<ExpensesDto> cardList = new ArrayList<ExpensesDto>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = " SELECT * FROM contact";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
		//		ExpensesDto expense = new ExpensesDto (rs.getString("phone"), rs.getString("email"),
			//			rs.getString("name"), "",rs.getString("remarks"));
		//		cardList.add(contact);
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

	//public boolean insert(Contact contact) {
		Connection conn = null;
		boolean result = false;

		
		{
			// INSERT文を準備する
		//	String sql = "INSERT INTO contact VALUES (0,?,?,?,?,?)";
		//	PreparedStatement pStmt = conn.prepareStatement(sql);
		//	pStmt.setString(1, contact.getPhone());
		//	pStmt.setString(2, contact.getEmail());
		//	pStmt.setString(3, contact.getName());
		//	pStmt.setString(5, contact.getCategory());
		//	pStmt.setString(4, contact.getRemarks());
		//	System.out.println("step2");
		//	System.out.println(sql);
			// SELECT文を実行し、結果表を取得する
		//	if (pStmt.executeUpdate() == 1) {
				result = true;
		//	}
		
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					result = false;
				}
			}
	//	}

		// 結果を返す
	//	return result;
	}
}