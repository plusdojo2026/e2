/**
 * ログイン画面のjavascript作成
 */

let formObj = document.getElementById('login_form');

formObj.onsubmit = function(event) {

    if (formObj.id.value === '' || formObj.pw.value === '') {

        alert('ユーザーIDとパスワードを入力してください');

        event.preventDefault();
    }
};
function togglePassword() {

    const pw = document.getElementById("pw");

    if (pw.type === "password") {
        pw.type = "text";
    } else {
        pw.type = "password";
    }
}
