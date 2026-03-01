package BT4.business;

import BT4.config.ConnectionDB;
import BT4.exception.ShopException;
import BT4.model.Customer;
import BT4.model.Order;
import BT4.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    public boolean isProductNameExist(String name) {
        String sql = "SELECT COUNT(id) FROM product WHERE name = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isCustomerExist(int id) {
        String sql = "SELECT COUNT(id) FROM customer WHERE id = ?";
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

    // Kiểm tra email trùng lặp (ngoại trừ chính khách hàng)
    public boolean isEmailExist(String email, int customerId) {
        String sql = "SELECT COUNT(id) FROM customer WHERE email = ? AND id != ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setInt(2, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getBigDecimal("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addProduct(Product product) throws ShopException {
        if (isProductNameExist(product.getName())) {
            throw new ShopException("Sản phẩm có tên '" + product.getName() + "' đã tồn tại!");
        }

        String sql = "INSERT INTO product (name, price) VALUES (?,?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setBigDecimal(2, product.getPrice());

            if (pstmt.executeUpdate() == 0) {
                throw new ShopException("Lỗi: Thêm sản phẩm thất bại do lỗi hệ thống!");
            }

        } catch (SQLException e) {
            throw new ShopException("Lỗi hệ thống: " + e.getMessage());
        }
    }

    public void updateCustomer(int customerId, Customer customer) throws ShopException {
        if (!isCustomerExist(customerId)) {
            throw new ShopException("Không tìm thấy khách hàng có ID = " + customerId);
        }

        if (isEmailExist(customer.getEmail(), customerId)) {
            throw new ShopException("Email '" + customer.getEmail() + "' đã được khách hàng khác sử dụng!");
        }

        String sql = "UPDATE customer SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setInt(3, customerId);

            if (pstmt.executeUpdate() == 0) {
                throw new ShopException("Cập nhật thất bại do lỗi hệ thống!");
            }
        } catch (SQLException e) {
            throw new ShopException("Lỗi SQL: " + e.getMessage());
        }
    }

    public void createOrder(Order order) throws ShopException {
        if (!isCustomerExist(order.getCustomerId())) {
            throw new ShopException("Khách hàng có ID = " + order.getCustomerId() + " không tồn tại!");
        }

        String sql = "INSERT INTO orders (customer_id, order_date, total_amount) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getCustomerId());
            // Chuyển java.time.LocalDate sang java.sql.Date
            pstmt.setDate(2, Date.valueOf(order.getOrderDate()));
            pstmt.setBigDecimal(3, order.getTotalAmount());

            if (pstmt.executeUpdate() == 0) {
                throw new ShopException("Tạo đơn hàng thất bại do lỗi hệ thống!");
            }
        } catch (SQLException e) {
            throw new ShopException("Lỗi SQL: " + e.getMessage());
        }
    }

    public List<Order> listAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY id DESC";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getDate("order_date").toLocalDate(), // Chuyển ngược SQL Date về LocalDate
                        rs.getBigDecimal("total_amount")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
        }
        return orders;
    }

    public List<Order> getOrdersByCustomer(int customerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ? ORDER BY id DESC";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getBigDecimal("total_amount")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
        }
        return orders;
    }
}
