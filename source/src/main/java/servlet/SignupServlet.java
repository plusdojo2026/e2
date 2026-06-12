package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import dao.UserDAO;
//import dto.User;
/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// ID未入力ならエラーメッセージ
		if(id == null|| pw.isEmpty()) {
			request.setAttribute("error", "ユーザーIDを入力してください");
		    request.getRequestDispatcher("/WEB-INF/jsp/Signup.jsp")
		           .forward(request, response);
		}

		// PW未入力ならエラーメッセージ
		else if(pw == null|| pw.isEmpty()) {
			request.setAttribute("error", "パスワードを入力してください");
		    request.getRequestDispatcher("/WEB-INF/jsp/Signup.jsp")
		           .forward(request, response);
		}

		// 登録処理を行う
//					UserDAO Dao = new UserDAO();
//					boolean result = dao.insert(id,pw)
//					if(result) {
//						response.sendRedirect("LoginServlet");
//					}else {		
//						request.getRequestDispatcher("/WEB-INF/jsp/Signup.jsp");
//						.forward(request, response);
//					}
	}

}
