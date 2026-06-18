<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>一覧 | kakemi</title>
<link rel="stylesheet" type="text/css" href="css/list.css">
<script src="<c:url value='/js/list.js' />"></script>
</head>

<body>
	<header> 一覧 </header>
	

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



<<<<<<< Updated upstream
			<div class="tcard">

				<div class="header"
					onclick="toggleContent('cat${st.index}_it', this)">
					<span> 月の収入総合計 </span> <span class="amount">
						¥${incomeTotalMap[entry.key]} </span> <span class="arrow">▼</span>

				</div>

				<div id="cat${st.index}_it" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
						<c:forEach var="e" items="${entry.value}">

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

							<input type="submit" name="submit" value="編集"> <input
								type="submit" name="submit" value="削除">
						</div>

					</form>
				</div>
=======
		<!-- 合計用カード -->
		<div class="tcard">
>>>>>>> Stashed changes


			<div class="header"
				onclick="toggleContent('cat${st.index}_it', this)">
				<span> 月の収入総合計 </span> <span class="amount">
					¥${incomeTotalMap[entry.key]} </span> <span class="arrow">▼</span>

			</div>

			<div id="cat${st.index}_it" class="detail" style="display: none;">
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

						<input type="submit" name="submit" value="編集"> <input
							type="submit" name="submit" value="削除">
					</div>

				</form>
			</div>
		</div>

		<!-- カテゴリ用カード -->

		<div class="ccard">


			<div class="header"
				onclick="toggleContent('cat${st.index}_ic', this)">



<<<<<<< Updated upstream
					<select name="tag" id="select_menu">
						<c:forEach var="c" items="${categoryList}">
							<option value="${c.key}">${c.value}</option>
						</c:forEach>


					</select> の収入合計 <span class="amount"> ¥${categoryTotalMap[entry.key]}
					</span> <span class="arrow">▼</span>

				</div>

				<div id="cat${st.index}_ic" class="detail" style="display: none;">



					<form method="post" action="ListServlet">

						<c:forEach var="e" items="${entry.value}">

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
=======
				<form method="get" action="ListServlet">

					<select name="selectedCategory" id="select_menu"
						onchange="this.form.submit()">

						<c:forEach var="c" items="${categoryList}">
							<option value="${c.key}"
								<c:if test="${c.key == selectedCategory}">
                    selected="selected"
                </c:if>>
								${c.value}</option>
						</c:forEach>
						<c:forEach var="entry" items="${incomeCategoryMap}" varStatus="st">
						</c:forEach>
					</select>の収入合計 <span class="amount"> ¥${selectedCategoryTotal} </span> <span
						class="arrow">▼</span>
				</form>
			</div>

			<div id="cat${st.index}_ic" class="detail" style="display: none;">



				<form method="post" action="ListServlet">

					<c:forEach var="e" items="${categoryIncomeList}">
					</c:forEach>
					<div class="row-item">

						<input type="checkbox" name="deleteIds" value="${e.id}"> <input
							type="hidden" name="id" value="${e.id}"> <input
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
>>>>>>> Stashed changes
                                            </c:if>>
									${em.value}</option>
							</c:forEach>
						</select> <input type="text" name="amount" value="${e.amount}">
					</div>

					<div class="buttons">

						<input type="submit" name="submit" value="編集"> <input
							type="submit" name="submit" value="削除">
					</div>

				</form>
			</div>


		</div>
		<!--感情用カード -->
		<c:forEach var="entry" items="${incomeEmotionMap}" varStatus="st">
			<div class="ecard">


				<div class="header"
					onclick="toggleContent('cat${st.index}_ie', this)">

<<<<<<< Updated upstream
					<span> <select name="emotion" id="select_menu">
							<c:forEach var="em" items="${emotionList}">
								<option value="${em.key}">${em.value}</option>
							</c:forEach>
					</select> の収入合計
					</span> <span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
						class="arrow">▼</span>

=======
					<form method="get" action="ListServlet">



						<select name="selectedEmotion" id="select_menu"
							onchange="this.form.submit()">

							<c:forEach var="em" items="${emotionList}">
								<option value="${em.key}"
									<c:if test="${em.key == selectedEmotion}">
                    selected="selected"
                </c:if>>
									${em.value}</option>
							</c:forEach>

						</select> の収入合計 <span class="amount"> ¥${selectedEmotionTotal} </span> <span
							class="arrow">▼</span>
					</form>
>>>>>>> Stashed changes
				</div>

				<div id="cat${st.index}_ie" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
						<c:forEach var="e" items="${entry.value}">
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

							<input type="submit" name="submit" value="編集"> <input
								type="submit" name="submit" value="削除">
						</div>
					</form>
				</div>

			</div>
		</c:forEach>

	</div>

	<div id="expense" class="content">
		<div class="calendar">
			<input type="month" name="calendar">
		</div>



		<div class="tcard">

			<div class="header" onclick="toggleContent('cat${st.index}_t', this)">

				<span> ${entry.key} の支出合計 </span> <span class="amount">
					¥${expenseTotalMap[entry.key]}</span> <span class="arrow">▼</span>

			</div>
			<c:forEach var="entry" items="${expenseCategoryMap}" varStatus="st"></c:forEach>

			<c:forEach var="e" items="${categoryIncomeList}">
				<div id="cat${st.index}_t" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
<<<<<<< Updated upstream
						<c:forEach var="e" items="${entry.value}">
=======
>>>>>>> Stashed changes



						<div class="row-item">

<<<<<<< Updated upstream
								<input type="hidden" name="id" value="${e.id}"> <input
									type="date" name="created_at" value="${e.created_at}"> <select
									name="category">
									<c:forEach var="c" items="${categoryList}">
										<option value="${c.key}"
											<c:if test="${c.key == e.category}">selected
=======
							<input type="checkbox" name="deleteIds" value="${e.id}">

							<input type="hidden" name="id" value="${e.id}"> <input
								type="date" name="created_at" value="${e.created_at}"> <select
								name="category">
								<c:forEach var="c" items="${categoryList}">
									<option value="${c.key}"
										<c:if test="${c.key == e.category}">selected
>>>>>>> Stashed changes
                                            </c:if>>
										${c.value}</option>
								</c:forEach>
							</select> <select name="tag">
								<c:forEach var="t" items="${tagList}">
									<option value="${t.id}"
										<c:if test="${t.id == e.tag}">selected</c:if>>
										${t.tname}</option>
								</c:forEach>
							</select> <select name="situation">
								<c:forEach var="s" items="${situationList}">
									<option value="${s.key}">
										<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
									</option>
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


						<div class="buttons">

							<input type="submit" name="submit" value="編集"> <input
								type="submit" name="submit" value="削除">
						</div>
					</form>
				</div>
			</c:forEach>
		</div>



<<<<<<< Updated upstream
			</div>

			<div class="ccard">

				<div class="header"
					onclick="toggleContent('cat${st.index}_c', this)">

					<c:forEach var="c" items="${categoryList}">
						<option value="${c.key}">${c.value}</option>
					</c:forEach>
					<span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
						class="arrow">▼</span>

				</div>

				<div id="cat${st.index}_c" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
						<c:forEach var="e" items="${entry.value}">

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
										<option value="${t.id}"
											<c:if test="${t.id == e.tag}">selected</c:if>>
											${t.tname}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}">
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
										</option>
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

					</form>
				</div>
			</div>

			<div class="scard">

				<div class="header"
					onclick="toggleContent('cat${st.index}_s', this)">

					<span> <select name="situetion" id="select_menu">
							<option value="1">通勤時</option>
							<option value="2">昼休み</option>
							<option value="3">休日</option>
					</select> の支出合計
					</span> <span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
						class="arrow">▼</span>

				</div>

				<div id="cat${st.index}_s" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
						<c:forEach var="e" items="${entry.value}">


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
										<option value="${t.id}"
											<c:if test="${t.id == e.tag}">selected</c:if>>
											${t.tname}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}">
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
										</option>
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

					</form>
				</div>
			</div>

			<div class="tagcard">

				<div class="header"
					onclick="toggleContent('cat${st.index}_tag', this)">

					<span> <select name="tag" id="select_menu">
							<option value="1">セール</option>
							<option value="2">曖昧</option>
							<option value="3">ご褒美</option>
					</select> の支出合計
					</span> <span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
						class="arrow">▼</span>

				</div>

				<div id="cat${st.index}_tag" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
						<c:forEach var="e" items="${entry.value}">


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
										<option value="${t.id}"
											<c:if test="${t.id == e.tag}">selected</c:if>>
											${t.tname}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}">
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
										</option>
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

					</form>
				</div>
			</div>

			<div class="ecard">

				<div class="header"
					onclick="toggleContent('cat${st.index}_e', this)">

					<span> <select name="tag" id="select_menu">
							<option value="1">😐</option>
							<option value="2">😊</option>
							<option value="3">😢</option>
							<option value="4">😡</option>
					</select> の支出合計
					</span> <span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
						class="arrow">▼</span>

				</div>

				<div id="cat${st.index}_e" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
						<c:forEach var="e" items="${entry.value}">



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
										<option value="${t.id}"
											<c:if test="${t.id == e.tag}">selected</c:if>>
											${t.tname}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}">
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
										</option>
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

					</form>
				</div>
			</div>
		</c:forEach>

=======
>>>>>>> Stashed changes
	</div>

	<div class="ccard">

		<div class="header" onclick="toggleContent('cat${st.index}_c', this)">

			<c:forEach var="c" items="${categoryList}">
				<option value="${c.key}">${c.value}</option>
			</c:forEach>
			<span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
				class="arrow">▼</span>

		</div>

		<div id="cat${st.index}_c" class="detail" style="display: none;">
			<form method="post" action="ListServlet">
				<c:forEach var="e" items="${categoryIncomeList}">

					<div class="row-item">


						<input type="checkbox" name="deleteIds" value="${e.id}"> <input
							type="hidden" name="id" value="${e.id}"> <input
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
								<option value="${t.id}"
									<c:if test="${t.id == e.tag}">selected</c:if>>
									${t.tname}</option>
							</c:forEach>
						</select> <select name="situation">
							<c:forEach var="s" items="${situationList}">
								<option value="${s.key}">
									<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
								</option>
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

			</form>
		</div>
	</div>

	<div class="scard">

		<div class="header" onclick="toggleContent('cat${st.index}_s', this)">

			<span> <select name="situetion" id="select_menu">
					<option value="1">通勤時</option>
					<option value="2">昼休み</option>
					<option value="3">休日</option>
			</select> の支出合計
			</span> <span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
				class="arrow">▼</span>

		</div>

		<div id="cat${st.index}_s" class="detail" style="display: none;">
			<form method="post" action="ListServlet">
				<c:forEach var="e" items="${categoryIncomeList}">


					<div class="row-item">

						<input type="checkbox" name="deleteIds" value="${e.id}"> <input
							type="hidden" name="id" value="${e.id}"> <input
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
								<option value="${t.id}"
									<c:if test="${t.id == e.tag}">selected</c:if>>
									${t.tname}</option>
							</c:forEach>
						</select> <select name="situation">
							<c:forEach var="s" items="${situationList}">
								<option value="${s.key}">
									<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
								</option>
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

			</form>
		</div>
	</div>

	<div class="tagcard">

		<div class="header"
			onclick="toggleContent('cat${st.index}_tag', this)">

			<span> <select name="tag" id="select_menu">
					<option value="1">セール</option>
					<option value="2">曖昧</option>
					<option value="3">ご褒美</option>
			</select> の支出合計
			</span> <span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
				class="arrow">▼</span>

		</div>

		<div id="cat${st.index}_tag" class="detail" style="display: none;">
			<form method="post" action="ListServlet">
				<c:forEach var="e" items="${categoryIncomeList}">


					<div class="row-item">

						<input type="checkbox" name="deleteIds" value="${e.id}"> <input
							type="hidden" name="id" value="${e.id}"> <input
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
								<option value="${t.id}"
									<c:if test="${t.id == e.tag}">selected</c:if>>
									${t.tname}</option>
							</c:forEach>
						</select> <select name="situation">
							<c:forEach var="s" items="${situationList}">
								<option value="${s.key}">
									<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
								</option>
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

			</form>
		</div>
	</div>

	<div class="ecard">

		<div class="header" onclick="toggleContent('cat${st.index}_e', this)">

			<span> <select name="tag" id="select_menu">
					<option value="1">😐</option>
					<option value="2">😊</option>
					<option value="3">😢</option>
					<option value="4">😡</option>
			</select> の支出合計
			</span> <span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
				class="arrow">▼</span>

		</div>

		<div id="cat${st.index}_e" class="detail" style="display: none;">
			<form method="post" action="ListServlet">
				<c:forEach var="e" items="${categoryIncomeList}">



					<div class="row-item">
						<input type="checkbox" name="deleteIds" value="${e.id}"> <input
							type="hidden" name="id" value="${e.id}"> <input
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
								<option value="${t.id}"
									<c:if test="${t.id == e.tag}">selected</c:if>>
									${t.tname}</option>
							</c:forEach>
						</select> <select name="situation">
							<c:forEach var="s" items="${situationList}">
								<option value="${s.key}">
									<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
								</option>
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

			</form>
		</div>
	</div>


	<div id="patience" class="content">
		<div class="calendar">
			<input type="month" name="calendar">
		</div>

		<c:forEach var="entry" items="${patienceCategoryMap}" varStatus="st">

			<div class="tcard">

				<div class="header"
					onclick="toggleContent('cat${st.index}_pt', this)">

					<span> ${entry.key} の我慢できたもの合計 </span> <span class="amount">
						¥${patienceTotalMap[entry.key]}</span> <span class="arrow">▼</span>

				</div>

				<div id="cat${st.index}_pt" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
						<c:forEach var="e" items="${entry.value}">


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
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}">
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
										</option>
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

					</form>
				</div>



			</div>

			<div class="ccard">

				<div class="header"
					onclick="toggleContent('cat${st.index}_c', this)">

					<c:forEach var="c" items="${categoryList}">
						<option value="${c.key}">${c.value}</option>
					</c:forEach>
					<span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
						class="arrow">▼</span>

				</div>

				<div id="cat${st.index}_c" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
						<c:forEach var="e" items="${entry.value}">

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
										<option value="${t.id}"
											<c:if test="${t.id == e.tag}">selected</c:if>>
											${t.tname}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}">
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
										</option>
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

					</form>
				</div>
			</div>

			<div class="scard">

				<div class="header"
					onclick="toggleContent('cat${st.index}_s', this)">

					<span> <select name="situetion" id="select_menu">
							<option value="1">通勤時</option>
							<option value="2">昼休み</option>
							<option value="3">休日</option>
					</select> に我慢できたもの合計
					</span> <span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
						class="arrow">▼</span>

				</div>

				<div id="cat${st.index}_s" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
						<c:forEach var="e" items="${entry.value}">


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
										<option value="${t.id}"
											<c:if test="${t.id == e.tag}">selected</c:if>>
											${t.tname}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}">
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
										</option>
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

					</form>
				</div>
			</div>

			<div class="ecard">

				<div class="header"
					onclick="toggleContent('cat${st.index}_e', this)">

					<span> <select name="tag" id="select_menu">
							<option value="1">😐</option>
							<option value="2">😊</option>
							<option value="3">😢</option>
							<option value="4">😡</option>
					</select> の支出合計
					</span> <span class="amount"> ¥${categoryTotalMap[entry.key]} </span> <span
						class="arrow">▼</span>

				</div>

				<div id="cat${st.index}_e" class="detail" style="display: none;">
					<form method="post" action="ListServlet">
						<c:forEach var="e" items="${entry.value}">



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
										<option value="${t.id}"
											<c:if test="${t.id == e.tag}">selected</c:if>>
											${t.tname}</option>
									</c:forEach>
								</select> <select name="situation">
									<c:forEach var="s" items="${situationList}">
										<option value="${s.key}">
											<c:if test="${s.key == e.situation}">selected
                                            </c:if>> ${s.value}
										</option>
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

					</form>
				</div>
		</c:forEach>

	</div>







</body>

<footer>

	<!--フッター-->
	<div id="footer">
		<nav class="nav-bar">
			 <a class="nav-item"
				href="${pageContext.request.contextPath}/HomeServlet">         
				<span class="icon">🏠</span>          <span>ホーム</span>       
			</a>        <a class="nav-item "
				href="${pageContext.request.contextPath}/Regist1Servlet">
				        <span class="icon">✏️</span>         <span>登録</span>       
			</a>       <a class="nav-item active"
				href="${pageContext.request.contextPath}/ListServlet">         <span
				class="icon">📋</span>         <span>一覧</span>       
			</a>       <a class="nav-item"
				href="${pageContext.request.contextPath}/LoginServlet">         <span
				class="icon">🚪</span>         <span>ログアウト</span>       
			</a>     
		</nav>
	</div>
	<!--フッター終わり-->
</footer>


<script>
	
</script>



</html>