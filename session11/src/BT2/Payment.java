package BT2;

public abstract class Payment {
    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public Payment() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract void pay();

    public void printAmount() {
        System.out.println("Amount : " + this.amount);
    }
}
