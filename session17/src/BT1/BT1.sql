create database session17;
create schema if not exists bt1;
set search_path to bt1;

CREATE TABLE IF NOT EXISTS movies
(
    id       SERIAL PRIMARY KEY,
    title    VARCHAR(255) NOT NULL,
    director VARCHAR(255),
    year     INT
);


-- Thêm phim
CREATE OR REPLACE PROCEDURE add_movie(
    in_title VARCHAR,
    in_director VARCHAR,
    in_year INT
)
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO movies (title, director, year)
    VALUES (in_title, in_director, in_year);
END;
$$;


-- Lấy danh sách phim
-- CREATE OR REPLACE PROCEDURE list_movies()
--     LANGUAGE plpgsql
-- AS
-- $$
-- BEGIN
--     SELECT *
--     FROM movies
--     ORDER BY id;
-- END;
-- $$;
-- ////////////////////// POSTGRES Ko trả về Resultset thông qua procudure được.

CREATE OR REPLACE PROCEDURE list_movies(INOUT p_cursor refcursor)
    LANGUAGE plpgsql
AS $$
BEGIN
    OPEN p_cursor FOR
        SELECT * FROM movies ORDER BY id;
END;
$$;

-- Cập nhật phim
CREATE OR REPLACE PROCEDURE update_movie(
    in_id INT,
    in_title VARCHAR,
    in_director VARCHAR,
    in_year INT
)
    LANGUAGE plpgsql
AS
$$
BEGIN
    update movies
    set title = in_title,
        director= in_director,
        year = in_year
    where id = in_id;
END;
$$;

-- Xóa phim
CREATE OR REPLACE PROCEDURE delete_movie(
    p_id INT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM movies
    WHERE id = p_id;
END;
$$;

select *
from movies;
