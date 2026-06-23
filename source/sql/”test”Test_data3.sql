USE kakemi_db;

-- =====================
-- users
-- =====================
INSERT INTO users (id, pw)
VALUES ('test', 'abc');


-- =====================
-- expenses（支出）5件
-- =====================
INSERT INTO expenses
(user_id, amount, emotion, category, situation, item_name, memo, tag, created_at)
VALUES
('test', 1200, '2', '1', '1', 'ラーメン', '昼ごはん', '1', '2026-06-01'),
('test', 2500, '3', '3', '2', '映画', '気晴らし', '2', '2026-06-02'),
('test', 800,  '1', '2', '1', '電車代', '',        '1', '2026-06-03');
('test',2000,  '3', '4', '3', '日用品', '生活用品', '3', '2026-06-04');
('test',2000,  '4', '2', '1, 'コップ', 'プレゼント', '2', '2026-06-05');

-- =====================
-- incomes（収入）3件
-- =====================
INSERT INTO incomes (user_id, amount, emotion, category, created_at)
VALUES 
('test', 10000, '2', '1', '2026-06-15'),
('test', 8000,  '1', '2', '2026-06-16'),
('test', 3000,  '2', '3', '2026-06-17'); 

-- =====================
-- patience（我慢）3件
-- =====================
INSERT INTO patience
(user_id, amount, emotion, category, situation, item_name, memo, created_at)
VALUES
('test', 1500, '1', '1', '1', 'ラーメン', '節約した', '2026-06-01'),
('test', 2000, '4', '3', '2', '映画', '我慢成功', '2026-06-02'),
('test', 1000, '2', '2', '1', '交通費', '', '2026-06-03');


-- =====================
-- budgets（予算）
-- =====================
INSERT INTO budgets (user_id, goal_amount, budget_amount)
VALUES ('test', 100000, 50000);