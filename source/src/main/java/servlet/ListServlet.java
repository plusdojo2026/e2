package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import dao.EmotionDao;
import dao.IncomesDao;
import dto.Incomes;
import dto.KeyValueDto;

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
		 * HttpSession session = request.getSession(); if (session.getAttribute("id") ==
		 * null) { response.sendRedirect("LoginServlet"); return; }
		 */

		// ユーザ情報取得用
		/*
		 * LoginUser loginUser = (LoginUser) session.getAttribute("id"); String userId =
		 * loginUser.getId(); request.setAttribute("userId", userId);
		 */

		// カテゴリセレクトの項目取得
		CategoryDao cDao = new CategoryDao();
		List<KeyValueDto> categoryList = cDao.selectEP();

		request.setAttribute("categoryList", categoryList);

		// 感情セレクトの項目取得
		EmotionDao eDao = new EmotionDao();
		List<KeyValueDto> emotionList = eDao.select();

		request.setAttribute("emotionList", emotionList);

		IncomesDao incomesDao = new IncomesDao();

		// 現在の年月を自動で取得
		String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
		// 取得した年月をjspに表示
		request.setAttribute("yearMonth", yearMonth);

		// デバック用
		System.out.println("userId = " + userId);
		System.out.println("yearMonth = " + yearMonth);

		// カレンダーから取得した年月で一覧表示
		List<Incomes> incomeList = incomesDao.selectByCalendar(userId, yearMonth);

<<<<<<< Updated upstream
		// カテゴリ単位の合計算出
		Map<String, List<Incomes>> incomeCategoryMap = incomeList.stream()
				.collect(Collectors.groupingBy(Incomes::getCategory, LinkedHashMap::new, Collectors.toList()));

		// カテゴリ単位の合計算出
=======
		// カテゴリを取得
		Map<String, List<Incomes>> incomeCategoryMap = incomeList.stream()
				.collect(Collectors.groupingBy(Incomes::getCategory, LinkedHashMap::new, Collectors.toList()));

		// 感情を取得
>>>>>>> Stashed changes
		Map<String, List<Incomes>> incomeEmotionMap = incomeList.stream()
				.collect(Collectors.groupingBy(Incomes::getEmotion, LinkedHashMap::new, Collectors.toList()));

		// 収入合計の算出
<<<<<<< Updated upstream
		Map<String, Integer> incomeTotalMap = new LinkedHashMap<>();

		for (Map.Entry<String, List<Incomes>> entry : incomeCategoryMap.entrySet()) {

			int total = 0;

			for (Incomes i : entry.getValue()) {
				if (i.getAmount() != null) {
					total += i.getAmount();
				}
			}

			incomeTotalMap.put(entry.getKey(), total);
		}

=======
		Map<String, Integer> incomeTotalMap = incomeList.stream().collect(Collectors.groupingBy(Incomes::getCategory,
				LinkedHashMap::new, Collectors.summingInt(i -> i.getAmount() == null ? 0 : i.getAmount())));

		// カテゴリ別の合計収入算出
		List<Incomes> categoryIncomeList = incomeList;
		String selectedCategory = request.getParameter("selectedCategory");

		if (selectedCategory != null && !selectedCategory.isEmpty()) {

			categoryIncomeList = incomeList.stream().filter(i -> selectedCategory.equals(i.getCategory()))
					.collect(Collectors.toList());
		}

		int selectedCategoryTotal = 0;

		for (Incomes i : categoryIncomeList) {
			if (i.getAmount() != null) {
				selectedCategoryTotal += i.getAmount();
			}
		}

		// 感情別の合計収入算出
		List<Incomes> emotionIncomeList = incomeList;
		String selectedEmotion = request.getParameter("selectedEmotion");

		if (selectedEmotion != null && !selectedEmotion.isEmpty()) {

			emotionIncomeList = incomeList.stream().filter(i -> selectedEmotion.equals(i.getEmotion()))
					.collect(Collectors.toList());
		}

		int selectedEmotionTotal = 0;

		for (Incomes i : emotionIncomeList) {
			if (i.getAmount() != null) {
				selectedEmotionTotal += i.getAmount();
			}
		}
>>>>>>> Stashed changes
		// jspに表示
		request.setAttribute("incomeTotalMap", incomeTotalMap);
		request.setAttribute("incomeCategoryMap", incomeCategoryMap);
<<<<<<< Updated upstream
		request.setAttribute("incomeTotalMap", incomeTotalMap);
=======
		request.setAttribute("incomeEmotionMap", incomeEmotionMap);

		request.setAttribute("selectedCategoryTotal", selectedCategoryTotal);
		request.setAttribute("selectedEmotionTotal", selectedEmotionTotal);

		request.setAttribute("categoryIncomeList", categoryIncomeList);
		request.setAttribute("emotionIncomeList", emotionIncomeList);
		request.setAttribute("selectedCategory", selectedCategory);
		request.setAttribute("selectedEmotion", selectedEmotion);
>>>>>>> Stashed changes

		// デバック用
		System.out.println("取得件数 = " + incomeList.size());

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		// IncomesDao incomesDao = new IncomesDao();
		// ExpensesDao expensesDao = new ExpensesDao();
		// PatienceDao patienceDao = new PatienceDao();

		// List<Incomes> incomeList = incomesDao.selectByCalendar(userId,yearMonth);
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
		 * HttpSession session = request.getSession(); if (session.getAttribute("id") ==
		 * null) { response.sendRedirect("LoginServlet"); return; }
		 */

		 System.out.println("★★★★ doPost開始 ★★★★");

		IncomesDao incomesDao = new IncomesDao();

		String yearMonth = request.getParameter("month");

		if (yearMonth == null) {
			yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
		}

		List<Incomes> incomeList = incomesDao.selectByCalendar(userId, yearMonth);

		request.setAttribute("incomeList", incomeList);

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		String hiduke = request.getParameter("hiduke");
		String category = request.getParameter("category");
		String emotion = request.getParameter("emotion");

		String amountString = request.getParameter("amount");
		Integer amount = null;

		// 数字以外が入力されたらnullに変更
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

		hiduke = request.getParameter("hiduke");
		category = request.getParameter("category");
		emotion = request.getParameter("emotion");

		// 編集削除ボタン押した時
		String submit = request.getParameter("submit");

		// 収入削除処理
		if ("削除".equals(submit)) {

			String[] deleteIds = request.getParameterValues("deleteIds");

			if (deleteIds != null) {

				IncomesDao dao = new IncomesDao();

				for (String id : deleteIds) {
					dao.delete(Integer.parseInt(id));
				}
			}

			response.sendRedirect("ListServlet");
			return;
		}

		// 収入編集処理
		if ("編集".equals(submit)) {

			String[] ids = request.getParameterValues("id");
			String[] dates = request.getParameterValues("created_at");
			String[] categories = request.getParameterValues("category");
			String[] emotions = request.getParameterValues("emotion");
			String[] amounts = request.getParameterValues("amount");

			IncomesDao dao = new IncomesDao();

			// デバック用
			System.out.println("編集ボタン押下");
			System.out.println("件数=" + ids.length);
			for (String s : ids) {
				System.out.println("id=" + s);

				for (int i = 0; i < ids.length; i++) {

					Incomes income = new Incomes(Integer.parseInt(ids[i]), userId, dates[i],
							Integer.parseInt(amounts[i]), emotions[i], categories[i]);

					dao.update(income);
				}

				response.sendRedirect("ListServlet");
				return;
			}
		}

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("incomeList", incometotalList);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		dispatcher.forward(request, response);
	}
}
