package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.UserDto;
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
		

		// どちらか片方でもならエラー表示
		List<String> errors = new ArrayList<>();


		if (id == null || id.isEmpty() || pw == null || pw.isEmpty()) {
				    errors.add("IDとパスワードを入力してください");
				}

				if (!errors.isEmpty()) {
				    request.setAttribute("errors", errors);
				    request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp")
				           .forward(request, response);
				    return;
				}

				// 登録処理を行う
					UserDao Dao = new UserDao();
					UserDto dto = new UserDto();
					dto.setId(id);
					dto.setPw(pw);
					boolean result = Dao.insert(dto);
					if(result) {
						response.sendRedirect("LoginServlet");
					}else {		
					request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp")
						.forward(request, response);
						}
	}

}
