package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.UserDto;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// ログイン状況をセッション画面から取得する（ログインするときに"login"と関連付け済み）
				//ログインの結果がIdPwと一致しているのか
				// (IdPw)はインスタンスを関連付けしていることを保証する
				 Object ip = null; //(IdPw)request.getSession().getAttribute("login");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
	}
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {




		    request.setCharacterEncoding("UTF-8");
		
		    String id = request.getParameter("id");
		    String pw = request.getParameter("pw");
		
		    UserDao dao = new UserDao();
		    
		    UserDto user = new UserDto();
			user.setId(id);
			user.setPw(pw);

		
		    boolean isLogin = dao.Login(user);
		
		    if (isLogin) {
		        request.getSession().setAttribute("user_id", id);
		        response.sendRedirect("HomeServlet");
	
		    } else {
		        RequestDispatcher dispatcher =
		            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		        dispatcher.forward(request, response);
		    }
		}
}
