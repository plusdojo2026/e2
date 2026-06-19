function validateBudget() {
    const goal   = document.querySelector('[name="goal_amount"]').value;
    const budget = document.querySelector('[name="budget_amount"]').value;
    const error  = document.getElementById("errorMsg");

    error.textContent = "";

    // null / 空 / 文字 / マイナス 全部まとめてチェック
    if (goal === "" || budget === "" ||
        isNaN(goal) || isNaN(budget) ||
        Number(goal) < 0 || Number(budget) < 0) {

        error.textContent =
            "予算と目標貯金額は0以上の数値を入力してください";
        return false; // ← 送信させない
    }

    return true; // ← OKならPOST
}
