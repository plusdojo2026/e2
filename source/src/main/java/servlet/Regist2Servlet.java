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

import dao.CategoryDao;
import dao.EmotionDao;
import dao.IncomesDao;
import dto.Incomes;
import dto.KeyValueDto;

@WebServlet("/Regist2Servlet")
public class Regist2Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 画面表示（GET）
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // ログインチェック
        if (session.getAttribute("user_id") == null) {
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
		

		RequestDispatcher dispatcher =
        request.getRequestDispatcher("/WEB-INF/jsp/regist2.jsp");
		dispatcher.forward(request, response);
}

    // 登録処理（POST）
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // ログインチェック
        String user_id = (String) session.getAttribute("user_id");
        if (user_id == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        // リクエスト取得
        request.setCharacterEncoding("UTF-8");

        String created_at = request.getParameter("created_at");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String emotion = request.getParameter("emotion");
        String category = request.getParameter("category");
       

        // DAO
        IncomesDao dao = new IncomesDao();

        // DTO

        Incomes income = new Incomes(user_id,amount,emotion,category,created_at);


        // DB登録
        dao.insert(income);

        // 結果画面へ
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
        dispatcher.forward(request, response);
    }
}