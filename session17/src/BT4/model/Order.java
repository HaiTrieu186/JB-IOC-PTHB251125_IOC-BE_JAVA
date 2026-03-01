package BT4.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private int id;
    private int customerId;
    private LocalDate orderDate;
    private BigDecimal totalAmount;

    public Order() {
    }

    public Order(int id, int customerId, LocalDate orderDate, BigDecimal totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
