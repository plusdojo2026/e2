<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>支出登録画面</title>

  <link rel="stylesheet" href="css/regist.css">

  <script src="js/regist.js" defer></script>
</head>

<body>

  <div class="phone-frame">

    <!-- ヘッダー -->
    <div class="header">支出登録画面</div>

    <!-- フォーム -->
    <div class="content">
      <form id="expenditureForm" action="Regist1Servlet" method="post">
        <div class="tabs">
          <button type="button" class="tab" onclick="location.href='Regist2Servlet'">収入</button>
          <button type="button" class="tab active" onclick="location.href='Regist1Servlet'">支出</button>
          <button type="button" class="tab" onclick="location.href='Regist3Servlet'">我慢</button>
        </div>

        <div class="form-row">
          <div class="field-group">

            <!-- 日付 -->
            <div class="field-wrap">
              <div class="label-tag">日付</div>
              <input class="input-field" type="date" id="created_at" name="created_at" placeholder="日付を入力してください" value="">
              <div class="error-msg" id="errDate">日付を入力してください</div>
            </div>

            <!-- 金額 -->
            <div class="field-wrap">
              <div class="label-tag">金額</div>
              <input class="input-field" type="text" id="amount" name="amount" placeholder="金額を入力してください" value="">
              <div class="error-msg" id="errAmount">金額を入力してください</div>
            </div>

            <!-- 商品名 -->
            <div class="field-wrap">
              <div class="label-tag">商品名</div>
              <input class="input-field" type="text" id="item_name" name="item_name" placeholder="商品名を入力してください" maxlength="100"
                value="">
              <div class="error-msg" id="errItemName">商品名を入力してください</div>
            </div>

          </div>
          <div class="field-group2">
            <!-- 感情ボタン -->
             <div class="field-wrap" style="margin-bottom:2px;">
            <div class="label-tag">感情</div>
          <button type="button" class="emotion-btn" id="emotionBtn">
            <span id="emotionLabel">😊 嬉しい</span>
          </button>
          <input type="hidden" id="emotion" name="emotion" value="1">
          </div>
          <!-- タグ -->
          <div class="field-wrap" style="margin-bottom:8px;">
            <div class="label-tag">タグ</div>
            <select class="select-field" id="tag" name="tag">
		  	<c:forEach var="c" items="${tagList}">
		    <option value="${c.key}">
		      ${c.value}
		    </option>
		  	</c:forEach>
		
			</select>
            <div class="error-msg" id="errTag">タグを選択してください</div>
          </div>
          </div>
          
        </div>

        <!-- カテゴリ -->
        <div class="field-wrap" style="margin-bottom:8px;">
          <div class="label-tag">カテゴリ</div>
          <select class="select-field" id ="category" name="category">
          <option value="" disabled selected>カテゴリを選択</option>
		  <c:forEach var="c" items="${categoryList}">
		    <option value="${c.key}">
		      ${c.value}
		    </option>
		  </c:forEach>
		</select>
          <div class="error-msg" id="errCategory">カテゴリを選択してください</div>
        </div>

        <!-- 状況 -->
        <div class="field-wrap" style="margin-bottom:8px;">
          <div class="label-tag">状況</div>
          <select class="select-field" id ="situation" name="situation">
          <option value="" disabled selected>状況を選択</option>
		  <c:forEach var="s" items="${situationList}">
		    <option value="${s.key}">
		      ${s.value}
		    </option>
		  </c:forEach>
		</select>
		<div class="error-msg" id="errSituation">状況を選択してください
		</div>
		</div>

        <!-- 備考 -->
        <div class="field-wrap">
          <div class="label-tag">備考</div>
          <textarea class="textarea-field" id="memo" name="memo" placeholder="メモがあれば入力してください"
            maxlength="500"></textarea>
        </div>
        <!-- ボタン -->
        <div class="btn-row">
          <button type="submit" class="btn-register">登録</button>
          <button type="button" class="btn-reset" id="resetBtn">リセット</button>
        </div>

      </form>
    </div>

    <!-- ナビバー -->
    <nav class="nav-bar">
      <a class="nav-item" href="HomeServlet">
        <span class="icon">🏠</span>
        <span>ホーム</span>
      </a>
      <a class="nav-item active" href="Regist1Servlet">
        <span class="icon">✏️</span>
        <span>登録</span>
      </a>
      <a class="nav-item" href="ListServlet">
        <span class="icon">📋</span>
        <span>一覧</span>
      </a>
      <a class="nav-item" href="LoginServlet">
        <span class="icon">🚪</span>
        <span>ログアウト</span>
      </a>
    </nav>

  </div>

  <!-- 感情モーダル -->
  <div class="modal-overlay" id="emotionModal">
    <div class="modal-card">
      <h3>感情を選んでください</h3>
      <div class="emotion-grid">
        <button type="button" class="emotion-opt" data-val="1">😊 嬉しい</button>
        <button type="button" class="emotion-opt" data-val="2">😐 普通</button>
        <button type="button" class="emotion-opt" data-val="3">😔 後悔</button>
        <button type="button" class="emotion-opt" data-val="4">😌 満足</button>
      </div>
      <button type="button" class="modal-close" id="modalClose">閉じる</button>
    </div>
  </div>


</body>

</html>