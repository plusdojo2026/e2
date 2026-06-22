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

	if (detail.style.display == "block") {

		detail.style.display = "none";
		arrow.textContent = "▲";

	} else {

		detail.style.display = "block";
		arrow.textContent = "▼";

	}

	if (controlElemenId) {
		const selector = `#${controlElemenId} [id^="${id.split("_")[0]}_"]`;
		const children = document.querySelectorAll(selector);
		children.forEach(el => { el.style.display = 'none'; });
	}





}

window.onload = function() {

	const forms = document.querySelectorAll("form");

	forms.forEach(function(formObj) {

		formObj.onsubmit = function(event) {

			const btn = document.activeElement;

			console.log(btn.value);

			if (btn.value === "delete") {
				if (!confirm("この内容を削除しますか？")) {
					event.preventDefault();
				}
			}

			if (btn.value === "edit") {
				if (!confirm("この内容で編集しますか？")) {
					event.preventDefault();
				}
			}
		};
	});
};

