package BT3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account account = new Account();

        System.out.println("--- KHỞI TẠO TÀI KHOẢN ---");
        account.input(sc);

        System.out.println("\n--- THỰC HIỆN GIAO DỊCH ---");
        account.output();
        handleWithdraw(sc, account);

    }

    private static void  handleWithdraw(Scanner sc, Account account) {
        double amount=0;
        while (true) {
            try {
                System.out.print("Mời bạn nhập số tiền cần rút: ");
                amount= Double.parseDouble(sc.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
            }
        }

        try {
            account.withdraw(amount);
            System.out.println();
            System.out.println("Rút số tiền: "+ amount +" thành công !");
            account.output();
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }
}
