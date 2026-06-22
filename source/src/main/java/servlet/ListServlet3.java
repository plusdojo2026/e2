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
import dao.PatienceDao;
import dao.SituationDao;
import dto.KeyValueDto;
import dto.PatienceDto;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/ListServlet3")
public class ListServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする

		System.out.println("★★★★ ListServlet開始 ★★★★");

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


		// 状況の項目取得
		SituationDao sDao = new SituationDao();
		List<KeyValueDto> situationList = sDao.select();
		request.setAttribute("situationList", situationList);

		PatienceDao patienceDao = new PatienceDao();

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
		List<PatienceDto> patienceList = patienceDao.selectByCalendar(userId, yearMonth);

		// カテゴリを取得
		Map<String, List<PatienceDto>> patienceCategoryMap = patienceList.stream()
				.collect(Collectors.groupingBy(PatienceDto::getCategory));

		// 感情を取得
		Map<String, List<PatienceDto>> patienceEmotionMap = patienceList.stream()
				.collect(Collectors.groupingBy(PatienceDto::getEmotion));

		// 状況を取得
		Map<String, List<PatienceDto>> patienceSituationMap = patienceList.stream()
				.collect(Collectors.groupingBy(PatienceDto::getSituation));

		// 収入合計の算出
		int patienceTotal = patienceList.stream().mapToInt(PatienceDto::getAmount).sum();

		// カテゴリ収入合計の算出
		Map<String, Integer> patienceTotalCategoryMap = patienceCategoryMap.entrySet().stream()
				.collect(Collectors.toMap((entry) -> entry.getKey(),
						(entry) -> entry.getValue().stream().mapToInt(PatienceDto::getAmount).sum()));

		// 感情収入合計の算出
		Map<String, Integer> patienceTotalEmotionMap = patienceEmotionMap.entrySet().stream()
				.collect(Collectors.toMap((entry) -> entry.getKey(),
						(entry) -> entry.getValue().stream().mapToInt(PatienceDto::getAmount).sum()));

		// 状況収入合計の算出
		Map<String, Integer> patienceTotalSituationMap = patienceSituationMap.entrySet().stream()
				.collect(Collectors.toMap((entry) -> entry.getKey(),
						(entry) -> entry.getValue().stream().mapToInt(PatienceDto::getAmount).sum()));

		// jspに表示
		request.setAttribute("patienceTotal", patienceTotal);

		request.setAttribute("patienceTotalCategoryMap", patienceTotalCategoryMap);
		request.setAttribute("patienceCategoryMap", patienceCategoryMap);

		request.setAttribute("patienceTotalEmotionMap", patienceTotalEmotionMap);
		request.setAttribute("patienceEmotionMap", patienceEmotionMap);

		request.setAttribute("patienceTotalSituationMap", patienceTotalSituationMap);
		request.setAttribute("patienceSituationMap", patienceSituationMap);

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		request.setAttribute("patienceList", patienceList);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/list3.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		
		System.out.println("submit=[" + request.getParameter("submit") + "]");
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
				PatienceDao dao = new PatienceDao();

				for (String id : deleteIds) {
					dao.delete(Integer.parseInt(id));
				}
			}

			response.sendRedirect("ListServlet3");
			return;
		}

		else if ("edit".equals(submit)) {

			String[] ids = request.getParameterValues("id");
			String[] dates = request.getParameterValues("created_at");
			String[] categories = request.getParameterValues("category");
			String[] emotions = request.getParameterValues("emotion");
			String[] tag = request.getParameterValues("tag");
			String[] situation = request.getParameterValues("situation");
			String[] amounts = request.getParameterValues("amount");

			PatienceDao dao = new PatienceDao();

			for (int i = 0; i < ids.length; i++) {
				dao.update(new PatienceDto( Integer.parseInt(ids[i]), userId, dates[i], Integer.parseInt(amounts[i]),
						emotions[i], categories[i], situation[i]));
			}
			
			response.sendRedirect("ListServlet3");
			return;
		}
	}

}
