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
		<button class="tab" onclick="showTab(event, 'income')">収入</button>
		<button class="tab active" onclick="showTab(event, 'expense')">支出</button>
		<button class="tab" onclick="showTab(event, 'patience')">我慢</button>
	</div>

	<!-- タブの中身　-->

	<!-- テスト用
	${incomeList}
	 -->

	<div id="expense" class="content active">
		<!-- カレンダー押した瞬間表示切替＆選択した年月保持 -->
		<form method="get" action="ListServlet2">
			<input type="month" name="month" value="${yearMonth}"
				onchange="this.form.submit()">
		</form>



		<div class="tcard">
			<div class="header" onclick="toggleContent(this,'cat_et',0)">
				${yearMonth}<span>の支出総合計 </span> <span class="amount">
					¥${expenseTotal} </span> <span class="arrow">▼</span>
			</div>
			<div id="cat_et" class="detail" style="display: none;">
				<form method="post" action="ListServlet2">
					<c:forEach var="e" items="${expensesList}">
						<div class="row-item">
							<input type="checkbox" name="deleteIds" value="${e.id}">
							<input type="hidden" name="id" value="${e.id}"> <input
								type="date" name="created_at" value="${e.created_at}"> <select
								name="category">
								<c:forEach var="c" items="${categoryList}">
									<option value="${c.key}"
										<c:if test="${c.key == e.category}">selected
                                            </c:if>>
										${c.value}</option>
								</c:forEach>
							</select> <select name="tag">
								<c:forEach var="t" items="${tagList}">
									<option value="${t.key}"
										<c:if test="${t.key == e.tag}">selected</c:if>>
										${t.value}</option>
								</c:forEach>
							</select> <select name="situation">
								<c:forEach var="s" items="${situationList}">
									<option value="${s.key}"
										<c:if test="${s.key == e.situation}">selected
                                            </c:if>>
										${s.value}</option>
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
						<input type="submit" name="submit" value="編集"><input
							type="submit" name="submit" value="削除">
					</div>


				</form>
			</div>
		</div>



		<div class="ccard" id="expense_ccard">
			<form method="post" action="ListServlet2">
				<div class="header"
					onclick="toggleContent(this,'cat_ec','select1_menu','expense_ccard')">
					<select name="selectedCategory" id="select1_menu">
						<c:forEach var="c" items="${categoryList}">
							<option value="${c.key}">${c.value}</option>
						</c:forEach>
					</select> <span class="arrow">▼</span>
				</div>

				<c:forEach var="entry" items="${categoryList}" varStatus="st">
					<div id="cat_ec_${st.index}" class="detail" style="display: none;">
						<span>支出合計</span> <span class="amount">
							¥${expenseTotalCategoryMap[entry.key]} </span>

						<c:forEach var="e" items="${expenseCategoryMap[entry.key]}"
							varStatus="row">

							<div class="row-item">
								<input type="checkbox" name="deleteIds" value="${e.id}">

								<input type="hidden" name="id" value="${e.id}"> <input
									type="date" name="created_at" value="${e.created_at}">
								<select name="category">
									<c:forEach var="c" items="${categoryList}">
										<option value="${c.key}"
											<c:if test="${c.key == e.category}">selected
                                            </c:if>>
											${c.value}</option>
									</c:forEach>
								</select> <select name="tag">
									<c:forEach var="t" items="${tagList}">
										<option value="${t.key}"
											<c:if test="${t.key == e.tag}">selected</c:if>>
											${t.value}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}"
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>>
											${s.value}</option>
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

		<div class="scard" id="expense_scard">
			<form method="post" action="ListServlet2">
				<div class="header"
					onclick="toggleContent(this,'cat_es','select2_menu','expense_scard')">
					<select name="selectedCategory" id="select2_menu">
						<c:forEach var="c" items="${situationList}">
							<option value="${c.key}">${c.value}</option>
						</c:forEach>
					</select> <span class="arrow">▼</span>
				</div>

				<c:forEach var="entry" items="${situationList}" varStatus="st">
					<div id="cat_es_${st.index}" class="detail" style="display: none;">
						<span>支出合計</span> <span class="amount">
							¥${expenseTotalSituationMap[entry.key]} </span>

						<c:forEach var="e" items="${expenseSituationMap[entry.key]}"
							varStatus="row">

							<div class="row-item">
								<input type="checkbox" name="deleteIds" value="${e.id}">

								<input type="hidden" name="id" value="${e.id}"> <input
									type="date" name="created_at" value="${e.created_at}">
								<select name="category">
									<c:forEach var="c" items="${categoryList}">
										<option value="${c.key}"
											<c:if test="${c.key == e.category}">selected
                                            </c:if>>
											${c.value}</option>
									</c:forEach>
								</select> <select name="tag">
									<c:forEach var="t" items="${tagList}">
										<option value="${t.key}"
											<c:if test="${t.key == e.tag}">selected</c:if>>
											${t.value}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}"
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>>
											${s.value}</option>
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

		<div class="tagcard" id="expense_tagcard">
			<form method="post" action="ListServlet2">
				<div class="header"
					onclick="toggleContent(this,'cat_tag','select3_menu','expense_tagcard')">
					<select name="selectedCategory" id="select3_menu">
						<c:forEach var="c" items="${tagList}">
							<option value="${c.key}">${c.value}</option>
						</c:forEach>
					</select> <span class="arrow">▼</span>
				</div>

				<c:forEach var="entry" items="${tagList}" varStatus="st">
					<div id="cat_tag_${st.index}" class="detail" style="display: none;">
						<span>支出合計</span> <span class="amount">
							¥${expenseTotalTagMap[entry.key]} </span>

						<c:forEach var="e" items="${expenseTagMap[entry.key]}"
							varStatus="row">

							<div class="row-item">
								<input type="checkbox" name="deleteIds" value="${e.id}">

								<input type="hidden" name="id" value="${e.id}"> <input
									type="date" name="created_at" value="${e.created_at}">
								<select name="category">
									<c:forEach var="c" items="${categoryList}">
										<option value="${c.key}"
											<c:if test="${c.key == e.category}">selected
                                            </c:if>>
											${c.value}</option>
									</c:forEach>
								</select> <select name="tag">
									<c:forEach var="t" items="${tagList}">
										<option value="${t.key}"
											<c:if test="${t.key == e.tag}">selected</c:if>>
											${t.value}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}"
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>>
											${s.value}</option>
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

		<div class="ecard" id="expense_ecard">
			<form method="post" action="ListServlet2">
				<div class="header"
					onclick="toggleContent(this,'cat_ee','select4_menu','expense_ecard')">
					<select name="inemo" id="select4_menu">
						<c:forEach var="em" items="${emotionList}">
							<option value="${em.key}">${em.value}</option>
						</c:forEach>

					</select> <span class="arrow">▼</span>

				</div>

				<c:forEach var="entry" items="${emotionList}" varStatus="st">


					<div id="cat_ee_${st.index}" class="detail" style="display: none;">
						<span>支出合計</span> <span class="amount">
							¥${expenseTotalEmotionMap[entry.key]} </span>
						<c:forEach var="e" items="${expenseEmotionMap[entry.key]}">
							<div class="row-item">
								<input type="checkbox" name="deleteIds" value="${e.id}">
								<input type="hidden" name="id" value="${e.id}"> <input
									type="date" name="created_at" value="${e.created_at}">
								<select name="category">
									<c:forEach var="c" items="${categoryList}">
										<option value="${c.key}"
											<c:if test="${c.key == e.category}">selected
                                            </c:if>>
											${c.value}</option>
									</c:forEach>
								</select> <select name="tag">
									<c:forEach var="t" items="${tagList}">
										<option value="${t.key}"
											<c:if test="${t.key == e.tag}">selected</c:if>>
											${t.value}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}"
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>>
											${s.value}</option>
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

<footer class="footer">
	<nav class="nav-bar">
		<a class="nav-item" href="HomeServlet"> <span class="icon">🏠</span>
			<span>ホーム</span>
		</a> <a class="nav-item " href="Regist1Servlet"> <span class="icon">✏️</span>
			<span>登録</span>
		</a> <a class="nav-item active" href="ListServlet"> <span class="icon">📋</span>
			<span>一覧</span>
		</a> <a class="nav-item" href="LoginServlet"> <span class="icon">🚪</span>
			<span>ログアウト</span>
		</a>
	</nav>
</footer>


<script>
	
</script>



</html>