package BT1.model;

import BT1.Exception.EmptyInputException;

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
        while (true){
            try {
                this.name=inputName(sc);
                break;
            }catch (EmptyInputException e){
                System.out.println(e.getMessage());
            }
        }

        while (true){
            try {
                this.price=inputPrice(sc);
                break;
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static int inputID(Scanner sc) {
        int id;

        try {
            System.out.print("Mời bạn nhập ID: ");
            id= Integer.parseInt(sc.nextLine());
        }catch (NumberFormatException e){
            throw new NumberFormatException("Lỗi: Vui lòng nhập định dạng ID hợp lệ !");
        }

        if (id <=0)
            throw new NumberFormatException("Lỗi: Vui lòng nhập ID hợp lệ (>=0) !");

        return id;
    }

    public static String inputName(Scanner sc) {
        String name;

        System.out.print("Mời bạn nhập tên sản phẩm: ");
        name= sc.nextLine().trim();

        if (name.isEmpty())
            throw new EmptyInputException("Lỗi: Không được để trống tên sản phẩm !");

        return name;
    }

    public static double inputPrice(Scanner sc) {
        double price;

        try {
            System.out.print("Mời bạn nhập giá sản phẩm: ");
            price= Double.parseDouble(sc.nextLine());
        }catch (NumberFormatException e){
            throw new NumberFormatException("Lỗi: Vui lòng nhập định dạng giá hợp lệ !");
        }

        if (price <=0)
            throw new NumberFormatException("Lỗi: Vui lòng nhập giá hợp lệ (>=0) !");

        return price;
    }

    @Override
    public void display() {
        System.out.println("ID: "+this.id+",  Name: "+this.name+", Price: "+this.price);
    }
}
