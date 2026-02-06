package BT3;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Account {
    public static final double minimumBalance = 50000.0;
    private double balance;

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Lỗi: Số tiền rút phải lớn hơn 0!");
        }

        if (this.balance < amount) {
            throw new IllegalArgumentException("Lỗi: Số tiền rút vượt quá số dư!");
        }

        if ((this.balance - amount) < minimumBalance) {
            throw new IllegalArgumentException("Lỗi: Tài khoản phải duy trì số dư tối thiểu 50.000 đồng!");
        }

        this.balance -= amount;
    }

    public void deposit(double balance) {
        this.balance += balance;
    }

    public void input(Scanner sc) {
        double amount;
        while (true) {
            try {
                System.out.print("Mời bạn nhập số dư ban đầu: ");
                amount = Double.parseDouble(sc.nextLine());

                if (amount < minimumBalance) {
                    System.out.println("Lỗi: Số dư ban đầu không được thấp hơn 50.000 VNĐ!");
                } else {
                    this.balance = amount;
                    break;
                }
            }catch (NumberFormatException e){
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
            }
        }
    }

    public void output() {
        Locale vnLocale = new Locale("vi", "VN");
        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(vnLocale);

        String formattedAmount = vnFormat.format(this.balance);
        System.out.println("Số dư hiện tại: " + formattedAmount);
    }



}
