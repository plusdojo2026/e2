package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BudgetDao;
import dao.ExpensesDao;
import dao.IncomesDao;
import dto.BudgetDto;
import dto.ExpensesDto;
import dto.Incomes;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* 表示 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user_id");

		// もしもログインしていなかったらログインサーブレットにリダイレクトする

		if (userId == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// String userId = "test";

		// 予算、目標貯金額DAO生成
		BudgetDao budgetDao = new BudgetDao();
		// 収入DAO生成
		IncomesDao incomesDao = new IncomesDao();
		// 支出DAO生成
		ExpensesDao expensesDao = new ExpensesDao();

		// 予算・目標貯金額取得
		List<BudgetDto> budgetList = budgetDao.select(userId);
		// 収入取得
		List<Incomes> incomeList = incomesDao.selectByUser(userId);
		// 支出取得
		List<ExpensesDto> expenseList = expensesDao.selectByUser(userId);

		// 今月の年月を取得
		YearMonth thisMonth = YearMonth.now();

		// 今月の収入合計
		int incomesTotal = 0;
		for (Incomes income : incomeList) {
			// yyyy-MM-dd → LocalDate
			LocalDate date = LocalDate.parse(income.getCreated_at());
			// LocalDate → YearMonth
			YearMonth incomeMonth = YearMonth.from(date);
			// 今月かチェック
			if (incomeMonth.equals(thisMonth)) {
				incomesTotal += income.getAmount();
			}
		}

		// 今月の支出合計
		int expensesTotal = 0;
		for (ExpensesDto expense : expenseList) {
			// yyyy-MM-dd → LocalDate
			LocalDate date = LocalDate.parse(expense.getCreated_at());
			// LocalDate → YearMonth
			YearMonth expenseMonth = YearMonth.from(date);
			// 今月かチェック
			if (expenseMonth.equals(thisMonth)) {
				expensesTotal += expense.getAmount();
			}
		}

		// 予算、目標貯金額データ取り出す
		BudgetDto budget;
		if (budgetList.isEmpty()) {
			budget = new BudgetDto();
			budget.setBudget_amount(0);
			budget.setGoal_amount(0);
			budget.setUser_id(userId);
		} else {
			budget = budgetList.get(0);
		}

		// 残金計算
		int balance = budget.getBudget_amount() - expensesTotal;

		// 目標貯金額の進捗
		int goalAmount = budget.getGoal_amount();
		int savedAmount = incomesTotal - expensesTotal;

		int goalPercent = 0;
		if (goalAmount > 0) {
			goalPercent = Math.min(100, savedAmount * 100 / goalAmount);
		}

		// 予算消化率
		int budgetAmount = budget.getBudget_amount();
		int budgetPercent = 0;
		if (budgetAmount > 0) {
			int remain = budgetAmount - expensesTotal;
			budgetPercent = Math.max(0, Math.min(100, remain * 100 / budgetAmount));
		}

		// JSPへ渡す
		request.setAttribute("goalPercent", goalPercent);
		request.setAttribute("budgetPercent", budgetPercent);

		request.setAttribute("budget", budget);

		request.setAttribute("incomesTotal", incomesTotal);
		request.setAttribute("expensesTotal", expensesTotal);
		request.setAttribute("balance", balance);

		// ホームページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}

	/* 保存 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user_id");

		// もしもログインしていなかったらログインサーブレットにリダイレクトする

		if (userId == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// String userId = "test";

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
