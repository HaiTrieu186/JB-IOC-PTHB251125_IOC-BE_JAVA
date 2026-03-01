package BT4.presentation;

import BT4.business.OrderManager;
import BT4.exception.ShopException;
import BT4.model.Customer;
import BT4.model.Order;
import BT4.model.Product;
import BT4.util.InputUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final OrderManager orderManager = new OrderManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ ĐƠN HÀNG =====");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Cập nhật thông tin khách hàng");
            System.out.println("3. Tạo đơn hàng mới");
            System.out.println("4. Hiển thị danh sách đơn hàng");
            System.out.println("5. Tìm kiếm đơn hàng theo khách hàng");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn chức năng (0-5): ");

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddProduct(sc);
                    break;
                case 2:
                    handleUpdateCustomer(sc);
                    break;
                case 3:
                    handleCreateOrder(sc);
                    break;
                case 4:
                    handleListAllOrders();
                    break;
                case 5:
                    handleGetOrdersByCustomer(sc);
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 0 đến 5!");
            }
        }
    }

    // 1. Thêm sản phẩm mới
    private static void handleAddProduct(Scanner sc) {
        System.out.println("\n--- THÊM SẢN PHẨM MỚI ---");
        String name = InputUtil.inputString(sc, "Nhập tên sản phẩm: ");
        BigDecimal price = InputUtil.inputBigDecimal(sc, "Nhập giá sản phẩm: ");

        Product p = new Product(0, name, price);
        try {
            orderManager.addProduct(p);
            System.out.println("Thêm sản phẩm thành công!");
        } catch (ShopException e) {
            System.out.println(e.getMessage());
        }
    }

    // 2. Cập nhật khách hàng
    private static void handleUpdateCustomer(Scanner sc) {
        System.out.println("\n--- CẬP NHẬT THÔNG TIN KHÁCH HÀNG ---");
        int id = InputUtil.inputInt(sc, "Nhập ID khách hàng cần sửa: ");

        String newName = InputUtil.inputString(sc, "Nhập tên khách hàng mới: ");
        String newEmail = InputUtil.inputEmail(sc, "Nhập email mới (VD: abc@gmail.com): ");

        Customer c = new Customer(id, newName, newEmail);
        try {
            orderManager.updateCustomer(id, c);
            System.out.println("Cập nhật khách hàng thành công!");
        } catch (ShopException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3. Tạo đơn hàng mới
    private static void handleCreateOrder(Scanner sc) {
        System.out.println("\n--- TẠO ĐƠN HÀNG MỚI ---");

        int customerId = InputUtil.inputInt(sc, "Nhập ID khách hàng mua: ");
        Customer c = orderManager.getCustomerById(customerId);
        if (c == null) {
            System.out.println("Lỗi: Khách hàng có ID = " + customerId + " không tồn tại!");
            return;
        }

        int productId = InputUtil.inputInt(sc, "Nhập ID sản phẩm cần mua: ");
        Product p = orderManager.getProductById(productId);
        if (p == null) {
            System.out.println("Lỗi: Sản phẩm có ID = " + productId + " không tồn tại!");
            return;
        }

        int quantity = InputUtil.inputInt(sc, "Nhập số lượng mua: ");

        // Tính tổng
        BigDecimal totalAmount = p.getPrice().multiply(new BigDecimal(quantity));

        // Tạo Order
        Order order = new Order(0, customerId, LocalDate.now(), totalAmount);

        try {
            orderManager.createOrder(order);
            System.out.println("Tạo đơn hàng thành công! Tổng số tiền: " + String.format("%,.2f", totalAmount) + " VNĐ");
        } catch (ShopException e) {
            System.out.println(e.getMessage());
        }
    }

    // 4. Liệt kê toàn bộ đơn hàng
    private static void handleListAllOrders() {
        System.out.println("\n--- DANH SÁCH TOÀN BỘ ĐƠN HÀNG ---");
        List<Order> list = orderManager.listAllOrders();
        displayOrders(list);
    }

    // 5. Tìm kiếm đơn hàng theo khách hàng
    private static void handleGetOrdersByCustomer(Scanner sc) {
        System.out.println("\n--- TÌM KIẾM LỊCH SỬ MUA HÀNG ---");
        int customerId = InputUtil.inputInt(sc, "Nhập ID khách hàng cần tìm: ");

        Customer c = orderManager.getCustomerById(customerId);
        if (c == null) {
            System.out.println("Lỗi: Khách hàng có ID = " + customerId + " không tồn tại!");
            return;
        }

        List<Order> list = orderManager.getOrdersByCustomer(customerId);
        System.out.println("Lịch sử mua hàng của khách: " + c.getName());
        displayOrders(list);
    }

    // Hàm kẻ bảng hiển thị danh sách đơn hàng có ghép thêm Tên Khách Hàng
    private static void displayOrders(List<Order> list) {
        if (list.isEmpty()) {
            System.out.println("Danh sách đơn hàng trống.");
            return;
        }

        System.out.printf("+%s+%s+%s+%s+\n",
                "-".repeat(8), "-".repeat(25), "-".repeat(15), "-".repeat(20));
        System.out.printf("| %-6s | %-23s | %-13s | %-18s |\n",
                "Mã ĐH", "Tên Khách Hàng", "Ngày Đặt", "Tổng Tiền");
        System.out.printf("+%s+%s+%s+%s+\n",
                "-".repeat(8), "-".repeat(25), "-".repeat(15), "-".repeat(20));

        for (Order o : list) {
            Customer c = orderManager.getCustomerById(o.getCustomerId());
            String customerName = (c != null) ? c.getName() : "Không xác định";

            System.out.printf("| %-6d | %-23s | %-13s | %-18s |\n",
                    o.getId(),
                    customerName,
                    o.getOrderDate().toString(),
                    String.format("%,.2f", o.getTotalAmount()));
        }
        System.out.printf("+%s+%s+%s+%s+\n",
                "-".repeat(8), "-".repeat(25), "-".repeat(15), "-".repeat(20));
    }
}