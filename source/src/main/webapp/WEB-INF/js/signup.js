window.onload = function() {

   let formObj = document.getElementById('signup_form');
	let errorMessageObj = document.getElementById('error_message');

    formObj.onsubmit = function(event) {

        if (!formObj.id.value || !formObj.pw.value) {
            errorMessageObj.textContent = '※IDとパスワードを入力してください';
            event.preventDefault();
        }
    }
};