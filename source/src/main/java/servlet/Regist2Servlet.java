package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IncomesDao;
import dto.Incomes;

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

        // 登録画面へ
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/regist1.jsp");
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
        Incomes income = new Incomes(0,user_id,created_at,amount, emotion, category);

        // DB登録
        dao.insert(income);

        // 結果画面へ
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
        dispatcher.forward(request, response);
    }
}