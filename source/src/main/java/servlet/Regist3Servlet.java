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
import dao.PatienceDao;
import dao.SituationDao;
import dto.KeyValueDto;
import dto.PatienceDto;

@WebServlet("/Regist3Servlet")
public class Regist3Servlet extends HttpServlet {
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
        
        // カテゴリセレクトの項目取得(カテゴリのみ支出・我慢)
 		CategoryDao cDao = new CategoryDao();
 		List<KeyValueDto> categoryList = cDao.selectEP();

 		request.setAttribute("categoryList", categoryList);
 		
 	// 感情セレクトの項目取得
		EmotionDao eDao = new EmotionDao();
		List<KeyValueDto> emotionList = eDao.select();

		request.setAttribute("emotionList", emotionList);
		
	//状況セレクトの項目事項
		SituationDao sDao = new SituationDao();
		List<KeyValueDto> situationList = sDao.select();
		
		request.setAttribute("situationList", situationList);
        // 登録画面へ
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/regist3.jsp");
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
        String situation = request.getParameter("situation");
        String item_name = request.getParameter("item_name");
        String memo = request.getParameter("memo");

        // DAO
        PatienceDao dao = new PatienceDao();

        // DTO
        PatienceDto patience = new PatienceDto(0, user_id,amount,emotion,category,situation,item_name,memo,created_at);

        // DB登録
        dao.insert(patience);

        // 結果画面へ
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/result3.jsp");
        dispatcher.forward(request, response);
    }
}