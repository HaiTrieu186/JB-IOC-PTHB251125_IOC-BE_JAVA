package BT2;

public class CreditCardPayment extends Payment implements Refundable{

    public CreditCardPayment(double amount) {
        super(amount);
    }

    public CreditCardPayment() {
    }

    @Override
    public void pay() {
        System.out.println("Pay by Card....");;
    }

    @Override
    public void refund() {
        System.out.println("Refund by Card....");;
    }
}
