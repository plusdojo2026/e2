<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>ホーム</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS -->
<link rel="stylesheet" href="css/home.css">
<!-- script -->
<script src="${pageContext.request.contextPath}/js/home.js"></script>
</head>

<body>

	<header class="header">
		<div class="title">
			<b> ホーム </b>
		</div>
	</header>

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

		<form method="POST" action="HomeServlet"
			onsubmit="return validateBudget();">
			<p id="errorMsg" style="color: red;"></p>
			<!-- 目標貯金額 -->
			<section class="card">
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

				<div class="progress-area">
					<div class="progress-bar">
						<div class="progress-fill" style="width: ${goalPercent}%"></div>
					</div>
					<div class="progress-text">${goalPercent}%</div>
				</div>
			</section>

			<!-- 予算 -->
			<section class="card">
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

				<div class="progress-area">
					<div class="progress-bar">
						<div class="progress-fill budget" style="width: ${budgetPercent}%"></div>
					</div>
					<div class="progress-text">${budgetPercent}%</div>
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
					<div class="balance <c:if test='${balance < 0}'>red</c:if>">￥
						${balance}</div>
				</div>
			</div>
		</section>

	</main>

	<!-- フッター -->
	<footer class="footer">
		<nav class="nav-bar">
			<a class="nav-item active" href="HomeServlet"> <span class="icon">🏠</span>
				<span>ホーム</span>
			</a> <a class="nav-item" href="Regist1Servlet"> <span class="icon">✏️</span>
				<span>登録</span>
			</a> <a class="nav-item" href="ListServlet"> <span class="icon">📋</span>
				<span>一覧</span>
			</a> <a class="nav-item" href="LoginServlet"> <span class="icon">🚪</span>
				<span>ログアウト</span>
			</a>
		</nav>
	</footer>

</body>

</html>