<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="ja">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>支出登録画面</title>

  <link rel="stylesheet" href="sisyututouroku.css">

  <script src="sisyututouroku.js" defer></script>
</head>

<body>

  <div class="phone-frame">

    <!-- ヘッダー -->
    <div class="header">支出登録画面</div>

    <!-- フォーム -->
    <div class="content">
      <form id="expenditureForm" action="ExpenseRegisterServlet" method="post">
        <div class="tabs">
          <button class="tab active" onclick="showTab(event, 'income')">収入</button>
          <button class="tab" onclick="showTab(event, 'expense')">支出</button>
          <button class="tab" onclick="showTab(event, 'save')">我慢</button>
        </div>

        <!-- タブの中身　-->
        <div id="income" class="content active">
        </div>

        <div id="expense" class="content">
        </div>

        <div id="save" class="content">
        </div>
        <div class="form-row">
          <div class="field-group">

            <!-- 日付 -->
            <div class="field-wrap">
              <div class="label-tag">日付</div>
              <input class="input-field" type="date" id="created_at" name="created_at"placeholder="日付を入力してください"value="">
            </div>

            <!-- 金額 -->
            <div class="field-wrap">
              <div class="label-tag">金額</div>
              <input class="input-field" type="text" id="amount" name="amount" placeholder="金額を入力してください" value="">
              <div class="error-msg" id="errAmount">整数を入力してください</div>
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
          <button type="button" class="emotion-btn" id="emotionBtn">
            <span id="emotionLabel">感情</span>
          </button>
          <input type="hidden" id="emotion_id" name="emotion_id" value="">
          <!-- タグ -->
          <div class="field-wrap" style="margin-bottom:8px;">
            <div class="label-tag">タグ</div>
            <select class="select-field" id="tag_id" name="tag_id">
              <option value="" disabled selected>タグ選択</option>
              <option value="食費">食費</option>
              <option value="交通費">交通費</option>
              <option value="日用品">日用品</option>
              <option value="娯楽">娯楽</option>
              <option value="医療費">医療費</option>
              <option value="その他">その他</option>
            </select>
            <div class="error-msg" id="errTag">タグを選択してください</div>
          </div>
          </div>
          
        </div>

        <!-- カテゴリ -->
        <div class="field-wrap" style="margin-bottom:8px;">
          <div class="label-tag">カテゴリ</div>
          <select class="select-field" id="category_id" name="category_id">
            <option value="" disabled selected>カテゴリを選択</option>
            <option value="食費">食費</option>
            <option value="交通費">交通費</option>
            <option value="日用品">日用品</option>
            <option value="娯楽">娯楽</option>
            <option value="医療費">医療費</option>
            <option value="その他">その他</option>
          </select>
          <div class="error-msg" id="errCategory">カテゴリを選択してください</div>
        </div>

        <!-- 状況 -->
        <div class="field-wrap" style="margin-bottom:8px;">
          <div class="label-tag">状況</div>
          <select class="select-field" id="situation_id" name="situation_id">
            <option value="" disabled selected>状況を選択</option>
            <option value="通常">通常</option>
            <option value="キャンペーン">キャンペーン</option>
            <option value="特売">特売</option>
          </select>
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
      <a class="nav-item" href="Servlet">
        <span class="icon">🏠</span>
        <span>ホーム</span>
      </a>
      <a class="nav-item active" href="Servlet">
        <span class="icon">✏️</span>
        <span>登録</span>
      </a>
      <a class="nav-item" href="Servlet">
        <span class="icon">📋</span>
        <span>一覧</span>
      </a>
      <a class="nav-item" href="Servlet">
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
        <button type="button" class="emotion-opt" data-val="😊">😊 嬉しい</button>
        <button type="button" class="emotion-opt" data-val="😐">😐 普通</button>
        <button type="button" class="emotion-opt" data-val="😔">😔 後悔</button>
        <button type="button" class="emotion-opt" data-val="😌">😌 満足</button>
      </div>
      <button type="button" class="modal-close" id="modalClose">閉じる</button>
    </div>
  </div>


</body>

</html>