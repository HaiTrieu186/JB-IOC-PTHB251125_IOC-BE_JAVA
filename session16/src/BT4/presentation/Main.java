package BT4.presentation;

import BT4.business.ProductProcessor;
import BT4.business.ProductProcessorImpl;
import BT4.model.Product;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ProductProcessorImpl processor = new ProductProcessorImpl();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== QUẢN LÝ SẢN PHẨM =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Cập nhật giá sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Hiển thị danh sách sản phẩm");
            System.out.println("5. Kiểm tra sản phẩm đắt tiền (> 100)");
            System.out.println("6. Tính tổng giá trị sản phẩm");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn chức năng (0-6): ");

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Lựa chọn phải là một số nguyên. Vui lòng nhập lại!");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAdd(sc);
                    break;
                case 2:
                    handleUpdate(sc);
                    break;
                case 3:
                    handleDelete(sc);
                    break;
                case 4:
                    handleDisplay();
                    break;
                case 5:
                    handleCheckExpensive();
                    break;
                case 6:
                    handleCalculateTotal();
                    break;
                case 0:
                    System.out.println("Kết thúc chương trình!");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 0-6!");
            }
        }
    }

    private static void handleAdd(Scanner sc) {
        System.out.println("--- Thêm sản phẩm ---");
        String name;

        while (true) {
            name = Product.inputName(sc);
            if (processor.isExistByName(name)) {
                System.out.println("Lỗi: Sản phẩm có tên '" + name + "' đã tồn tại! Vui lòng nhập tên khác.");
            } else {
                break;
            }
        }

        double price = Product.inputPrice(sc);
        processor.add(new Product(name, price));
        System.out.println("Thêm sản phẩm thành công!");
    }

    private static void handleUpdate(Scanner sc) {
        System.out.println("--- Cập nhật sản phẩm ---");
        String name;

        while (true) {
            name = Product.inputName(sc);
            if (!processor.isExistByName(name)) {
                System.out.println("Lỗi: Không tìm thấy sản phẩm '" + name + "'! Vui lòng kiểm tra và nhập lại.");
            } else {
                break;
            }
        }

        System.out.println("Nhập giá mới cho sản phẩm:");
        double newPrice = Product.inputPrice(sc);
        processor.update(new Product(name, newPrice));
        System.out.println("Cập nhật thành công!");
    }

    private static void handleDelete(Scanner sc) {
        System.out.println("--- Xóa sản phẩm ---");
        String name;

        while (true) {
            name = Product.inputName(sc);
            if (!processor.isExistByName(name)) {
                System.out.println("Lỗi: Không tìm thấy sản phẩm '" + name + "'! Vui lòng kiểm tra và nhập lại.");
            } else {
                break;
            }
        }

        Product dummyProduct = new Product();
        dummyProduct.setName(name);
        processor.delete(dummyProduct);
        System.out.println("Đã xóa sản phẩm thành công!");
    }

    private static void handleDisplay() {
        List<Product> list = processor.findAll();
        ProductProcessor.printProductList(list);
    }

    private static void handleCheckExpensive() {
        List<Product> list = processor.findAll();

        if (list.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }

        boolean hasExpensive = processor.hasExpensiveProduct(list);

        if (hasExpensive) {
            System.out.println("Các sản phẩm đắt tiền (> 100):");
            list.stream()
                    .filter(p -> p.getPrice() > 100)
                    .forEach(System.out::println);
        } else {
            System.out.println("Không có sản phẩm đắt tiền");
        }
    }

    private static void handleCalculateTotal() {
        List<Product> list = processor.findAll();
        if (list.isEmpty()) {
            System.out.println("Danh sách trống, tổng giá trị = 0");
            return;
        }

        double total = processor.calculateTotalValue(list);
        System.out.println("Tổng giá trị các sản phẩm: " + total);
    }
}