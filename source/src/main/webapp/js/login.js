window.onload = function() {

		/* HTML要素をオブジェクトとして取得する */
		let formObj = document.getElementById('login_form');
		let errorMessageObj = document.getElementById('error_message');

		/* [ログイン]ボタンをクリックしたときの処理 */
		formObj.onsubmit = function(event) {

			// 前回のエラーメッセージを消す
			errorMessageObj.textContent = '';

			if (formObj.id.value === '' || formObj.pw.value === '') {
				errorMessageObj.textContent = 'IDとパスワードを入力してください';
				event.preventDefault();
			}
		};
		/* [リセット]ボタンをクリックしたときの処理 */
		formObj.onreset = function() {
			errorMessageObj.textContent = null;
		};
		};