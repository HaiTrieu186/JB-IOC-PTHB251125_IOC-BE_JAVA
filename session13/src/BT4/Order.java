package BT4;

import java.util.Scanner;

public class Order {
    private String orderId;
    private String customerName;

    public Order() {
    }

    public Order(String orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void inputData(Scanner sc) {
        System.out.println(" Mời bạn nhập mã đơn hàng:  ");
        this.orderId = sc.nextLine();
        System.out.println(" Mời bạn nhập tên khách hàng:  ");
        this.customerName = sc.nextLine();
    }

    public void outputData() {
        System.out.printf("Mã đơn hàng: %-10s, Tên khách hàng: %-50s\n", this.orderId, this.customerName);
    }
}
