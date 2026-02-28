package BT2.business;
import BT2.config.ConnectionDB;
import BT2.model.Task;
import BT2.model.TaskStatusEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManagement {
    public void addTask(String taskName, String status){
        try (
                Connection connection = ConnectionDB.getConnection();
                CallableStatement callableStatement = connection.prepareCall("call add_task(?,?)");
        ){

            callableStatement.setString(1, taskName);
            callableStatement.setString(2, status);

            callableStatement.executeUpdate();

            System.out.println("Thêm công việc mới thành công !");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    };

    public List<Task> listTasks(){
        List<Task> tasks = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection()) {
            connection.setAutoCommit(false);

            try (CallableStatement cs = connection.prepareCall("call list_tasks(?)")) {
                cs.registerOutParameter(1, Types.REF_CURSOR);
                cs.execute();

                ResultSet rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("task_name"),
                        TaskStatusEnum.valueOf(rs.getString("status"))
                    );
                   tasks.add(task);
                }
                rs.close();
            }
            connection.commit();
            connection.setAutoCommit(true); // Bật lại auto-commit

        } catch (SQLException e) {
            System.out.println("Lỗi: khi lấy danh sách công việc: " + e.getMessage());
        }

        return tasks;

    };

    public boolean isExist(int id) {
        String sql = "SELECT COUNT(id) FROM tasks WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi kiểm tra ID: " + e.getMessage());
        }
        return false;
    }

    public boolean updateTaskStatus(int taskId, String status) {
        if (!isExist(taskId)) {
            return false;
        }

        try (Connection connection = ConnectionDB.getConnection();
             CallableStatement cs = connection.prepareCall("call update_task_status(?,?)")) {

            cs.setInt(1, taskId);
            cs.setString(2, status);
            cs.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteTask(int taskId) {
        if (!isExist(taskId)) {
            return false;
        }

        try (Connection connection = ConnectionDB.getConnection();
             CallableStatement cs = connection.prepareCall("call delete_task(?)")) {

            cs.setInt(1, taskId);
            cs.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Task> searchTaskByName(String taskName) {
        List<Task> tasks = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection()) {
            connection.setAutoCommit(false);

            try (CallableStatement cs = connection.prepareCall("call search_task_by_name(?, ?)")) {

                cs.setString(1, taskName);
                cs.registerOutParameter(2, Types.REF_CURSOR);

                cs.execute();
                ResultSet rs = (ResultSet) cs.getObject(2);

                while (rs.next()) {
                    Task task = new Task(
                            rs.getInt("id"),
                            rs.getString("task_name"),
                            TaskStatusEnum.valueOf(rs.getString("status"))
                    );
                    tasks.add(task);
                }
                rs.close();
            }

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm công việc: " + e.getMessage());
        }

        return tasks;
    };

    public void taskStatistics(){
        try (
                Connection connection = ConnectionDB.getConnection();
                CallableStatement callableStatement = connection.prepareCall("call task_statistics(?,?)");
        ){

            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.registerOutParameter(2, Types.INTEGER);

            callableStatement.execute();
            System.out.println("Số công việc hoàn thành: "+callableStatement.getInt(1));
            System.out.println("Số công việc Chưa hoàn thành: "+callableStatement.getInt(2));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    };
}
