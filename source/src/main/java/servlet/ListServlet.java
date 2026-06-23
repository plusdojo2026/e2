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
import dao.IncomesDao;
import dto.Incomes;
import dto.KeyValueDto;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
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
		List<KeyValueDto> categoryList = cDao.select();
		request.setAttribute("categoryList", categoryList);
		// 感情セレクトの項目取得
		EmotionDao eDao = new EmotionDao();
		List<KeyValueDto> emotionList = eDao.select();
		request.setAttribute("emotionList", emotionList);

		IncomesDao incomesDao = new IncomesDao();
		// カレンダーから年月が取得できなかったら現在の年月を自動で取得
		String yearMonth = request.getParameter("month");
		if (yearMonth == null || yearMonth.isEmpty()) {
			yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
		}
		// 取得した年月をjspに表示
		request.setAttribute("yearMonth", yearMonth);
		// デバック用
		System.out.println("userId = " + userId);
		System.out.println("yearMonth = " + yearMonth);
		// カレンダーから取得した年月で一覧表示
		List<Incomes> incomeList = incomesDao.selectByCalendar(userId, yearMonth);
		// カテゴリを取得
		Map<String, List<Incomes>> incomeCategoryMap = incomeList.stream()
				.collect(Collectors.groupingBy(Incomes::getCategory));
		// 感情を取得
		Map<String, List<Incomes>> incomeEmotionMap = incomeList.stream()
				.collect(Collectors.groupingBy(Incomes::getEmotion));
		// 収入合計の算出
		int incomeTotal = incomeList.stream().mapToInt(Incomes::getAmount).sum();
		// カテゴリ収入合計の算出
		Map<String, Integer> incomeTotalCategoryMap = incomeCategoryMap.entrySet().stream().collect(Collectors.toMap(
				(entry) -> entry.getKey(), (entry) -> entry.getValue().stream().mapToInt(Incomes::getAmount).sum()));
		// 感情収入合計の算出
		Map<String, Integer> incomeTotalEmotionMap = incomeEmotionMap.entrySet().stream().collect(Collectors.toMap(
				(entry) -> entry.getKey(), (entry) -> entry.getValue().stream().mapToInt(Incomes::getAmount).sum()));
		// jspに表示
		// 総収入合計と全データ
		request.setAttribute("incomeTotal", incomeTotal);
		request.setAttribute("incomeList", incomeList);
		// カテゴリごとの総収入合計金額とカテゴリごとのデータ
		request.setAttribute("incomeTotalCategoryMap", incomeTotalCategoryMap);
		request.setAttribute("incomeCategoryMap", incomeCategoryMap);
		// 感情ごとの総収入合計金額とカテゴリごとのデータ
		request.setAttribute("incomeTotalEmotionMap", incomeTotalEmotionMap);
		request.setAttribute("incomeEmotionMap", incomeEmotionMap);

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
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
		// カレンダー/現在の年月を取得
		String yearMonth = request.getParameter("month");
		if (yearMonth == null) {
			yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
		}
		// 値段変換用
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
				IncomesDao dao = new IncomesDao();
				for (String id : deleteIds) {
					dao.delete(Integer.parseInt(id));
				}
			}
			response.sendRedirect("ListServlet");
			return;
		} else if ("edit".equals(submit)) {
			String[] ids = request.getParameterValues("id");
			String[] dates = request.getParameterValues("created_at");
			String[] categories = request.getParameterValues("category");
			String[] emotions = request.getParameterValues("emotion");
			String[] amounts = request.getParameterValues("amount");
			if (ids == null || dates == null || categories == null || emotions == null || amounts == null) {
				System.out.println("送信データ不足");
				return;
			}
			IncomesDao dao = new IncomesDao();
			for (int i = 0; i < ids.length; i++) {
				dao.update(new Incomes(Integer.parseInt(ids[i]), userId, dates[i], Integer.parseInt(amounts[i]),
						emotions[i], categories[i]));
			}
			response.sendRedirect("ListServlet");
			return;
		}
	}

}
