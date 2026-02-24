package LT03.model;

import LT03.Exception.EmptyInputException;

import java.util.Scanner;

public class Product implements IBaseModel{
    private int id;
    private String name;
    private double price;

    public Product() {
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public void input(Scanner sc) {
        while (true) {
            try {
                this.name= inputName(sc);
                break;
            }catch (EmptyInputException e){
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                this.price = inputPrice(sc);
                break;
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String inputName(Scanner sc) {
        System.out.print("Mời ban nhập tên sản phẩm: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty())
            throw new EmptyInputException("Lỗi: Tên sản phẩm không được để trống !");

        return name;
    }

    public static double inputPrice(Scanner sc) {
        double price;
        try {
            System.out.print("Mời bạn nhập giá tiền: ");
             price = Double.parseDouble(sc.nextLine());
        }catch (NumberFormatException e){
            throw new NumberFormatException("Lỗi: Vui lòng nhập định dạng hợp lệ, mời bạn nhập lại!");
        }

        if (price<=0)
            throw new IllegalArgumentException("Lỗi: Vui lòng nhập giá tiền hợp lệ (lớn hơn 0)");

        return price;
    }

    public static int inputID(Scanner sc) {
        int id;
        try {
            System.out.print("Mời bạn nhập ID sản phẩm: ");
            id= Integer.parseInt(sc.nextLine());
        }catch (NumberFormatException e){
            throw new NumberFormatException("Lỗi: Vui lòng nhập định dạng hợp lệ, mời bạn nhập lại!");
        }
        return id;
    }

    @Override
    public void display() {
        System.out.println("ID: " + this.id + ", Name: "+ this.name + ", Price: "+ this.price);
    }
}
