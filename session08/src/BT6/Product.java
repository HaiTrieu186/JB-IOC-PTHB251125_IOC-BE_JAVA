package BT6;

import java.util.Scanner;

public class Product {
    private static int auto_id=1;

    private int id;
    private String name;
    private double price;

    private final String WAREHOUSE_CODE="KHO-01";

    public Product() {
        this(0,"");
    }

    public Product(double price, String name) {
        this.id=auto_id++;
        this.price = price;
        this.name = name;
    }

    public void input(Scanner sc){
        System.out.print("+ Mời bạn nhập tên: ");
        this.name = sc.nextLine();

        double temp;
        do {
            System.out.print("+ Mời bạn nhập giá: ");
            temp = Double.parseDouble(sc.nextLine());

            if (temp < 0.0) {
                System.out.println("Giá không hợp lệ, vui lòng nhập lại !");
            } else {
                this.price = temp;
                break;
            }
        }while(true);
    }

    public void print(){
        System.out.printf("| %-3s | %-20s | %-15s |\n",this.id,this.name,this.price);
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

    public static int getNumberProduct(){
        return (auto_id-1);
    }


}
