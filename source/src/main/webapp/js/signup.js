window.onload = function() {

    let formObj = document.getElementById('signup_form');
    let errorMessageObj = document.getElementById('error_message');

    formObj.onsubmit = function(event) {

        // 未入力チェック
        if (!formObj.id.trim() || !formObj.pw.trim()) {
            errorMessageObj.textContent = '※IDとパスワードを入力してください';
            event.preventDefault();
            return;
        }
    }
};