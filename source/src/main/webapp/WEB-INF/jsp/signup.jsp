<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <form method="POST" action="/e2/SignupServlet">
      <div class="forms">
      <table>
          <tr>
            <td>
              <label>
                <div class="idpw">
                ユーザーID<br>
                </div>
                <input type="text" name="id" placeholder="ユーザーIDを入力">
               
              </label>
            </td>
          </tr>
          <tr>
            <td>
              <label>
                <div class="idpw">
                パスワード<br>
                </div>
                <input type="password" name="pw" placeholder="パスワードを入力">
                
              </label>
            </td>
          </tr>
        </div>
        <tr>
          <td colspan="2">
            <span class="signup">
              <div class="imgb">
              <button type="submit" name="signup" class="image-button">
                <img src="img/Image signupbutton.png" width="150px" height="auto;" alt="登録"></button>
            </span>
            <span class="reset">
              <button type="reset" name="reset" class="image-button">
                <img src="img/resetbutton.png" width="150px" height="auto;" alt="リセット"></button>
  
            </span> 
          <td>
        </tr>
      </table>
      </div>
    </form>
  </main>
  <!-- メイン（ここまで） -->
  <!-- フッター（ここから） -->
  <footer>

  </footer>
  <!-- フッター（ここまで） -->
  </body>

</html>