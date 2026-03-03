package business;

import config.ConnectionDB;
import exception.ProductException;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    // Kiểm tra Tên sản phẩm trùng lặp
    public boolean isProductNameExist(String name, int excludeId) {
        String sql = "SELECT COUNT(Product_Id) FROM Product WHERE Product_Name = ? AND Product_Id != ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, excludeId); // Bỏ qua ID hiện tại khi đang ở chức năng Update
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi kiểm tra trùng lặp tên: " + e.getMessage());
        }
        return false;
    }

    // Lấy thông tin 1 sản phẩm theo ID
    public Product getProductById(int id) {
        try (Connection conn = ConnectionDB.getConnection()) {
            conn.setAutoCommit(false);
            try (CallableStatement cs = conn.prepareCall("call get_product_by_id(?, ?)")) {

                cs.setInt(1, id);
                cs.registerOutParameter(2, Types.REF_CURSOR);
                cs.execute();

                ResultSet rs = (ResultSet) cs.getObject(2);
                if (rs.next()) {
                    return new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getFloat("product_price"),
                            rs.getString("product_title"),
                            rs.getDate("product_created").toLocalDate(),
                            rs.getString("product_catalog"),
                            rs.getInt("product_status")
                    );
                }
                rs.close();
            }
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Lỗi tìm kiếm sản phẩm theo ID: " + e.getMessage());
        }
        return null;
    }

    // 1. Lấy tất cả danh sách sản phẩm
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection()) {
            conn.setAutoCommit(false);

            try (CallableStatement cs = conn.prepareCall("call get_all_products(?)")) {
                cs.registerOutParameter(1, Types.REF_CURSOR);
                cs.execute();

                ResultSet rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Product p = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getFloat("product_price"),
                            rs.getString("product_title"),
                            rs.getDate("product_created").toLocalDate(),
                            rs.getString("product_catalog"),
                            rs.getInt("product_status")
                    );
                    products.add(p);
                }
                rs.close();
            }
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            System.err.println("Lỗi lấy danh sách sản phẩm: " + e.getMessage());
        }
        return products;
    }

    // 2. Thêm mới sản phẩm
    public void addProduct(Product p) throws ProductException {
        // Validate Unique
        if (isProductNameExist(p.getName(), 0)) {
            throw new ProductException("Lỗi: Tên sản phẩm '" + p.getName() + "' đã tồn tại trong hệ thống!");
        }

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall("call add_product(?, ?, ?, ?, ?, ?)")) {

            cs.setString(1, p.getName());
            cs.setFloat(2, p.getPrice());
            cs.setString(3, p.getTitle());
            cs.setDate(4, Date.valueOf(p.getCreated())); // Parse LocalDate to java.sql.Date
            cs.setString(5, p.getCatalog());
            cs.setInt(6, p.getStatus());

            cs.executeUpdate();
            System.out.println("Thêm sản phẩm mới thành công!");

        } catch (SQLException e) {
            throw new ProductException("Lỗi hệ thống khi thêm sản phẩm: " + e.getMessage());
        }
    }

    // 3. Cập nhật sản phẩm
    public void updateProduct(int id, Product p) throws ProductException {
        // Validate ID tồn tại
        if (getProductById(id) == null) {
            throw new ProductException("Lỗi: Không tìm thấy sản phẩm có ID = " + id);
        }
        // Validate Unique (Ngoại trừ chính sản phẩm đang update)
        if (isProductNameExist(p.getName(), id)) {
            throw new ProductException("Lỗi: Tên sản phẩm '" + p.getName() + "' đã bị trùng với sản phẩm khác!");
        }

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall("call update_product(?, ?, ?, ?, ?, ?, ?)")) {

            cs.setInt(1, id);
            cs.setString(2, p.getName());
            cs.setFloat(3, p.getPrice());
            cs.setString(4, p.getTitle());
            cs.setDate(5, Date.valueOf(p.getCreated()));
            cs.setString(6, p.getCatalog());
            cs.setInt(7, p.getStatus());

            cs.executeUpdate();
            System.out.println("Cập nhật sản phẩm thành công!");

        } catch (SQLException e) {
            throw new ProductException("Lỗi hệ thống khi cập nhật: " + e.getMessage());
        }
    }

    // 4. Xóa sản phẩm
    public void deleteProduct(int id) throws ProductException {
        if (getProductById(id) == null) {
            throw new ProductException("Lỗi: Không tìm thấy sản phẩm có ID = " + id);
        }

        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall("call delete_product(?)")) {

            cs.setInt(1, id);
            cs.executeUpdate();
            System.out.println("Xóa sản phẩm thành công!");

        } catch (SQLException e) {
            throw new ProductException("Lỗi hệ thống khi xóa: " + e.getMessage());
        }
    }

    // 5. Tìm kiếm theo tên (Tương đối)
    public List<Product> searchProductByName(String name) {
        List<Product> products = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection()) {
            conn.setAutoCommit(false);

            try (CallableStatement cs = conn.prepareCall("call search_product_by_name(?, ?)")) {
                cs.setString(1, name);
                cs.registerOutParameter(2, Types.REF_CURSOR);
                cs.execute();

                ResultSet rs = (ResultSet) cs.getObject(2);
                while (rs.next()) {
                    Product p = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getFloat("product_price"),
                            rs.getString("product_title"),
                            rs.getDate("product_created").toLocalDate(),
                            rs.getString("product_catalog"),
                            rs.getInt("product_status")
                    );
                    products.add(p);
                }
                rs.close();
            }
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm sản phẩm: " + e.getMessage());
        }
        return products;
    }

    // 6. Sắp xếp sản phẩm theo giá tăng dần
    public List<Product> getProductsSortedByPrice() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection()) {
            conn.setAutoCommit(false);

            try (CallableStatement cs = conn.prepareCall("call get_products_sorted_by_price(?)")) {
                cs.registerOutParameter(1, Types.REF_CURSOR);
                cs.execute();

                ResultSet rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Product p = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getFloat("product_price"),
                            rs.getString("product_title"),
                            rs.getDate("product_created").toLocalDate(),
                            rs.getString("product_catalog"),
                            rs.getInt("product_status")
                    );
                    products.add(p);
                }
                rs.close();
            }
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            System.err.println("Lỗi khi sắp xếp sản phẩm: " + e.getMessage());
        }
        return products;
    }

    // 7. Thống kê số lượng sản phẩm theo danh mục
    public void statProductByCatalog() {
        try (Connection conn = ConnectionDB.getConnection()) {
            conn.setAutoCommit(false);

            try (CallableStatement cs = conn.prepareCall("call stat_product_by_catalog(?)")) {
                cs.registerOutParameter(1, Types.REF_CURSOR);
                cs.execute();

                ResultSet rs = (ResultSet) cs.getObject(1);

                System.out.println("\n===== THỐNG KÊ SẢN PHẨM THEO DANH MỤC =====");
                System.out.printf("+%s+%s+\n", "-".repeat(25), "-".repeat(15));
                System.out.printf("| %-23s | %-13s |\n", "Danh mục (Catalog)", "Số lượng SP");
                System.out.printf("+%s+%s+\n", "-".repeat(25), "-".repeat(15));

                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    System.out.printf("| %-23s | %-13d |\n",
                            rs.getString("product_catalog"),
                            rs.getInt("quantity"));
                }

                if(!hasData) {
                    System.out.printf("| %-23s | %-13s |\n", "Chưa có dữ liệu", "0");
                }
                System.out.printf("+%s+%s+\n", "-".repeat(25), "-".repeat(15));

                rs.close();
            }
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            System.err.println("Lỗi khi thống kê danh mục: " + e.getMessage());
        }
    }
}

