package BT2;

public class EWalletPayment extends Payment implements Refundable{

    public EWalletPayment(double amount) {
        super(amount);
    }

    public EWalletPayment() {
    }

    @Override
    public void pay() {
        System.out.println("Pay by EWallet....");;
    }

    @Override
    public void refund() {
        System.out.println("Refund by EWallet....");;
    }
}
