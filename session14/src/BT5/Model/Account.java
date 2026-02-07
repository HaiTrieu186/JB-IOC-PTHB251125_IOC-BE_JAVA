package BT5.Model;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Account implements IAccountModel{
    private String accountId;
    private double balance;

    public Account() {
    }

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void inputData(Scanner sc) {

    }

    @Override
    public void displayData() {
        System.out.print("Account ID : "+accountId);
        System.out.println(", Current Balance : "+formatBalance(this.balance));
    }


    @Override
    public void deposit(double amount) throws IllegalArgumentException {

        validateDepositAmount(amount);

        this.balance += amount;
    }

    private void validateDepositAmount(double amount) {
        if (Double.compare(amount, 0) <= 0) {
            throw  new IllegalArgumentException("Lỗi: Số tiền gửi không hợp lệ (Phải lớn hơn 0)!");
        }
    }

    @Override
    public void withdraw(double amount) throws IllegalArgumentException{
        validateWithdrawAmount(amount);

        this.balance -= amount;
    }

    private void validateWithdrawAmount(double amount) {
        if (Double.compare(amount, 0) <= 0) {
            throw  new IllegalArgumentException("Lỗi: Số tiền rút không hợp lệ (Phải lớn hơn 0)!");
        }

        if (Double.compare(this.balance, amount) < 0) {
            throw  new IllegalArgumentException("Lỗi: Số dư không đủ để rút!");
        }
    }

    @Override
    public void transfer(Account accTo, double amount) throws IllegalArgumentException{
        this.validateWithdrawAmount(amount);
        accTo.validateDepositAmount(amount);

        this.balance -= amount;
        accTo.balance += amount;

        System.out.println("Chuyển khoản thành công !");
    }

    public static String formatBalance (double amount){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String formatBalance = currencyVN.format(amount);

        return formatBalance;
    }

}
