package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategoryDao;
import dao.EmotionDao;
import dao.ExpensesDao;
import dao.SituationDao;
import dao.TagDao;
import dto.ExpensesDto;
import dto.KeyValueDto;

@WebServlet("/ListServlet2")
public class ListServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user_id");
		if (userId == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		// カテゴリセレクトの項目取得
		CategoryDao cDao = new CategoryDao();
		List<KeyValueDto> categoryList = cDao.selectEP();
		request.setAttribute("categoryList", categoryList);
		// 感情セレクトの項目取得
		EmotionDao eDao = new EmotionDao();
		List<KeyValueDto> emotionList = eDao.select();
		request.setAttribute("emotionList", emotionList);
		// タグセレクトの項目取得
		TagDao tDao = new TagDao();
		List<KeyValueDto> tagList = tDao.select();
		request.setAttribute("tagList", tagList);
		// 状況セレクトの項目取得
		SituationDao sDao = new SituationDao();
		List<KeyValueDto> situationList = sDao.select();
		request.setAttribute("situationList", situationList);

		ExpensesDao expensesDao = new ExpensesDao();
		// カレンダーから年月が取得できなかったら現在の年月を自動で取得
		String yearMonth = request.getParameter("month");
		if (yearMonth == null || yearMonth.isEmpty()) {
			yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
		}
		// 取得した年月をjspに表示
		request.setAttribute("yearMonth", yearMonth);
		// カレンダーから取得した年月で一覧表示
		List<ExpensesDto> expensesList = expensesDao.selectByCalendar(userId, yearMonth);
		// カテゴリを取得
		Map<String, List<ExpensesDto>> expenseCategoryMap = expensesList.stream()
				.collect(Collectors.groupingBy(ExpensesDto::getCategory));
		// 感情を取得
		Map<String, List<ExpensesDto>> expenseEmotionMap = expensesList.stream()
				.collect(Collectors.groupingBy(ExpensesDto::getEmotion));
		// タグを取得
		Map<String, List<ExpensesDto>> expenseTagMap = expensesList.stream()
				.collect(Collectors.groupingBy(ExpensesDto::getTag));
		// 状況を取得
		Map<String, List<ExpensesDto>> expenseSituationMap = expensesList.stream()
				.collect(Collectors.groupingBy(ExpensesDto::getSituation));
		// 収入合計の算出
		int expenseTotal = expensesList.stream().mapToInt(ExpensesDto::getAmount).sum();
		// カテゴリ収入合計の算出
		Map<String, Integer> expenseTotalCategoryMap = expenseCategoryMap.entrySet().stream()
				.collect(Collectors.toMap((entry) -> entry.getKey(),
						(entry) -> entry.getValue().stream().mapToInt(ExpensesDto::getAmount).sum()));
		// 感情収入合計の算出
		Map<String, Integer> expenseTotalEmotionMap = expenseEmotionMap.entrySet().stream()
				.collect(Collectors.toMap((entry) -> entry.getKey(),
						(entry) -> entry.getValue().stream().mapToInt(ExpensesDto::getAmount).sum()));
		// タグ収入合計の算出
		Map<String, Integer> expenseTotalTagMap = expenseTagMap.entrySet().stream()
				.collect(Collectors.toMap((entry) -> entry.getKey(),
						(entry) -> entry.getValue().stream().mapToInt(ExpensesDto::getAmount).sum()));
		// 状況収入合計の算出
		Map<String, Integer> expenseTotalSituationMap = expenseSituationMap.entrySet().stream()
				.collect(Collectors.toMap((entry) -> entry.getKey(),
						(entry) -> entry.getValue().stream().mapToInt(ExpensesDto::getAmount).sum()));
		// jspに表示
		// 総収入合計と全データ
		request.setAttribute("expenseTotal", expenseTotal);
		request.setAttribute("expensesList", expensesList);
		// カテゴリごとの総収入合計金額とカテゴリごとのデータ
		request.setAttribute("expenseTotalCategoryMap", expenseTotalCategoryMap);
		request.setAttribute("expenseCategoryMap", expenseCategoryMap);
		// 感情ごとの総収入合計金額とカテゴリごとのデータ
		request.setAttribute("expenseTotalEmotionMap", expenseTotalEmotionMap);
		request.setAttribute("expenseEmotionMap", expenseEmotionMap);
		// タグごとの総収入合計金額とカテゴリごとのデータ
		request.setAttribute("expenseTotalTagMap", expenseTotalTagMap);
		request.setAttribute("expenseTagMap", expenseTagMap);
		// 状況ごとの総収入合計金額とカテゴリごとのデータ
		request.setAttribute("expenseTotalSituationMap", expenseTotalSituationMap);
		request.setAttribute("expenseSituationMap", expenseSituationMap);

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/list2.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user_id");
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}
		// 現在の年月を取得
		String yearMonth = request.getParameter("month");
		if (yearMonth == null) {
			yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
		}
		String amountString = request.getParameter("amount");
		Integer amount;
		if (amountString != null && !amountString.isEmpty()) {
			try {
				amount = Integer.parseInt(amountString);
			} catch (NumberFormatException e) {
				amount = null;
			}
		}
		String submit = request.getParameter("submit");
		if ("delete".equals(submit)) {
			String[] deleteIds = request.getParameterValues("deleteIds");
			if (deleteIds != null) {
				ExpensesDao dao = new ExpensesDao();
				for (String id : deleteIds) {
					dao.delete(Integer.parseInt(id));
				}
			}
			response.sendRedirect("ListServlet2");
			return;
		} else if ("edit".equals(submit)) {
			String[] ids = request.getParameterValues("id");
			String[] dates = request.getParameterValues("created_at");
			String[] categories = request.getParameterValues("category");
			String[] emotions = request.getParameterValues("emotion");
			String[] tag = request.getParameterValues("tag");
			String[] situation = request.getParameterValues("situation");
			String[] amounts = request.getParameterValues("amount");
			ExpensesDao dao = new ExpensesDao();
			for (int i = 0; i < ids.length; i++) {
				dao.update(new ExpensesDto(Integer.parseInt(ids[i]), userId, dates[i], Integer.parseInt(amounts[i]),
						emotions[i], categories[i], tag[i], situation[i]));
			}
			response.sendRedirect("ListServlet2");
			return;
		}
	}
}
