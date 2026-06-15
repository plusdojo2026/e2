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

import dao.BudgetDao;
import dto.BudgetDto;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*表示*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		/*
		 * HttpSession session = request.getSession(); if (session.getAttribute("id") ==
		 * null) { response.sendRedirect("LoginServlet"); return; }
		 */

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user_id");

		// DAO生成
		BudgetDao budgetDao = new BudgetDao();

		// 予算・目標貯金額取得
		List<BudgetDto> budgetList = budgetDao.select(userId);

		// データ取り出す
		BudgetDto budget;
		if (budgetList.isEmpty()) {
			budget = new BudgetDto();
			budget.setBudget_amount(0);
			budget.setGoal_amount(0);
			budget.setUser_id(userId);
		} else {
			budget = budgetList.get(0);
		}

		// JSPへ渡す
		request.setAttribute("budget", budget);

		// ホームページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}

	/* 保存 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		/*HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}*/

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user_id");
		
		// JSPのフォームから送信された「予算」と「目標貯金額」を取得
		int budgetAmount = Integer.parseInt(request.getParameter("budget_amount"));
		int goalAmount = Integer.parseInt(request.getParameter("goal_amount"));
		
		// 取得した値のセット
		BudgetDto budget = new BudgetDto();
		budget.setUser_id(userId);
		budget.setBudget_amount(budgetAmount);
		budget.setGoal_amount(goalAmount);
		
		// DAO生成
		BudgetDao budgetDao = new BudgetDao();
		// budgetsテーブルを更新
		budgetDao.update(budget);

		// 再表示
		response.sendRedirect("HomeServlet");
	}
}
