package BT2;

public class CashPayment extends Payment{
    @Override
    public void pay() {
        System.out.println("Pay by Cash....");;
    }

    public CashPayment(double amount) {
        super(amount);
    }

    public CashPayment() {
    }
}
