-- =========================================
-- 1. CREATE DATABASE
-- =========================================
DROP DATABASE IF EXISTS ecommerce_db;
CREATE DATABASE ecommerce_db;
USE ecommerce_db;

-- =========================================
-- 2. USERS TABLE (ISSUE: no PK)
-- =========================================
CREATE TABLE users (
    user_id INT,
    name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100)
);

INSERT INTO users VALUES
(1, 'Ahmed Ali', 'ahmed1@test.com', '123'),
(2, 'Sara Mohamed', 'sara@test.com', '123'),
(3, 'Omar Hassan', 'omar@test.com', '123'),
(4, 'Mona Adel', 'mona@test.com', '123'),
(5, 'Youssef Khaled', 'youssef@test.com', '123'),
(6, 'Laila Ibrahim', 'laila@test.com', '123'),
(7, 'Khaled Samy', 'khaled@test.com', '123'),
(8, 'Nour Hany', 'nour@test.com', '123'),
(9, 'Tamer Fathy', 'tamer@test.com', '123'),
(10, 'Heba Salah', 'heba@test.com', '123'),
(11, 'Ali Zaki', 'ali@test.com', '123'),
(12, 'Salma Nabil', 'salma@test.com', '123'),
(13, 'Karim Adel', 'karim@test.com', '123'),
(14, 'Nada Mostafa', 'nada@test.com', '123'),
(15, 'Duplicate Ahmed', 'dup@test.com', '123'),
(1, 'Ahmed Duplicate', 'dup2@test.com', '123'); -- hidden issue


-- =========================================
-- 3. PRODUCTS TABLE
-- =========================================
CREATE TABLE products (
    product_id INT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10,2),
    stock INT
);

INSERT INTO products VALUES
(1, 'Laptop', 15000, 10),
(2, 'Phone', 8000, 20),
(3, 'Tablet', 6000, 15),
(4, 'Monitor', 3000, 12),
(5, 'Keyboard', 200, 100),
(6, 'Mouse', 150, 120),
(7, 'Printer', 2500, 8),
(8, 'Camera', 9000, 5),
(9, 'Speaker', 700, 40),
(10, 'Router', 500, 30),
(11, 'SSD', 1200, 25),
(12, 'HDD', 900, 35),
(13, 'Power Bank', 300, 50),
(14, 'Smart Watch', 2000, 18),
(15, 'Headphones', 400, 60);


-- =========================================
-- 4. ORDERS TABLE (ISSUE: no FK)
-- =========================================
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    user_id INT,
    order_date DATE,
    total_amount DECIMAL(10,2)
);

INSERT INTO orders VALUES
(1,1,'2025-01-01',15000),
(2,2,'2025-01-02',8000),
(3,3,'2025-01-03',6000),
(4,4,'2025-01-04',3000),
(5,5,'2025-01-05',200),
(6,6,'2025-01-06',150),
(7,7,'2025-01-07',2500),
(8,8,'2025-01-08',9000),
(9,9,'2025-01-09',700),
(10,10,'2025-01-10',500),
(11,11,'2025-01-11',1200),
(12,12,'2025-01-12',900),
(13,13,'2025-01-13',300),
(14,14,'2025-01-14',2000),
(15,15,'2025-01-15',400),
(16,99,'2025-01-16',1000); -- hidden issue


-- =========================================
-- 5. ORDER_ITEMS TABLE (ISSUE: no FK)
-- =========================================
CREATE TABLE order_items (
    id INT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT
);

INSERT INTO order_items VALUES
(1,1,1,1),
(2,2,2,1),
(3,3,3,1),
(4,4,4,1),
(5,5,5,2),
(6,6,6,1),
(7,7,7,1),
(8,8,8,1),
(9,9,9,1),
(10,10,10,1),
(11,11,11,1),
(12,12,12,1),
(13,13,13,2),
(14,14,14,1),
(15,15,15,1),
(16,16,20,1); -- hidden issue


-- =========================================
-- 6. PAYMENTS TABLE (ISSUE: negative amount)
-- =========================================
CREATE TABLE payments (
    payment_id INT PRIMARY KEY,
    order_id INT,
    amount DECIMAL(10,2),
    method VARCHAR(50)
);

INSERT INTO payments VALUES
(1,1,15000,'Card'),
(2,2,8000,'Cash'),
(3,3,6000,'Card'),
(4,4,3000,'Card'),
(5,5,200,'Cash'),
(6,6,150,'Cash'),
(7,7,2500,'Card'),
(8,8,9000,'Card'),
(9,9,700,'Cash'),
(10,10,500,'Cash'),
(11,11,1200,'Card'),
(12,12,900,'Card'),
(13,13,300,'Cash'),
(14,14,2000,'Card'),
(15,15,-400,'Card'); -- hidden issue


-- =========================================
-- 7. REVIEWS TABLE (ISSUE: invalid rating)
-- =========================================
CREATE TABLE reviews (
    review_id INT PRIMARY KEY,
    product_id INT,
    user_id INT,
    rating INT,
    comment TEXT
);

INSERT INTO reviews VALUES
(1,1,1,5,'Great'),
(2,2,2,4,'Good'),
(3,3,3,3,'Average'),
(4,4,4,2,'Bad'),
(5,5,5,1,'Very bad'),
(6,6,6,5,'Excellent'),
(7,7,7,4,'Nice'),
(8,8,8,3,'Okay'),
(9,9,9,2,'Poor'),
(10,10,10,1,'Worst'),
(11,11,11,5,'Perfect'),
(12,12,12,4,'Good'),
(13,13,13,3,'Average'),
(14,14,14,2,'Bad'),
(15,15,15,6,'Invalid'); -- hidden issue


-- =========================================
-- 8. CART TABLE (ISSUE: no PK)
-- =========================================
CREATE TABLE cart (
    user_id INT,
    product_id INT,
    quantity INT
);

INSERT INTO cart VALUES
(1,1,1),
(1,1,2), -- duplicate
(2,2,1),
(3,3,1),
(4,4,1),
(5,5,2),
(6,6,1),
(7,7,1),
(8,8,1),
(9,9,1),
(10,10,1),
(11,11,1),
(12,12,1),
(13,13,2),
(14,14,1),
(15,15,1);


-- =========================================
-- 9. INVENTORY TABLE (ISSUE: negative stock)
-- =========================================
CREATE TABLE inventory (
    product_id INT PRIMARY KEY,
    stock INT
);

INSERT INTO inventory VALUES
(1,10),
(2,20),
(3,15),
(4,12),
(5,100),
(6,120),
(7,8),
(8,5),
(9,40),
(10,30),
(11,25),
(12,35),
(13,50),
(14,18),
(15,-3); -- hidden issue




INSERT INTO users (user_id, name, email, password) 
VALUES (99, 'Test User', 'test@test.com', '123');

SELECT * FROM users WHERE user_id = 99;

SELECT name, email FROM users WHERE user_id = 1;

UPDATE users SET name = 'Sara Updated' WHERE user_id = 2;

SET SQL_SAFE_UPDATES = 0;
UPDATE users SET name = 'Sara Updated' WHERE user_id = 2;
SELECT name FROM users WHERE user_id = 2;

INSERT INTO users (user_id, name, email, password) VALUES (99, 'Duplicate 99', 'dup99@test.com', '000');

INSERT INTO users (user_id, name, email, password) VALUES (3, NULL, 'noname@test.com', '123');

INSERT INTO users (user_id, name, email, password) VALUES (4, 'Another User', 'test@test.com', '123');

INSERT INTO products (product_id, name, price, stock) VALUES (99, 'Smart TV', 12000.00, 15);
SELECT * FROM products WHERE product_id = 99;


UPDATE products SET price = 14000.00 WHERE product_id = 1;
SELECT price FROM products WHERE product_id = 1;

INSERT INTO products (product_id, name, price, stock) VALUES (100, 'Test Item', -500.00, 10);

INSERT INTO products (product_id, name, price, stock) VALUES (105, 'Test Item 2', 500.00, -5);

INSERT INTO orders (order_id, user_id, order_date, total_amount) VALUES (100, 1, '2026-07-04', 1500.00);
SELECT * FROM orders WHERE order_id = 100;

SELECT * FROM orders WHERE user_id = 2;

INSERT INTO orders (order_id, user_id, order_date, total_amount) VALUES (101, 9999, '2026-07-04', 500.00);

INSERT INTO orders (order_id, user_id, order_date, total_amount) VALUES (102, 3, '2026-07-04', -200.00);

INSERT INTO order_items (id, order_id, product_id, quantity) VALUES (100, 1, 1, 2);

SELECT * FROM order_items WHERE order_id = 1;


INSERT INTO order_items (id, order_id, product_id, quantity) VALUES (101, 1, 9999, 1);

INSERT INTO order_items (id, order_id, product_id, quantity) VALUES (103, 1, 2, -5);

INSERT INTO payments (payment_id, order_id, amount, method) VALUES (100, 1, 1500.00, 'Card');

SELECT * FROM payments WHERE order_id = 1;

INSERT INTO payments (payment_id, order_id, amount, method) VALUES (101, 8888, 500.00, 'Cash');

INSERT INTO payments (payment_id, order_id, amount, method) VALUES (102, 2, -100.00, 'Card');


INSERT INTO reviews (review_id, product_id, user_id, rating, comment) VALUES (100, 1, 1, 5, 'Great product');

SELECT * FROM reviews WHERE product_id = 1;

INSERT INTO reviews (review_id, product_id, user_id, rating, comment) VALUES (101, 2, 2, 6, 'Invalid rating');

INSERT INTO reviews (review_id, product_id, user_id, rating, comment) VALUES (102, 9999, 1, 4, 'Fake product');

INSERT INTO cart (user_id, product_id, quantity) VALUES (1, 3, 2);

SELECT * FROM cart WHERE user_id = 1;


INSERT INTO cart (user_id, product_id, quantity) VALUES (2, 4, -1);


INSERT INTO cart (user_id, product_id, quantity) VALUES (1, 1, 1);


INSERT INTO inventory (product_id, stock) VALUES (99, 50);


SELECT stock FROM inventory WHERE product_id = 1;


INSERT INTO inventory (product_id, stock) VALUES (103, -10);



INSERT INTO inventory (product_id, stock) VALUES (8888, 100);



INSERT INTO orders (order_id, user_id, order_date, total_amount) VALUES (200, 1, '2026-07-04', 500);
INSERT INTO order_items (id, order_id, product_id, quantity) VALUES (200, 200, 1, 1);
INSERT INTO payments (payment_id, order_id, amount, method) VALUES (200, 200, 500, 'Card');


DELETE FROM users WHERE user_id = 1;



