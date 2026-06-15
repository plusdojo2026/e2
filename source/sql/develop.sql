Drop database if exists kakemi_db;
CREATE DATABASE kakemi_db;
USE kakemi_db
CREATE TABLE users (id VARCHAR(100) PRIMARY KEY,pw VARCHAR(255) NOT NULL,name VARCHAR(50) NOT NULL);

-- CREATE TABLE categories (id INT AUTO_INCREMENT PRIMARY KEY,category_name VARCHAR(50) NOT NULL);
-- CREATE TABLE tags (id INT AUTO_INCREMENT PRIMARY KEY,tag_name VARCHAR(50) NOT NULL);
-- CREATE TABLE emotions (id INT AUTO_INCREMENT PRIMARY KEY,emotion_name VARCHAR(50) NOT NULL);
-- CREATE TABLE situations(id INT AUTO_INCREMENT PRIMARY KEY,situ_name VARCHAR(50) NOT NULL);


CREATE TABLE expenses(
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(100) NOT NULL,
  amount INT NOT NULL,
  emotion VARCHAR(50) NOT NULL,
  category VARCHAR(50),
  situation VARCHAR(50),
  item_name VARCHAR(100),
  memo VARCHAR(255),
  created_at DATE NOT NULL,
  tag VARCHAR(50)
);
CREATE TABLE incomes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(100) NOT NULL,
  amount INT NOT NULL,
  emotion VARCHAR(50) NOT NULL,
  category VARCHAR(50),
  created_at DATE NOT NULL
);
CREATE TABLE patience (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(100) NOT NULL,
  amount INT NOT NULL,
  created_at DATE NOT NULL,
  category VARCHAR(50),
  item_name VARCHAR(100),
  memo VARCHAR(255),
  emotion VARCHAR(50) NOT NULL,
  situation VARCHAR(50)
);

CREATE TABLE budgets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    goal_amount INT,
    budget_amount INT
);