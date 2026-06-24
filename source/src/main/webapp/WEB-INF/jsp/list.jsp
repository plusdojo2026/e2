<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>一覧</title>
<link rel="stylesheet" type="text/css" href="css/list.css">
<script src="<c:url value='/js/list.js' />"></script>
</head>

<body>
	<header>
		<div class="title">
			<b> 一覧 </b>
		</div>
	</header>


	<!-- タブ -->
	<div class="tabs">
		<button class="tab active" onclick="showTab(event, 'income')">収入</button>
		<button class="tab" onclick="showTab(event, 'expense')">支出</button>
		<button class="tab" onclick="showTab(event, 'patience')">我慢</button>
	</div>

	<!-- タブの中身　-->

	<!-- テスト用
	${incomeList}
	 -->

	<div id="income" class="content active">
		<!-- カレンダー押した瞬間表示切替＆選択した年月保持 -->
		<form method="get" action="ListServlet">
			<input type="month" name="month" value="${yearMonth}"
				onchange="this.form.submit()">
		</form>



		<div class="tcard">
			<div class="header" onclick="toggleContent(this,'cat_it',0)">
				${yearMonth}<span>の収入総合計 </span> <span class="amount">
					¥${incomeTotal} </span> <span class="arrow">▼</span>
			</div>
			<div id="cat_it" class="detail" style="display: none;">
				<form method="post" action="ListServlet">
					<c:forEach var="e" items="${incomeList}">
						<div class="row-item">
							<input type="checkbox" name="deleteIds" value="${e.id}">
							<input type="hidden" name="id" value="${e.id}"> <input
								type="date" name="created_at" value="${e.created_at}"> <select
								name="category">
								<c:forEach var="c" items="${categoryList}">
									<option value="${c.key}"
										<c:if test="${c.key == e.category}">selected</c:if>>
										${c.value}</option>
								</c:forEach>
							</select> <select name="emotion" class="emo">
								<c:forEach var="em" items="${emotionList}">
									<option value="${em.key}"
										<c:if test="${em.key == e.emotion}">selected
                                            </c:if>>
										${em.value}</option>
								</c:forEach>
							</select> <input type="text" name="amount" value="${e.amount}">
						</div>
					</c:forEach>
					<div class="buttons">
						<input type="submit" name="submit" value="edit">編集 <input
							type="submit" name="submit" value="delete">削除
					</div>
				</form>
			</div>
		</div>



		<div class="ccard" id="income_ccard">
			<form method="post" action="ListServlet">
				<div class="header"
					onclick="toggleContent(this,'cat_ic','select1_menu','income_ccard')">
					<select name="selectedCategory" id="select1_menu">
						<c:forEach var="c" items="${categoryList}">
							<option value="${c.key}">${c.value}</option>
						</c:forEach>
					</select> <span class="arrow">▼</span>
				</div>



				<c:forEach var="entry" items="${categoryList}" varStatus="st">
					<div id="cat_ic_${st.index}" class="detail" style="display: none;">
						<span>収入合計</span> <span class="amount">
							¥${incomeTotalCategoryMap[entry.key]} </span>

						<c:forEach var="e" items="${incomeCategoryMap[entry.key]}"
							varStatus="row">

							<div class="row-item">

								<input type="checkbox" name="deleteIds" value="${e.id}">
								<input type="hidden" name="id" value="${e.id}"> <input
									type="date" name="created_at" value="${e.created_at}">
								<select name="category">
									<c:forEach var="c" items="${categoryList}">
										<option value="${c.key}"
											<c:if test="${c.key == e.category}">selected</c:if>>
											${c.value}</option>
									</c:forEach>
								</select> <select name="emotion" class="emo">
									<c:forEach var="em" items="${emotionList}">
										<option value="${em.key}"
											<c:if test="${em.key == e.emotion}">selected
                                            </c:if>>
											${em.value}</option>
									</c:forEach>
								</select> <input type="text" name="amount" value="${e.amount}">
							</div>
						</c:forEach>
						<div class="buttons">
							<input type="submit" name="submit" value="edit"> <input
								type="submit" name="submit" value="delete">
						</div>
					</div>
				</c:forEach>


			</form>
		</div>

		<div class="ecard" id="income_ecard">
			<form method="post" action="ListServlet">
				<div class="header"
					onclick="toggleContent(this,'cat_ie','select2_menu','income_ecard')">


					<select name="inemo" id="select2_menu">
						<c:forEach var="em" items="${emotionList}">
							<option value="${em.key}">${em.value}</option>
						</c:forEach>

					</select> <span class="arrow">▼</span>

				</div>

				<c:forEach var="entry" items="${emotionList}" varStatus="st">


					<div id="cat_ie_${st.index}" class="detail" style="display: none;">
						<span>収入合計</span> <span class="amount">
							¥${incomeTotalEmotionMap[entry.key]} </span>
						<c:forEach var="e" items="${incomeEmotionMap[entry.key]}">
							<div class="row-item">


								<input type="checkbox" name="deleteIds" value="${e.id}">

								<input type="hidden" name="id" value="${e.id}"> <input
									type="date" name="created_at" value="${e.created_at}">
								<select name="category">
									<c:forEach var="c" items="${categoryList}">
										<option value="${c.key}"
											<c:if test="${c.key == e.category}">selected</c:if>>
											${c.value}</option>
									</c:forEach>
								</select> <select name="emotion" class="emo">
									<c:forEach var="em" items="${emotionList}">
										<option value="${em.key}"
											<c:if test="${em.key == e.emotion}">selected
                                            </c:if>>
											${em.value}</option>
									</c:forEach>
								</select> <input type="text" name="amount" value="${e.amount}">
							</div>
						</c:forEach>
						<div class="buttons">

							<input type="submit" name="submit" value="編集"> <input
								type="submit" name="submit" value="削除">
						</div>
					</div>
				</c:forEach>
			</form>
		</div>

	</div>







</body>

<footer>

	<!--フッター-->
	<div id="footer">
		<nav class="nav-bar">
			<a class="nav-item" href="HomeServlet"> <span class="icon">🏠</span>
				<span>ホーム</span>
			</a> <a class="nav-item " href="Regist1Servlet"> <span class="icon">✏️</span>
				<span>登録</span>
			</a> <a class="nav-item active" href="ListServlet"> <span
				class="icon">📋</span> <span>一覧</span>
			</a> <a class="nav-item" href="LoginServlet"> <span class="icon">🚪</span>
				<span>ログアウト</span>
			</a>
		</nav>
	</div>
	<!--フッター終わり-->
</footer>


<script>
	
</script>



</html>