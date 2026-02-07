package BT5.Presentation;

import BT5.Business.impl.AccountServiceImpl;
import BT5.Model.Account;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        AccountServiceImpl accountService = new AccountServiceImpl();

        accountService.add(new Account("A01",1000000));
        accountService.add(new Account("A02",2500000));
        accountService.add(new Account("A03",100000000));


        while(true){
            System.out.println("------ MENU -----");
            System.out.println("1. Thêm tài khoản mới ");
            System.out.println("2. Rút tiền");
            System.out.println("3. Nạp tiền");
            System.out.println("4. Chuyên tiền");
            System.out.println("5. Thoát");
            System.out.print("Mời bạn chọn: ");


            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
                continue;
            }

            switch (choice){
                case 1:{break;}
                case 2:{
                    withdrawMoney(sc, accountService);
                    break;}
                case 3:{
                    depositMoney(sc, accountService);
                    break;}
                case 4:{
                    transferMoney(sc, accountService);
                    break;}
                case 5:{
                    System.out.println("Thoát chương trình");
                    sc.close();
                    System.exit(0);
                }
                default:{
                    System.out.println("Lỗi: Vui lòng chọn lựa chọn hợp lệ (1-5)");
                }
            }
        }

    }

    private static void transferMoney(Scanner sc, AccountServiceImpl accountService) {
        System.out.println("--- CHUYỂN KHOẢN ---");

        // Tìm tài khoản nguồn
        Account fromAcc = findAccountInput(sc, accountService, "Nhập ID tài khoản NGUỒN: ");
        if (fromAcc == null) return;

        // Tìm tài khoản đích
        Account toAcc = findAccountInput(sc, accountService, "Nhập ID tài khoản ĐÍCH: ");
        if (toAcc == null) return;

        // Xử lý chuyển tiền
        try {
            System.out.print("Nhập số tiền cần chuyển: ");
            double amount = Double.parseDouble(sc.nextLine());

            fromAcc.transfer(toAcc, amount);

            System.out.println(">> Chuyển thành công!");
            System.out.println("   - Số dư người gửi (" + fromAcc.getAccountId() + "): " + Account.formatBalance(fromAcc.getBalance()));
            System.out.println("   - Số dư người nhận (" + toAcc.getAccountId() + "): " + Account.formatBalance(toAcc.getBalance()));

        } catch (NumberFormatException e) {
            System.out.println(">> Lỗi: Nhập số tiền sai định dạng!");
        } catch (IllegalArgumentException e) {
            System.out.println(">> Giao dịch thất bại: " + e.getMessage());
        }

    }

    private static void depositMoney(Scanner sc, AccountServiceImpl accountService) {
        System.out.println("--- NẠP TIỀN ---");
        Account acc = findAccountInput(sc, accountService, "Nhập ID tài khoản nạp: ");

        if (acc != null) {
            try {
                System.out.print("Nhập số tiền cần nạp: ");
                double amount = Double.parseDouble(sc.nextLine());

                acc.deposit(amount); // Logic cộng tiền nằm ở Model

                System.out.println(">> Nạp thành công! ");
                acc.displayData();
            } catch (NumberFormatException e) {
                System.out.println(">> Lỗi: Nhập số tiền sai định dạng!");
            } catch (IllegalArgumentException e) {
                System.out.println(">> Giao dịch thất bại: " + e.getMessage());
            }
        }
    }

    private static void withdrawMoney(Scanner sc, AccountServiceImpl accountService) {
        System.out.println("--- RÚT TIỀN ---");
        Account acc = findAccountInput(sc, accountService, "Nhập ID tài khoản rút: ");

        if (acc != null) {
            try {
                System.out.print("Nhập số tiền cần rút: ");
                double amount = Double.parseDouble(sc.nextLine());

                acc.withdraw(amount);

                System.out.println(">> Rút thành công! ");
                acc.displayData();

            } catch (NumberFormatException e) {
                System.out.println(">> Lỗi: Nhập số tiền sai định dạng!");
            } catch (IllegalArgumentException e) {
                System.out.println(">> Giao dịch thất bại: " + e.getMessage());
            }
        }
    }

    private static Account findAccountInput(Scanner sc, AccountServiceImpl service, String message) {
        System.out.print(message);
        String id = sc.nextLine();
        Account acc = service.findById(id);
        if (acc == null) {
            System.out.println(">> Lỗi: Không tìm thấy tài khoản có ID [" + id + "]");
        }
        return acc;
    }
}
