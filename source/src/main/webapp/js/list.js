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

    //開閉トグル▲▼用

    function toggleContent(id, header) {

        const detail =
            document.getElementById(id);

        const arrow =
            header.querySelector(".arrow");

        if (detail.style.display === "none") {

            detail.style.display = "block";
            arrow.textContent = "▼";

        } else {

            detail.style.display = "none";
            arrow.textContent = "▲";

        }
    }

