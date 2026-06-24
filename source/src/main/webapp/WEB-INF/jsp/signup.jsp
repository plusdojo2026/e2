<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新規登録</title>
<link rel="stylesheet" href="css/signup.css">
</head>
<body>
	<!-- ヘッダー（ここから） -->
	<header>
		<h1 id="logo">新規登録</h1>
	</header>
	<!-- ヘッダー（ここまで） -->
	<!-- メイン（ここから） -->
	<main>
		<form id="signup_form" method="POST" action="SignupServlet">
			<table class="forms idpw">
				<tr>
					<td colspan="2"><label> ユーザーID<br> <input
							type="text" id="id" name="id" placeholder="ユーザーIDを入力">
					</label></td>
				</tr>
				<tr>
					<td colspan="2"><label> パスワード<br> <input
							type="password" id="pw" name="pw" placeholder="パスワードを入力">
					</label></td>
				</tr>
				<tr>
					<td colspan="2" style="height: 25px; vertical-align: top;"><span
						id="error_message" style="color: red;"></span> <!-- エラーメッセージ --> <c:if
							test="${not empty errors}">
							<div style="color: red;">${errors[0]}</div>
						</c:if></td>
						
				</tr>
				<tr>
					<td>
						<div class="signup">
							<button type="submit" name="signup" class="image-button">
								<img
									src="${pageContext.request.contextPath}/images/signupbutton.png"
									alt="登録">
							</button>
						</div>
					</td>
					<td>
						<div class="reset">
							<button type="reset" name="reset" class="image-button">
								<img
									src="${pageContext.request.contextPath}/images/resetbutton.png"
									alt="リセット">
							</button>
						</div>
					</td>
				</tr>
			</table>

		</form>
	</main>

	<!-- メイン（ここまで） -->

	<!-- フッター（ここから） -->
	<footer> </footer>
	<!-- フッター（ここまで） -->
</body>
</html>