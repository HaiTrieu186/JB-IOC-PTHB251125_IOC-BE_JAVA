package BT3;

import java.util.Scanner;

public class Invoice {
    private int id;
    private String receiptId;
    private double price;

    public Invoice() {
    }

    public Invoice(int id, String receiptId, double price) {
        this.id = id;
        this.receiptId = receiptId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void inputData(Scanner sc){
        System.out.println("Nhập mã hóa đơn: ");
        this.receiptId = sc.nextLine();
        System.out.println("Nhập số tiền: ");
        this.price = Double.parseDouble(sc.nextLine());
    }

    public void displayData() {
        System.out.println("ID: "+id+", Receipt ID: "+receiptId+", Price: "+price);
    }
}
