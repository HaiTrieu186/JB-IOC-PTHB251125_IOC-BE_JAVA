package LT3;

import java.util.Scanner;

public abstract class Drink implements IPromotion {
    public static int count=0;
    private int id;
    private String name;
    private double price;

    public Drink() {
    }

    public Drink(int id, String name, double price) {
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

    public void inputData(Scanner sc){
        this.id = ++count;
        System.out.println("Mời bạn nhập tên thức uống: ");
        name = sc.nextLine();

        double p;
        do {
            System.out.println("Mời bạn nhập giá: ");
            p = Double.parseDouble(sc.nextLine());

            if (p<=0)
                System.out.println("Vui lòng nhập giá tiền hợp lệ !");
            else
                break;

        }while (true);
        this.price = p;
    }

    public abstract void prepare();
    public abstract double applyDiscount(double percentage);
}
