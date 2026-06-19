package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログイン状況をセッション画面から取得する（ログインするときに"login"と関連付け済み）
		// ログインの結果がIdPwと一致しているのか
		// (IdPw)はインスタンスを関連付けしていることを保証する
		UserDto ip = (UserDto) request.getSession().getAttribute("login");
		// ログインできたらホーム画面に飛ぶ
		// ログインしていなかったらログイン画面のまま
		if (ip == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("HomeServlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		//入力未入力の場合、エラーメッセージ表示させる。
		if (id == null || id.isEmpty() || pw == null || pw.isEmpty()) {
			request.setAttribute("errorMsg", "IDとパスワードを入力してください");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			return;
		}

		// IdPwのDTO
		UserDto idpw = new UserDto(id, pw);
		// IdPwのDao
		UserDao idpwDao = new UserDao();
		// セレクトの実行（ログイン成功したかどうか）
		boolean result = idpwDao.Login(idpw);
		// 取得できたパスワードと一致していたらホームサーブレットにリダイレクトする
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("user_id", id);
			response.sendRedirect("HomeServlet");
		} else {
			// 異なる場合はエラーを表示し、遷移しない
			request.setAttribute("errorMsg", "IDまたはパスワードが違います");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}
}