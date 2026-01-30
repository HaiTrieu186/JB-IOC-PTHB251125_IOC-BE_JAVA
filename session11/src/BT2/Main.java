package BT2;

public class Main {
    public static void main(String[] args) {
        Payment[] payments = new Payment[3];
        payments[0]= new CashPayment(3000);
        payments[1]= new CreditCardPayment(5000);
        payments[2]= new EWalletPayment(10000);

        for (Payment payment : payments) {

            if (payment instanceof CashPayment) {
                CashPayment cashPayment = (CashPayment) payment;
                cashPayment.pay();
            } else

            if (payment instanceof CreditCardPayment) {
                CreditCardPayment creditCardPayment = (CreditCardPayment) payment;
                creditCardPayment.pay();
                creditCardPayment.refund();
            } else

            if (payment instanceof EWalletPayment) {
                EWalletPayment eWalletPayment = (EWalletPayment) payment;
                eWalletPayment.pay();
                eWalletPayment.refund();
            }
        }

    }
}
