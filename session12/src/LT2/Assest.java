package LT2;

import java.util.Scanner;

public abstract class Assest {
    private String assestCode;
    private String name;
    private double purchasePrice;

    public Assest() {
    }

    public Assest(String assestCode, String name, double purchasePrice) {
        this.assestCode = assestCode;
        this.name = name;
        this.purchasePrice = purchasePrice;
    }

    public String getAssestCode() {
        return assestCode;
    }

    public void setAssestCode(String assestCode) {
        this.assestCode = assestCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void inputData(Scanner sc){
        System.out.print("Mời bạn nhập mã code: ");
        this.assestCode= sc.nextLine();

        System.out.print("Mời bạn nhập tên thiết bị: ");
        this.name= sc.nextLine();

        double price;
        do {
            System.out.print("Mời bạn nhập giá mua: ");
            price = Double.parseDouble(sc.nextLine());

            if (price <= 0){
                System.out.println("Giá tiền phải lớn hơn 0.");
            } else
                break;
        }while (true);

        this.purchasePrice = price;
    }

    public abstract void getMarketValue();

}
