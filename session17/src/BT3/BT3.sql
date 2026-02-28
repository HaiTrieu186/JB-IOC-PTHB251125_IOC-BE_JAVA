create database sesseion17;
create schema if not exists bt3;
set search_path to bt3;

CREATE TABLE IF NOT EXISTS books
(
    id             SERIAL PRIMARY KEY,
    title          VARCHAR(255)   NOT NULL,
    author         VARCHAR(255)   NOT NULL,
    published_year INT            NOT NULL,
    price          DECIMAL(10, 2) NOT NULL
);

-- Thêm sẵn vài cuốn sách để test tính năng hiển thị
INSERT INTO books (title, author, published_year, price)
VALUES ('Số Đỏ', 'Vũ Trọng Phụng', 1936, 45000.00),
       ('Dế Mèn Phiêu Lưu Ký', 'Tô Hoài', 1941, 35000.50),
       ('Nhà Giả Kim', 'Paulo Coelho', 1988, 75000.00),
       ('Làm Đĩ', 'Vũ Trọng Phụng', 1939, 42000.00);

-- Test
SELECT *
FROM books;