<script>
		/* HTML要素をオブジェクトとして取得する */
		let formObj = document.getElementById('Signup_form');
		let errorMessageObj = document.getElementById('error_message');

		/* [実行]ボタンをクリックしたときの処理 */
		formObj.onsubmit = function(event) {
			/*IDを必須入力項目とします */
			if (!formObj.name.value) {
				errorMessageObj.textContent = '※IDを入力してください';
				event.preventDefault();
			} else {
				/* エラーメッセージを消します */
				errorMessageObj.textContent = null;
				/* 確認ダイアログボックスを表示します */
				if (window.confirm('登録します。よろしいですか？') === false) {
					event.preventDefault();
				}
			}
		};

		/* [リセット]ボタンをクリックしたときの処理 */
		formObj.onreset = function() {
			/* エラーメッセージを消します */
			errorMessageObj.textContent = null;
		};
	</script>