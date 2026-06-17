USE kakemi_db;


-- users
INSERT INTO users (id, pw)
VALUES ('test', 'abc');


-- expenses
INSERT INTO expenses 
(user_id, amount, emotion, category, situation, item_name, memo, tag, created_at)
VALUES
('test', 1200, '楽しい', '食費', '外食',     'ラーメン', '昼ごはん',    '食事', '2026-06-01'),
('test', 5000, '後悔',   '買い物','衝動買い', '服',       'セールで購入','衣服', '2026-06-02'),
('test', 800,  '普通',   '日用品','必要',     '洗剤',     '',             '生活', '2026-06-03'),
('test', 2000, '楽しい', '趣味',  'ゲーム',   'ソフト',   '欲しかった',   '趣味', '2026-06-04'),
('test', 1500, '普通',   '食費',  '自炊',     '食材',     'スーパー購入', '食材', '2026-06-05');


-- incomes
INSERT INTO incomes (user_id, amount, emotion, category, created_at)
VALUES 
('test', 10000, '嬉しい', '給料',       '2026-06-15'),
('test', 8000,  '普通',   '副業',       '2026-06-16'),
('test', 12000, '嬉しい', 'ボーナス',   '2026-06-17'),
('test', 3000,  '普通',   'お小遣い',   '2026-06-18'),
('test', 15000, '嬉しい', '給料',       '2026-06-19');


-- patience
INSERT INTO patience 
(user_id, amount, emotion, category, situation, item_name, memo, created_at)
VALUES
('test', 1500, '我慢',   '食費',  '外食したかった', 'ラーメン', '節約成功',      '2026-06-01'),
('test', 3000, '達成感', '買い物','衝動買い防止', '靴',       '買わずに済んだ', '2026-06-02'),
('test', 2000, '普通',   '趣味',  '欲しかった',     'ゲーム',   '',              '2026-06-03'),
('test', 1000, '満足',   '間食',  'お菓子我慢',     'チョコ',   'ダイエット成功','2026-06-04'),
('test', 2500, '達成感', '買い物','買うのを我慢', 'バッグ',   '節約できた',    '2026-06-05');


-- budgets
INSERT INTO budgets (user_id, goal_amount, budget_amount)
VALUES ('test', 100000, 50000);
