create database session17;
create schema if not exists bt4;
set search_path to bt4;

create table product
(
    id    serial primary key,
    name  varchar(50)    not null,
    price decimal(10, 2) not null
);

create table customer
(
    id    serial primary key,
    name  varchar(255) not null,
    email varchar(255) unique
);

-- Orders vì Order trùng
create table orders
(
    id           serial primary key,
    customer_id  int references Customer (id) on delete cascade,
    order_date   date           not null,
    total_amount decimal(10, 2) not null
);

-- THÊM DỮ LIỆU MẪU ĐỂ TEST
INSERT INTO product (name, price)
VALUES ('Laptop Dell XPS', 25000000.00),
       ('Chuột Logitech G102', 450000.00),
       ('Bàn phím cơ Keychron', 1500000.00);

INSERT INTO customer (name, email)
VALUES ('Nguyễn Văn A', 'nguyenvana@gmail.com'),
       ('Trần Thị B', 'tranthib@gmail.com');

INSERT INTO orders (customer_id, order_date, total_amount)
VALUES (1, CURRENT_DATE, 25450000.00),
       (2, CURRENT_DATE, 1500000.00);


SELECT *
FROM product;
SELECT *
FROM customer;
SELECT *
FROM orders;


drop table orders;
drop table customer;
drop table product;

