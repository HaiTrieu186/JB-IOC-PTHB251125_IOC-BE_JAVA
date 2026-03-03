create database session18;
create schema if not exists lt1;
set search_path to lt1;


-- Em thay bit sang int vì default 1 bị lỗi với bit
create table if not exists Product
(
    Product_Id      serial primary key,
    Product_Name    Varchar(100) Not null unique,
    Product_Price   Float        not null check ( Product_Price > 0 ),
    Product_Title   Varchar(200) Not null,
    Product_created Date         Not null,
    Product_catalog Varchar(100) Not null,
    Product_Status  int Default 1
);


-- 1. Lấy tất cả thông tin sản phẩm
CREATE OR REPLACE PROCEDURE get_all_products(INOUT inout_cursor refcursor)
    LANGUAGE plpgsql AS
$$
BEGIN
    OPEN inout_cursor FOR
        SELECT * FROM Product ORDER BY Product_Id;
END;
$$;

-- 2. Kiểm tra sự tồn tại của danh mục
CREATE OR REPLACE PROCEDURE check_catalog_exist(
    in_catalog VARCHAR(100),
    OUT out_is_exist BOOLEAN
)
    LANGUAGE plpgsql AS
$$
BEGIN
    SELECT EXISTS(SELECT 1 FROM Product WHERE Product_catalog = in_catalog) INTO out_is_exist;
END;
$$;

-- 3. Thêm mới một sản phẩm
CREATE OR REPLACE PROCEDURE add_product(
    in_name VARCHAR(100),
    in_price FLOAT,
    in_title VARCHAR(200),
    in_created DATE,
    in_catalog VARCHAR(100),
    in_status INT
)
    LANGUAGE plpgsql AS
$$
BEGIN
    INSERT INTO Product(Product_Name, Product_Price, Product_Title, Product_created, Product_catalog, Product_Status)
    VALUES (in_name, in_price, in_title, in_created, in_catalog, in_status);
END;
$$;

-- 4. Cập nhật một sản phẩm theo mã sản phẩm
CREATE OR REPLACE PROCEDURE update_product(
    in_id INT,
    in_name VARCHAR(100),
    in_price FLOAT,
    in_title VARCHAR(200),
    in_created DATE,
    in_catalog VARCHAR(100),
    in_status INT
)
    LANGUAGE plpgsql AS
$$
BEGIN
    UPDATE Product
    SET Product_Name    = in_name,
        Product_Price   = in_price,
        Product_Title   = in_title,
        Product_created = in_created,
        Product_catalog = in_catalog,
        Product_Status  = in_status
    WHERE Product_Id = in_id;
END;
$$;

-- 5. Xóa một sản phẩm theo mã sản phẩm
CREATE OR REPLACE PROCEDURE delete_product(in_id INT)
    LANGUAGE plpgsql AS
$$
BEGIN
    DELETE FROM Product WHERE Product_Id = in_id;
END;
$$;

-- 6. Lấy thông tin sản phẩm theo mã sản phẩm
CREATE OR REPLACE PROCEDURE get_product_by_id(
    in_id INT,
    INOUT inout_cursor refcursor
)
    LANGUAGE plpgsql AS
$$
BEGIN
    OPEN inout_cursor FOR
        SELECT * FROM Product WHERE Product_Id = in_id;
END;
$$;

-- 7. Tìm kiếm sản phẩm theo tên sản phẩm (tương đối)
CREATE OR REPLACE PROCEDURE search_product_by_name(
    in_name VARCHAR,
    INOUT inout_cursor refcursor
)
    LANGUAGE plpgsql AS
$$
BEGIN
    OPEN inout_cursor FOR
        SELECT *
        FROM Product
        WHERE Product_Name ILIKE '%' || in_name || '%';
END;
$$;

-- 8. Thống kê số lượng sản phẩm theo danh mục
CREATE OR REPLACE PROCEDURE stat_product_by_catalog(
    INOUT inout_cursor refcursor
)
    LANGUAGE plpgsql AS
$$
BEGIN
    OPEN inout_cursor FOR
        SELECT Product_catalog, COUNT(Product_Id) as quantity
        FROM Product
        GROUP BY Product_catalog;
END;
$$;

-- 9. Sắp xếp sản phẩm theo giá tăng dần
CREATE OR REPLACE PROCEDURE get_products_sorted_by_price(INOUT inout_cursor refcursor)
    LANGUAGE plpgsql AS
$$
BEGIN
    OPEN inout_cursor FOR
        SELECT * FROM Product ORDER BY Product_Price ASC;
END;
$$;