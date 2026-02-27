package BT1.business;

import BT1.config.ConnectionDB;
import BT1.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    public void addMovie(String title, String director, int year){
        try (
                Connection connection = ConnectionDB.getConnection();
                CallableStatement callableStatement = connection.prepareCall("call add_movie(?,?,?)");
            ){

                callableStatement.setString(1, title);
                callableStatement.setString(2, director);
                callableStatement.setInt(3, year);

                callableStatement.executeUpdate();

            System.out.println("Thêm phim mới thành công !");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Movie> listMovies() {
        List<Movie> movies = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection()) {
            connection.setAutoCommit(false);

            try (CallableStatement cs = connection.prepareCall("call list_movies(?)")) {
                cs.registerOutParameter(1, Types.REF_CURSOR);
                cs.execute();

                ResultSet rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Movie movie = new Movie(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("director"),
                            rs.getInt("year")
                    );
                    movies.add(movie);
                }
                rs.close();
            }
            connection.commit();
            connection.setAutoCommit(true); // Bật lại auto-commit

        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách phim: " + e.getMessage());
        }

        return movies;
    }

    public boolean updateMovie(int id, String title, String director, int year){
        int count = 0;
        try (
                Connection connection = ConnectionDB.getConnection();
                CallableStatement callableStatement = connection.prepareCall("call update_movie(?,?,?,?)");
        ){
            callableStatement.setInt(1, id);
            callableStatement.setString(2, title);
            callableStatement.setString(3, director);
            callableStatement.setInt(4, year);

            count =callableStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return count > 0;
    }

    public boolean deleteMovie(int id){
        int count = 0;
        try (
                Connection connection = ConnectionDB.getConnection();
                CallableStatement callableStatement = connection.prepareCall("call delete_movie(?)");
        ){
            callableStatement.setInt(1, id);

            count =callableStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return count > 0;
    }

    public boolean isExist(int id) {
        String sql = "SELECT COUNT(id) FROM movies WHERE id = ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra ID: " + e.getMessage());
        }
        return false;
    }
}


