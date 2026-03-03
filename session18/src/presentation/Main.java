package presentation;

import business.ProductManager;
import exception.ProductException;
import model.Product;
import utils.InputUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ProductManager productManager = new ProductManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n******************** PRODUCT MANAGEMENT ****************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục");
            System.out.println("8. Thoát");
            System.out.print("Mời bạn chọn chức năng (1-8): ");

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    handleListProducts();
                    break;
                case 2:
                    handleAddProduct(sc);
                    break;
                case 3:
                    handleUpdateProduct(sc);
                    break;
                case 4:
                    handleDeleteProduct(sc);
                    break;
                case 5:
                    handleSearchProduct(sc);
                    break;
                case 6:
                    handleSortProducts();
                    break;
                case 7:
                    handleStatProduct();
                    break;
                case 8:
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 8!");
            }
        }
    }

    // 1. Hiển thị danh sách sản phẩm
    private static void handleListProducts() {
        System.out.println("\n--- DANH SÁCH TOÀN BỘ SẢN PHẨM ---");
        List<Product> products = productManager.getAllProducts();
        displayProducts(products);
    }

    // 2. Thêm mới sản phẩm
    private static void handleAddProduct(Scanner sc) {
        System.out.println("\n--- THÊM MỚI SẢN PHẨM ---");
        String name = InputUtil.inputString(sc, "Nhập tên sản phẩm: ");
        float price = InputUtil.inputFloat(sc, "Nhập giá sản phẩm: ");
        String title = InputUtil.inputString(sc, "Nhập tiêu đề sản phẩm: ");
        LocalDate created = InputUtil.inputDate(sc, "Nhập ngày tạo");
        String catalog = InputUtil.inputString(sc, "Nhập danh mục sản phẩm: ");
        int status = InputUtil.inputStatus(sc, "Nhập trạng thái");

        Product p = new Product(0, name, price, title, created, catalog, status);

        try {
            productManager.addProduct(p);
        } catch (ProductException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3. Cập nhật sản phẩm
    private static void handleUpdateProduct(Scanner sc) {
        System.out.println("\n--- CẬP NHẬT SẢN PHẨM ---");
        int id = InputUtil.inputInt(sc, "Nhập mã sản phẩm (ID) cần cập nhật: ");

        if (productManager.getProductById(id) == null) {
            System.out.println("Lỗi: Không tìm thấy sản phẩm có ID = " + id);
            return;
        }

        System.out.println("- Nhập thông tin mới cho sản phẩm -");
        String name = InputUtil.inputString(sc, "Nhập tên sản phẩm mới: ");
        float price = InputUtil.inputFloat(sc, "Nhập giá mới: ");
        String title = InputUtil.inputString(sc, "Nhập tiêu đề mới: ");
        LocalDate created = InputUtil.inputDate(sc, "Nhập ngày tạo mới");
        String catalog = InputUtil.inputString(sc, "Nhập danh mục mới: ");
        int status = InputUtil.inputStatus(sc, "Nhập trạng thái mới");

        Product p = new Product(id, name, price, title, created, catalog, status);

        try {
            productManager.updateProduct(id, p);
        } catch (ProductException e) {
            System.out.println(e.getMessage());
        }
    }

    // 4. Xóa sản phẩm
    private static void handleDeleteProduct(Scanner sc) {
        System.out.println("\n--- XÓA SẢN PHẨM ---");
        int id = InputUtil.inputInt(sc, "Nhập mã sản phẩm (ID) cần xóa: ");

        try {
            productManager.deleteProduct(id);
        } catch (ProductException e) {
            System.out.println(e.getMessage());
        }
    }

    // 5. Tìm kiếm sản phẩm theo tên
    private static void handleSearchProduct(Scanner sc) {
        System.out.println("\n--- TÌM KIẾM SẢN PHẨM THEO TÊN ---");
        String keyword = InputUtil.inputString(sc, "Nhập từ khóa tên sản phẩm cần tìm: ");

        List<Product> products = productManager.searchProductByName(keyword);
        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào phù hợp với từ khóa: " + keyword);
        } else {
            displayProducts(products);
        }
    }

    // 6. Sắp xếp sản phẩm theo giá tăng dần
    private static void handleSortProducts() {
        System.out.println("\n--- DANH SÁCH SẢN PHẨM (GIÁ TĂNG DẦN) ---");
        List<Product> products = productManager.getProductsSortedByPrice();
        displayProducts(products);
    }

    // 7. Thống kê theo danh mục
    private static void handleStatProduct() {
        productManager.statProductByCatalog();
    }

    //  In danh sách sản phẩm
    private static void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }

        System.out.printf("+%s+%s+%s+%s+%s+%s+%s+\n",
                "-".repeat(6), "-".repeat(25), "-".repeat(12), "-".repeat(20), "-".repeat(12), "-".repeat(15), "-".repeat(12));
        System.out.printf("| %-4s | %-23s | %-10s | %-18s | %-10s | %-13s | %-10s |\n",
                "ID", "Tên Sản Phẩm", "Giá", "Tiêu Đề", "Ngày Tạo", "Danh Mục", "Trạng Thái");
        System.out.printf("+%s+%s+%s+%s+%s+%s+%s+\n",
                "-".repeat(6), "-".repeat(25), "-".repeat(12), "-".repeat(20), "-".repeat(12), "-".repeat(15), "-".repeat(12));

        for (Product p : products) {
            String statusStr = p.getStatus() == 1 ? "Hoạt động" : "Ngừng HĐ";
            System.out.printf("| %-4d | %-23s | %-10.2f | %-18s | %-10s | %-13s | %-10s |\n",
                    p.getId(),
                    p.getName().length() > 23 ? p.getName().substring(0, 20) + "..." : p.getName(),
                    p.getPrice(),
                    p.getTitle().length() > 18 ? p.getTitle().substring(0, 15) + "..." : p.getTitle(),
                    p.getCreated().toString(),
                    p.getCatalog().length() > 13 ? p.getCatalog().substring(0, 10) + "..." : p.getCatalog(),
                    statusStr);
        }
        System.out.printf("+%s+%s+%s+%s+%s+%s+%s+\n",
                "-".repeat(6), "-".repeat(25), "-".repeat(12), "-".repeat(20), "-".repeat(12), "-".repeat(15), "-".repeat(12));
    }
}