create database session17;
create schema if not exists bt2;
set search_path to bt2;

CREATE TABLE IF NOT EXISTS tasks
(
    id        SERIAL PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL,
    status    VARCHAR(50)  NOT NULL --  "chưa hoàn thành" hoặc "đã hoàn thành"
);

-- Thêm công việc
CREATE OR REPLACE PROCEDURE add_task(
    in_task_name VARCHAR(255),
    in_status varchar(50)
)
    LANGUAGE plpgsql
AS
$$
BEGIN
    insert into tasks (task_name, status) values (in_task_name, in_status);
END;
$$;



-- Trả về danh sách công việc
CREATE OR REPLACE PROCEDURE list_tasks(INOUT p_cursor refcursor)
    LANGUAGE plpgsql
AS $$
BEGIN
    OPEN p_cursor FOR
        SELECT * FROM tasks ORDER BY id;
END;
$$;


-- Cập nhật trạng thái
CREATE OR REPLACE PROCEDURE update_task_status(
    in_id int,
    in_status varchar(50)
)
    LANGUAGE plpgsql
AS
$$
BEGIN
    update tasks set status = in_status where id = in_id ;
END;
$$;


-- Xóa công việc
CREATE OR REPLACE PROCEDURE delete_task(
    in_id INT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    delete from tasks where id = in_id ;
END;
$$;


-- tìm theo tên
CREATE OR REPLACE PROCEDURE search_task_by_name(
    p_task_name VARCHAR,
    INOUT p_cursor refcursor
)
    LANGUAGE plpgsql
AS $$
BEGIN
    OPEN p_cursor FOR
        SELECT * FROM tasks
        WHERE task_name ILIKE '%' || p_task_name || '%'
        ORDER BY id;
END;
$$;



-- task_statistics
CREATE OR REPLACE PROCEDURE task_statistics(
    OUT out_completed_count INT,
    OUT out_pending_count INT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    select count(id) into out_completed_count from tasks where status = 'HOAN_THANH';
    select count(id) into out_pending_count from tasks where status = 'CHUA_HOAN_THANH';
END;
$$;