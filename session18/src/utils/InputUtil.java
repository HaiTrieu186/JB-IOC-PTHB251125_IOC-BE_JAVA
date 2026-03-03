package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtil {
    // 1. Nhập chuỗi
    public static String inputString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Lỗi: Không được để trống. Vui lòng nhập lại!");
            } else {
                return input;
            }
        }
    }

    // 2. Nhập số nguyên dương
    public static int inputInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int number = Integer.parseInt(sc.nextLine().trim());
                if (number <= 0) {
                    System.out.println("Lỗi: Số phải lớn hơn 0!");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Sai định dạng số nguyên. Vui lòng nhập lại!");
            }
        }
    }

    // 3. Nhập số Float
    public static float inputFloat(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                float number = Float.parseFloat(sc.nextLine().trim());
                if (number <= 0) {
                    System.out.println("Lỗi: Giá sản phẩm phải lớn hơn 0!");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Sai định dạng số thực. Vui lòng nhập lại!");
            }
        }
    }

    // 4. Nhập ngày tháng theo định dạng dd/MM/yyyy
    public static LocalDate inputDate(Scanner sc, String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print(prompt + " (dd/MM/yyyy): ");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Lỗi: Không được để trống ngày tháng. Vui lòng nhập lại!");
                continue;
            }

            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Sai định dạng ngày tháng. Vui lòng nhập đúng chuẩn dd/MM/yyyy (VD: 25/12/2024).");
            }
        }
    }

    // 5. Nhập trạng thái sản phẩm
    public static int inputStatus(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt + " (1: Hoạt động | 0: Ngừng hoạt động): ");
            try {
                int status = Integer.parseInt(sc.nextLine().trim());
                if (status == 0 || status == 1) {
                    return status;
                } else {
                    System.out.println("Lỗi: Trạng thái chỉ được nhập 0 hoặc 1!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Sai định dạng. Vui lòng nhập 0 hoặc 1!");
            }
        }
    }
}
