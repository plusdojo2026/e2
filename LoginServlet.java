package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				//ログインしできたらホーム画面に飛ぶ
				//ログインしていなかったらログイン画面のまま
				if(ip == null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
					dispatcher.forward(request, response);
				}else {
					response.sendRedirect("HomeServlet");
				}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		
		//取得できたパスワードと一致していたらホームサーブレットにリダイレクトする
		//result.get(0)はログインしているユーザーのこと
//		if(result.size()==1&& result.get(0).getPw().equals(pw)) {
//			//セッションスコープでログインとユーザーを紐付ける
//			request.getSession().setAttribute("login", result.get(0));
//			response.sendRedirect("HomeServlet");
//		}else {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
//			dispatcher.forward(request, response);
//			
//		}
		
		
	}

}
