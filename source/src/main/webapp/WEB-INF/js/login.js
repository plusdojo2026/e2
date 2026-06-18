window.onload = function() {

    let formObj = document.getElementById('login_form');
    let errorMessageObj = document.getElementById('error_message');

	//ログインボタンの処理
    formObj.onsubmit = function(event) {
	//前回表示したエラーメッセージ消す
        errorMessageObj.textContent = '';
	//idpwが未入力の処理
        if (formObj.id.value === '' || formObj.pw.value === '') {
            errorMessageObj.textContent = 'IDとパスワードを入力してください';
            event.preventDefault();
        }
    };
	//リセットボタンの処理
    formObj.onreset = function() {
        errorMessageObj.textContent = '';
    };
};