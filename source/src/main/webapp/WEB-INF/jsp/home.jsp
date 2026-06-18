<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>ホーム</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS -->
<link rel="stylesheet" href="css/home.css">
</head>

<body>

	<!-- ヘッダー -->
	<header class="header"> ホーム </header>

	<!-- メイン -->
	<main class="main">

		<!-- 今月の収入・支出 -->
		<section class="summary">
			<div class="summary-card income">
				<div class="label">今月の収入</div>
				<div class="logo">
					<img src="images/arrow1.png" alt="">
				</div>
				<div class="inamount">￥ ${incomesTotal}</div>
			</div>

			<div class="summary-card expense">
				<div class="label">今月の支出</div>
				<div class="logo">
					<img src="images/arrow2.png" alt="">
				</div>
				<div class="examount">￥ ${expensesTotal}</div>
			</div>
		</section>

		<form method="POST" action="HomeServlet">
			<!-- 目標貯金額 -->
			<section class="card">

				<div class="progress-area">
					<div class="progress-bar">
						<div class="progress-fill" style="width: ${goalPercent}%"></div>
					</div>
					<div class="progress-text">${goalPercent}%</div>
				</div>

				<div class="card-both">
					<div class="card-left">
						<div class="card-content">
							<div class="title">目標貯金額</div>
						</div>
						<div class="logo">
							<img src="images/target.png" alt="">
						</div>
					</div>

					<div class="card-right">
						<span>¥</span> <input type="number" name="goal_amount"
							value="${budget.goal_amount}">
						<button class="save-button" type="submit" name="action"
							value="goal">保存</button>
					</div>
				</div>
			</section>

			<!-- 予算 -->
			<section class="card">

				<div class="progress-area">
					<div class="progress-bar">
						<div class="progress-fill budget" style="width: ${budgetPercent}%"></div>
					</div>
					<div class="progress-text">${budgetPercent}%</div>
				</div>

				<div class="card-both">
					<div class="card-left">
						<div class="card-content">
							<div class="title">予算</div>
						</div>
						<div class="logo">
							<img src="images/money1.png" alt="">
						</div>
					</div>

					<div class="card-right">
						<span>¥</span> <input type="number" name="budget_amount"
							value="${budget.budget_amount}">
						<button class="save-button" type="submit" name="action"
							value="budget">保存</button>
					</div>
				</div>
			</section>

		</form>

		<!-- 残金 -->
		<section class="card">
			<div class="card-both">
				<div class="card-left">
					<div class="card-content">
						<div class="title">残金</div>
					</div>
					<div class="logo">
						<img src="images/money2.png" alt="">
					</div>
				</div>
				<div class="card-right">
					<span>¥</span>
					<div class="balance-amount">${balance}</div>
				</div>
			</div>
		</section>

	</main>

	<!-- フッター -->
	<footer class="footer">
		<nav class="nav-bar">
			<a class="nav-item" href="HomeServlet"> <span class="icon">🏠</span>
				<span>ホーム</span>
			</a> <a class="nav-item active" href="Regist1Servlet"> <span
				class="icon">✏️</span> <span>登録</span>
			</a> <a class="nav-item" href="ListServlet"> <span class="icon">📋</span>
				<span>一覧</span>
			</a> <a class="nav-item" href="LoginServlet"> <span class="icon">🚪</span>
				<span>ログアウト</span>
			</a>
		</nav>
	</footer>

</body>

</html>