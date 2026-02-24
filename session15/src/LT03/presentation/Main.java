package LT03.presentation;

import LT02.Exception.EmptyInputException;
import LT02.business.impl.SubjectManager;
import LT02.model.Subject;
import LT03.bussiness.impl.ProductManager;
import LT03.model.Order;
import LT03.model.Product;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static ProductManager productManager = new ProductManager();
    private static Map<String, Order> ordersManager = new HashMap<String, Order>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            choice = showMenu(sc);
            switch (choice) {
                case 1: {
                    handleAddProduct(sc);
                    break;
                }
                case 2: {
                    handleDeleteProduct(sc);
                    break;
                }
                case 3: {
                    handleDisplayProducts();
                    break;
                }
                case 4: {
                    handleCreateOrder(sc);
                    break;
                }
                case 5: {
                    handleAddProductToOrder(sc);
                    break;
                }
                case 6: {
                    handleDisplayOrders();
                    break;
                }
                case 0: {
                    System.out.println("Thoát chương trình !");
                    sc.close();
                    System.exit(0);
                }
                default: {
                    System.out.println("Lỗi: Vui lòng chọn lựa chọn hợp lệ (1-7) !");
                }
            }
        }
    }

    private static void handleAddProductToOrder(Scanner sc) {
        System.out.println("\n--- THÊM SẢN PHẨM VÀO ĐƠN HÀNG---");
        String code;

        while (true) {
            System.out.print("Mời bạn nhập mã đơn hàng: ");
            code = sc.nextLine();

            if (!ordersManager.containsKey(code)) {
                System.out.println("Lỗi: Đơn hàng không tồn tại, vui lòng nhập lại");
                continue;
            }
            break;
        }

        Order order = ordersManager.get(code);

        int productId;
        while (true){
            try {
                productId=Product.inputID(sc);

                if (!productManager.isExistById(productId)) {
                    System.out.println("Lỗi: sản phẩm với id["+productId+"] Không tồn tại !");
                    continue;
                }

                break;
            }catch (NumberFormatException e){
                System.out.println("Lỗi: "+ e.getMessage());
            }
        }


        Product product = productManager.findByID(productId);
        if (!order.addProductToOrder(product)) {
            System.out.println("Sản phẩm đã tồn tại trong đơn hàng!");
        } else {
            System.out.println("Thêm thành công!");
        }

    }

    private static void handleCreateOrder(Scanner sc) {
        System.out.println("\n--- TẠO ĐƠN HÀNG MỚI---");
        String code;

        while (true) {
            System.out.print("Mời bạn nhập mã đơn hàng: ");
            code = sc.nextLine();

            if (ordersManager.containsKey(code)) {
                System.out.println("Lỗi: mã đơn hàng đã tồn tại, vui lòng nhập lại");
                continue;
            }
            break;
        }

        int productId;
        while (true){
            try {
                productId=Product.inputID(sc);

                if (!productManager.isExistById(productId)) {
                    System.out.println("Lỗi: sản phẩm với id["+productId+"] Không tồn tại !");
                    continue;
                }

                break;
            }catch (NumberFormatException e){
                System.out.println("Lỗi: "+ e.getMessage());
            }
        }

        Order newOrder = new Order();
        newOrder.addProductToOrder(productManager.findByID(productId));
        ordersManager.put(code, newOrder);

        System.out.println("Đã thêm đơn hàng với ID: ["+code+"] thành công !");


    }

    private static void handleDisplayOrders() {
        System.out.println("\n--- DANH SÁCH ĐƠN HÀNG ---");

        for (Map.Entry<String, Order> entry: ordersManager.entrySet()){
            System.out.println("Mã hóa đơn: "+ entry.getKey());
            entry.getValue().displayInfo();
            System.out.println("---------------");
        }
    }

    private static void handleDisplayProducts() {
        System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
        ProductManager.displayList(productManager.findAll());
    }

    private static void handleDeleteProduct(Scanner sc) {
        int idDelete;
        System.out.println("\n--- XÓA SẢN PHẨM---");
        while (true) {
            try {
                idDelete= Product.inputID(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!productManager.isExistById(idDelete)) {
            System.out.println("Lỗi: Môn học với ID [" + idDelete + "] không tồn tại !");
            return;
        }

        productManager.delete(idDelete);
        System.out.println("Môn học với ID [" + idDelete + "] đã được xóa thành công !");

    }

    private static void handleAddProduct(Scanner sc) {
        System.out.println("\n--- THÊM SẢN PHẨM MỚI ---");
        int id;

        while (true) {
            try {
                id = Product.inputID(sc);

                if (productManager.isExistById(id)) {
                    System.out.println("Lỗi: ID [" + id + "] đã tồn tại, vui lòng nhập ID khác!");
                } else {
                    break;
                }
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        Product product = new Product();
        product.setId(id);
        product.input(sc);

        productManager.add(product);
        System.out.println("Sản phẩm đã được thêm thành công !");
    }

    private static int showMenu(Scanner sc) {
        int choice;
        System.out.println("""
                ------ MENU -------
                1. Thêm sản phẩm
                2. Xóa sản phẩm
                3. Hiển thị sản phẩm
                4. Tạo đơn hàng
                5. Thêm sản phẩm vào đơn hàng
                6. Hiển thị đơn hàng
                0. Thoát
                ---------------------------""");

        while (true) {
            try {
                System.out.print("Mời bạn chọn: ");
                choice = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng chỉ nhập định dạng số hợp lệ (0-6)");
            }
        }

        return choice;
    }
}
