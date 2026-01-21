package BT5;

public class Order {
    private int orderId;
    private Customer customer;
    private double total;

    public Order(double total, Customer customer, int orderId) {
        this.total = total;
        this.customer = customer;
        this.orderId = orderId;
    }

    public void printOrder(){

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
