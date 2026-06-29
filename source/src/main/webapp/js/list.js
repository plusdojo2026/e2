function showTab(event, id) {
	/*
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
	*/

	if (id === 'income') {
		window.location.href = 'ListServlet';
	} else if (id === 'expense') {
		window.location.href = 'ListServlet2';
	} else if (id === 'patience') {
		window.location.href = 'ListServlet3';
	}
}

//開閉トグル▲▼用

function toggleContent(header, id, selectElementId, controlElemenId) {
	if (selectElementId) {
		const selectedIndex = document.getElementById(selectElementId).selectedIndex;
		id = id + "_" + selectedIndex;
	}
	const detail =
		document.getElementById(id);
	const arrow =
		header.querySelector(".arrow");
	//デフォは全非表示　トグルは▲
	if (detail.style.display === "block") {
		detail.style.display = "none";
		arrow.textContent = "▼";
		return;
	}
	//プルダウンで選択された項目を開く
	if (controlElemenId) {
		const selector = `#${controlElemenId} [id^="${id.split("_")[0]}_"]`;
		const children = document.querySelectorAll(selector);
		children.forEach(el => { el.style.display = 'none'; });
	}
	// 選択されたものを開く
	detail.style.display = "block";
	arrow.textContent = "▲";
}

window.onload = function() {
	const forms = document.querySelectorAll("form");
	forms.forEach(function(formObj) {
		formObj.onsubmit = function(event) {
			const btn = document.activeElement;
			const cnt = formObj.querySelectorAll(
				'input[name="deleteIds"]:checked'
			).length;
			if (btn.value === "削除") {
				//選択０件のとき
				if (cnt === 0) {
					alert("削除するデータを選択してください");
					event.preventDefault();
					return;
				}
				if (!confirm(
					"この" + cnt + "件のデータを削除してもよいですか？"
				)) {
					event.preventDefault();
				}
			}
			if (btn.value === "編集") {
				if (!confirm(
					"このデータを編集してもよいですか？"
				)) {
					event.preventDefault();
				}
			}
		};
	});
};

document.addEventListener("DOMContentLoaded", function() {
	const form = document.querySelector('#expense form[action="ListServlet"]');
	document.querySelector('form[action="ListServlet2"]');
	document.querySelector('form[action="ListServlet3"]');
	const amountSpan = document.querySelector('.tcard .header .amount');
	const tabs = document.querySelector('.tabs');

	if (form && amountSpan && tabs) {
		const wrapper = document.createElement('div');
		wrapper.style.position = 'relative';
		wrapper.style.display = 'flex';
		wrapper.style.alignItems = 'center';
		wrapper.style.padding = '12px 16px';

		const clonedAmount = amountSpan.cloneNode(true);
		clonedAmount.style.fontSize = '1.8rem';
		clonedAmount.style.fontWeight = 'bold';
		// 絶対位置で真ん中に配置
		clonedAmount.style.position = 'absolute';
		clonedAmount.style.left = '50%';
		clonedAmount.style.transform = 'translateX(-50%)';

		wrapper.appendChild(form);
		wrapper.appendChild(clonedAmount);

		tabs.parentNode.insertBefore(wrapper, tabs.nextSibling);

		amountSpan.style.display = 'none';
	}
});

