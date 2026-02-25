package BT1.presentation;

import BT1.Exception.EmptyInputException;
import BT1.business.impl.ProductManager;
import BT1.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static ProductManager productManager = new ProductManager();

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
                    handleEditProduct(sc);
                    break;
                }
                case 3: {
                    handleDeleteProduct(sc);
                    break;
                }
                case 4: {
                    handleDisplayProducts();
                    break;
                }
                case 5: {
                    handleFilterProducts(sc);
                    break;
                }
                case 6: {
                    handleCalculateTotal();
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

    private static void handleAddProduct(Scanner sc) {
        System.out.println("\n--- THÊM SẢN PHẨM MỚI ---");
        int id;

        while (true) {
            try {
                id = Product.inputID(sc);

                if (productManager.isExistById(id)) {
                    System.out.println("Lỗi: ID [" + id + "] đã tồn tại, vui lòng nhập ID sản phẩm khác!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        Product product = new Product();
        product.setId(id);
        product.input(sc);

        productManager.add(product);
        System.out.println("Sản phẩm đã được thêm thành công !");
    }

    private static void handleEditProduct(Scanner sc) {
        int idEdit;
        System.out.println("\n--- SỬA SẢN PHẨM---");
        while (true) {
            try {
                idEdit= Product.inputID(sc);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }


        Product p = productManager.findById(idEdit).orElseGet(() -> null);
        if (p == null) {
            System.out.println("Lỗi: Sản phẩm với ID [" + idEdit + "] không tồn tại !");
            return;
        }

        System.out.println("\n-- Cập nhập thông tin mới --");
        p.input(sc);

        productManager.update(p);


        System.out.println("Cập nhật thông tin sản pẩm thành công");

    }

    private static void handleDeleteProduct(Scanner sc) {
        int idDelete;
        System.out.println("\n--- XÓA SẢN PHẨM---");
        while (true) {
            try {
                idDelete= Product.inputID(sc);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!productManager.isExistById(idDelete)) {
            System.out.println("Lỗi: Sản phẩm với ID [" + idDelete + "] không tồn tại !");
            return;
        }

        productManager.delete(idDelete);
        System.out.println("Xóa sản phẩm thành công !");

    }

    private static void handleDisplayProducts() {
        System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
        ProductManager.displayList(productManager.findAll());
    }

    private  static void handleFilterProducts(Scanner sc) {
        double priceFilter;
        System.out.println("\n--- LỌC THEO GIÁ ---");
        while (true){
            try {
                priceFilter=Product.inputPrice(sc);
                break;
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        }

        List<Product> results= productManager.filterByPrice(priceFilter);
        System.out.println("-- Danh sách sản phẩm với giá >="+priceFilter+" --");
        ProductManager.displayList(results);

    }

    private  static void handleCalculateTotal() {
        double total=0;

        total+=productManager.calculateTotalValue();

        System.out.println("Tổng giá trị sản phẩm là: "+total);

    }

    private static int showMenu(Scanner sc) {
        int choice;
        System.out.println("""
                ------ Product Management System -------
                1. Add Product
                2. Edit Product
                3. Delete Product
                4. Display Products
                5. Filter Products
                6. Total Value of Products
                0. Exit
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
