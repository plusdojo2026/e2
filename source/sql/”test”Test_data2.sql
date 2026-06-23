USE kakemi_db;

-- =====================
-- users
-- =====================
INSERT INTO users (id, pw)
VALUES ('test', 'abc');


-- =====================
-- expenses（支出）3件
-- =====================
INSERT INTO expenses
(user_id, amount, emotion, category, situation, item_name, memo, tag, created_at)
VALUES
('test', 1200, '1', '1', '1', 'ラーメン', '昼ごはん', '1', '2026-06-01'),
('test', 1500, '2', '2', '2', '電車代', '通勤', '2', '2026-06-02'),
('test', 2500, '3', '3', '1', '映画', '気晴らし', '3', '2026-06-03'),
('test', 3000, '4', '1', '2', '外食', 'ディナー', '1', '2026-06-04');

-- =====================
-- incomes（収入）3件
-- =====================
INSERT INTO incomes (user_id, amount, emotion, category, created_at)
VALUES 
('test', 10000, '1', '1', '2026-06-15'), 
('test', 8000,  '2', '2', '2026-06-16'), 
('test', 3000,  '3', '3', '2026-06-17'), 
('test', 2000,  '4', '1', '2026-06-18'); 


-- =====================
-- patience（我慢）3件
-- =====================
INSERT INTO patience
(user_id, amount, emotion, category, situation, item_name, memo, created_at)
VALUES
('test', 1500, '1', '1', '1', 'ラーメン', '節約した', '2026-06-01'),
('test', 2000, '2', '2', '2', '交通費', '我慢した', '2026-06-02'),
('test', 1800, '3', '3', '1', '映画', '見送り', '2026-06-03'),
('test', 1200, '4', '1', '2', '外食', '満足', '2026-06-04');



-- =====================
-- budgets（予算）
-- =====================
INSERT INTO budgets (user_id, goal_amount, budget_amount)
VALUES ('test', 100000, 50000);