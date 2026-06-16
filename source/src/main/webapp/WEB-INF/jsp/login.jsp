<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ログイン</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<!-- ヘッダー（ここから） -->
<body>
	<header>
		<h1 id="logo">ログイン</h1>
	</header>

	<!-- ヘッダー（ここまで） -->
	<!-- メイン（ここから） -->

	<main>
		<form id="login_form" action="LoginServlet" method="post">
			<table class="forms idpw">

				<tr>
					<td colspan = "2">
							<label> ユーザーID<br> <input type="text" id="id"
								name="id" placeholder="ユーザーIDを入力">
							</label>
					</td>
				</tr>
				<tr >
					<td colspan = "2">
	
							<label> パスワード<br> <input type="password" id="pw"
								name="pw" placeholder="パスワードを入力">
							</label>
					</td>
				</tr>
				<tr>
					<td colspan = "2">
						<span id="error_message"></span>
					</td>
				</tr>
				<tr>
					<td>
						
							<div class="login"> 

								<button type="submit" name="login" class="image-button">
									<img
										src="${pageContext.request.contextPath}/images/loginbutton.png"
										alt="ログイン">
								</button>
							</div >
						
					</td>
					<td>
						
							<div  class="reset">
								<button type="reset" name="login" class="image-button">
									<img
										src="${pageContext.request.contextPath}/images/resetbutton.png"
										alt="リセット">
								</button>

							</div >
						
					</td>
				</tr>
				<tr>
					<td colspan = "2">
						<br> <br> <a href="SignupServlet">新規登録はこちら</a>
					</td>
				</tr>
			</table>
		</form>
	</main>

	<!-- メイン（ここまで） -->
	<!-- フッター（ここから） -->
	<footer> </footer>
	<!-- JavaScript（ここから） -->
	<script>
		/* HTML要素をオブジェクトとして取得する */
		let formObj = document.getElementById('login_form');
		let errorMessageObj = document.getElementById('error_message');

		/* [ログイン]ボタンをクリックしたときの処理 */
		formObj.onsubmit = function(event) {
			if (formObj.id.value === '' || formObj.pw.value === '') {
				errorMessageObj.textContent = '※IDとパスワードを入力してください';
				event.preventDefault();
			}
		};

		/* [リセット]ボタンをクリックしたときの処理 */
		formObj.onreset = function() {
			errorMessageObj.textContent = null;
		};
	</script>
	<!-- JavaScript（ここまで） -->
</body>
</html>