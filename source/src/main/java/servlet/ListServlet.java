package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IncomeDao;
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

		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// ユーザ情報取得用
		/*
		 * LoginUser loginUser = (LoginUser) session.getAttribute("id"); String userId =
		 * loginUser.getId(); request.setAttribute("userId", userId);
		 */

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		IncomesDao incomesDao = new IncomesDao();
		// ExpensesDao expensesDao = new ExpensesDao();
		// PatienceDao patienceDao = new PatienceDao();

		List<Incomes> incomeList = incomesDao.selectByUser(userId);
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
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		IncomeDao incomeDao = new IncomeDao();

		List<Incomes> incomeList = incomeDao.select(userId);

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

		Incomes condition = new Incomes(userId, hiduke, amount, emotion, category);

		// 検索処理を行う
		IncomesDao iDao = new IncomesDao();
		List<Incomes> incomeList = iDao.selectByCondition(condition);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("incomeList", incomeList);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		dispatcher.forward(request, response);

	}

}
