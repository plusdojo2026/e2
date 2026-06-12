<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ログイン</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/login.css">
</head>
<!-- ヘッダー（ここから） -->
<body>
	<header>
		<h1 id="logo">ログイン</h1>
	</header>

	<!-- ヘッダー（ここまで） -->
	<!-- メイン（ここから） -->

	<main>
		<form id="login_form" action="result.html">
			<table>
				<div class="forms">

					<tr>
						<td><label>
								<div class="idpw">
									ユーザーID<br>
								</div> <input type="text" id="id" name="id" placeholder="ユーザーIDを入力">
						</label></td>
					</tr>
					<tr>
						<td><label>
								<div class="idpw">
									パスワード<br>
								</div> <input type="password" id="pw" name="pw" placeholder="パスワードを入力">
						</label></td>
					</tr>
				</div>

				<tr>
					<td colspan="2"><span class="login"> <br> <span
							id="error_message"></span>
							<div class="imgb">
								<button type="submit" name="login" class="image-button">
									<img
										src="${pageContext.request.contextPath}/images/loginbutton.png"
										width="150" alt="ログイン">
								</button></span> <span class="reset">
							<button type="reset" name="login" class="image-button">
								<img
									src="${pageContext.request.contextPath}/images/resetbutton.png"
									width="150" alt="リセット">
							</button>
							</div>
					</span> <br> <br> <a href="newRegister.jsp">新規登録はこちら</a>
					<td>
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