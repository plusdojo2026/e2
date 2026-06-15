<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
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
    <form method="POST" action="SignupServlet">
      <table class="forms" >
          <tr>
            <td>
            <div class="idpw">
              <label>
                ユーザーID<br>
             <input type="text" name="id" placeholder="ユーザーIDを入力">
            </label>
              </div>
            </td>
          </tr>
          <tr>
            <td>
            <div class="idpw">
              <label>               
                パスワード<br>              
                <input type="password" name="pw" placeholder="パスワードを入力">              
              </label>
              </div>
            </td>
          </tr>       
        <tr>
          <td>       
          <% List<String> errors = (List<String>) request.getAttribute("errors"); %>
           <!-- エラーがある場合、エラーメッセージを赤字で表示-->
			<% if (errors != null && !errors.isEmpty()) { %>
    			<div style="color:red;"><%= errors.get(0) %></div>
			<% } %>
			
				<div class="imgb">	
            <span class="signup">
              <button type="submit" name="signup" class="image-button">
                <img src="img/Image signupbutton.png" width="150px" height="auto;" alt="登録"></button>
            </span>
            <span class="reset">
              <button type="reset" name="reset" class="image-button">
                <img src="img/resetbutton.png" width="150px" height="auto;" alt="リセット"></button>
                </span> 
                </div>
          <td>
        </tr>
      </table>
    </form>
    <script>
    /* 登録ボタンをクリックしたときの処理 */
	formObj.onsubmit = function(event) {
		/* ID,パスワードを必須入力項目とする */
		if (!formObj.id.value) {
			errorMessageObj.textContent = '※IDとパスワードを入力してください';
			event.preventDefault();
    	}
    }
    </script>
  </main>
  <!-- メイン（ここまで） -->
  <!-- フッター（ここから） -->
  <footer>

  </footer>
  <!-- フッター（ここまで） -->
  </body>

</html>