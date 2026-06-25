
// 今日の日付をセット（サーバー側でセットできない場合のフォールバック）
window.addEventListener('DOMContentLoaded', function () {
    var dateField = document.getElementById('created_at');
    if (!dateField.value) {
        var now = new Date();
        var y = now.getFullYear();
        var m = String(now.getMonth() + 1).padStart(2, '0');
        var d = String(now.getDate()).padStart(2, '0');
        dateField.value = y + '/' + m + '/' + d;
    }
});

// 感情モーダル開閉
document.getElementById('emotionBtn').addEventListener('click', function () {
    document.getElementById('emotionModal').classList.add('open');
});
document.getElementById('modalClose').addEventListener('click', function () {
    document.getElementById('emotionModal').classList.remove('open');
});

// 感情選択
document.querySelectorAll('.emotion-opt').forEach(function (btn) {
    btn.addEventListener('click', function () {
        document.querySelectorAll('.emotion-opt').forEach(function (b) { b.classList.remove('selected'); });
        btn.classList.add('selected');
        document.getElementById('emotionLabel').textContent = btn.textContent;
        document.getElementById('emotion').value = btn.dataset.val;
        document.getElementById('emotionModal').classList.remove('open');
    });
});

// リセット
document.getElementById('resetBtn').addEventListener('click', function () {
    document.getElementById('expenditureForm').reset();
    var now = new Date();
    var y = now.getFullYear();
    var m = String(now.getMonth() + 1).padStart(2, '0');
    var d = String(now.getDate()).padStart(2, '0');
    document.getElementById('created_at').value = y + '-' + m + '-' + d;
    document.getElementById('emotionLabel').textContent = '感情';
    document.getElementById('emotion').value = '';
    document.querySelectorAll('.emotion-opt').forEach(function (b) { b.classList.remove('selected'); });
    document.querySelectorAll('.error-msg').forEach(function (e) { e.style.display = 'none'; });
});

// バリデーション
document.getElementById('expenditureForm').addEventListener('submit', function (e) {
    var valid = true;

    // 日付
    var date = document.getElementById('created_at').value;
    var dateErr = document.getElementById('errDate');
    if  (!/^\d{4}-\d{2}-\d{2}$/.test(date)) {
        dateErr.style.display = 'block'; valid = false;
    } else { dateErr.style.display = 'none'; }

    // 金額
    var amount = document.getElementById('amount').value;
    var amountErr = document.getElementById('errAmount');
    if (!/^\d+$/.test(amount) || parseInt(amount) < 1 || parseInt(amount) > 9999999) {
        amountErr.style.display = 'block'; valid = false;
    } else { amountErr.style.display = 'none'; }

    // 商品名
    
	var itemNameEl = document.getElementById('item_name');
	var itemErr = document.getElementById('errItemName');
	
	if (itemNameEl) {
	    var itemName = itemNameEl.value.trim();
	
	    if (!itemName) {
	        itemErr.style.display = 'block';
	        valid = false;
	    } else {
	        itemErr.style.display = 'none';
	    }
	}

	// カテゴリ
    var category = document.getElementById('category').value;
    var catErr = document.getElementById('errCategory');
    if (!category) {
        catErr.style.display = 'block'; valid = false;
    } else { catErr.style.display = 'none'; }
    
	 // 状況
	var situationEl = document.getElementById('situation');
	var sitErr = document.getElementById('errSituation');
	if (situationEl && sitErr) {
		   if (!situationEl.value) {
		       sitErr.style.display = 'block';
		       valid = false;
		   } else {
		       sitErr.style.display = 'none';
		   }
	}

    
	if (!valid) {e.preventDefault();
	    return false;
	}

});
function showTab(event, id) {
    // 全部非表示
    document.querySelectorAll(".content").forEach(el => {
        el.classList.remove("active");
    });

    // タブの色リセット
    document.querySelectorAll(".tab").forEach(tab => {
        tab.classList.remove("active");
    });

    // 押したやつ表示
    document.getElementById(id).classList.add("active");
    event.currentTarget.classList.add("active");
}