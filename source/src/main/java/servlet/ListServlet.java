package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IncomesDao;
import dto.Incomes;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	// テスト用ユーザID定義
	String userId = "test";

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		
		System.out.println("★★★★ ListServlet開始 ★★★★");

		/*
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}*/

		// ユーザ情報取得用
		/*
		 * LoginUser loginUser = (LoginUser) session.getAttribute("id"); String userId =
		 * loginUser.getId(); request.setAttribute("userId", userId);
		 */
		
		/*カレンダーでの年月を取得
		String yearMonth = request.getParameter("month");*/
		
		IncomesDao incomesDao = new IncomesDao();
		//現在の年月を自動で取得
		String yearMonth =
			    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
		
		System.out.println("userId = " + userId);
		System.out.println("yearMonth = " + yearMonth);

		List<Incomes> incomeList =
		    incomesDao.selectByCalendar(userId, yearMonth);

		System.out.println("取得件数 = " + incomeList.size());
		
		

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		//IncomesDao incomesDao = new IncomesDao();
		// ExpensesDao expensesDao = new ExpensesDao();
		// PatienceDao patienceDao = new PatienceDao();

		//List<Incomes> incomeList = incomesDao.selectByCalendar(userId,yearMonth);
		// List<Expenses> expenseList = expensesDao.select(userId);
		// List<Patience> patienceList = patienceDao.select(userId);

		request.setAttribute("incomeList", incomeList);

		/*
		 * request.setAttribute( "expenseList", expenseList);
		 * 
		 * request.setAttribute( "patienceList", patienceList);
		 */

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");

		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		
		/*
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}*/

		IncomesDao incomesDao = new IncomesDao();

		List<Incomes> incomeList = incomesDao.selectByUser(userId);

		request.setAttribute("incomeList", incomeList);

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		String hiduke = request.getParameter("hiduke");
		String category = request.getParameter("category");
		String emotion = request.getParameter("emotion");

		String amountString = request.getParameter("amount");
		Integer amount = null;

		//数字以外が入力されたらnullに変更
		if (amountString != null && !amountString.isEmpty()) {
			try {
				amount = Integer.parseInt(amountString);
			} catch (NumberFormatException e) {
				amount = null;
			}
		}

		
		// 検索処理を行う
		IncomesDao iDao = new IncomesDao();
		List<Incomes> incometotalList = iDao.selectByCondition(new Incomes(userId, hiduke, amount, emotion, category));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("incomeList", incometotalList);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		dispatcher.forward(request, response);

	}

}
