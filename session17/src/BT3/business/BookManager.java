package BT3.business;

import BT3.config.ConnectionDB;
import BT3.exception.LibraryException;
import BT3.model.Book;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    public boolean isDuplicate(String title, String author) {
        String sql = "SELECT COUNT(id) FROM books WHERE title = ? AND author = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExist(int id) {
        String sql = "SELECT COUNT(id) FROM books WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addBook(Book book) throws LibraryException {
        if (isDuplicate(book.getTitle(), book.getAuthor())) {
            throw new LibraryException("Cuốn sách này (cùng tên và tác giả) đã tồn tại trong thư viện!");
        }

        String sql = "INSERT INTO books (title, author, published_year, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPublishedYear());
            pstmt.setBigDecimal(4, book.getPrice()); // Dùng setBigDecimal

            int count = pstmt.executeUpdate();
            if (count == 0) {
                throw new LibraryException("Thêm sách thất bại do lỗi cơ sở dữ liệu!");
            }
        } catch (SQLException e) {
            throw new LibraryException("Lỗi hệ thống: " + e.getMessage());
        }
    }

    public void updateBook(int id, Book book) throws LibraryException {
        if (!isExist(id)) {
            throw new LibraryException("Không tìm thấy sách có ID = " + id);
        }

        String sql = "UPDATE books SET title = ?, author = ?, published_year = ?, price = ? WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPublishedYear());
            pstmt.setBigDecimal(4, book.getPrice()); // Dùng setBigDecimal
            pstmt.setInt(5, id);

            int count = pstmt.executeUpdate();
            if (count == 0) {
                throw new LibraryException("Cập nhật thất bại do lỗi cơ sở dữ liệu!");
            }
        } catch (SQLException e) {
            throw new LibraryException("Lỗi hệ thống: " + e.getMessage());
        }
    }

    public void deleteBook(int id) throws LibraryException {
        if (!isExist(id)) {
            throw new LibraryException("Không tìm thấy sách có ID = " + id);
        }

        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int count = pstmt.executeUpdate();
            if (count == 0) {
                throw new LibraryException("Xóa thất bại do lỗi cơ sở dữ liệu!");
            }
        } catch (SQLException e) {
            throw new LibraryException("Lỗi hệ thống: " + e.getMessage());
        }
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author ILIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + author + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("published_year"),
                        rs.getBigDecimal("price") // Dùng getBigDecimal
                ));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return books;
    }

    public List<Book> listAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY id";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("published_year"),
                        rs.getBigDecimal("price") // Dùng getBigDecimal
                ));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return books;
    }
}