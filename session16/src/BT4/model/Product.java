package BT4.model;

import java.util.Scanner;

public class Product {
    private String name;
    private double price;

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static String inputName(Scanner sc) {
        while (true) {
            System.out.print("Nhập tên sản phẩm: ");
            String name = sc.nextLine().trim();
            if (name.isBlank()) {
                System.out.println("Lỗi: Tên không được để trống!");
            } else {
                return name;
            }
        }
    }

    public static double inputPrice(Scanner sc) {
        while (true) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                double price = Double.parseDouble(sc.nextLine().trim());
                if (price < 0) {
                    System.out.println("Lỗi: Giá sản phẩm không được âm!");
                    continue;
                }
                return price;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Giá sản phẩm phải là số hợp lệ!");
            }
        }
    }


    @Override
    public String toString() {
        return "Product { name='" + name + "', price=" + price + " }";
    }
}
