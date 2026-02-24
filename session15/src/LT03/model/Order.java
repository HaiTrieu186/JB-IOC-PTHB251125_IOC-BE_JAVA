package LT03.model;

import LT03.bussiness.impl.ProductManager;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int autoId = 1;
    private int orderId;
    private List<Product> products = new ArrayList<>();

    public Order() {
        this.orderId = autoId++;
    }

    public Order(int orderId, List<Product> products) {
        this.orderId = orderId;
        this.products = products;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean addProductToOrder(Product product) {
        if (this.products.stream().anyMatch(p -> p.getId() == product.getId())) {
            return false; // đã tồn tại
        }
        products.add(product);
        return true;
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public void displayInfo() {
        System.out.println("Order ID: " + orderId);
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("Total: " + calculateTotal());
    }

}
