package BT4.util;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

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

    // 3. Nhập BigDecimal
    public static BigDecimal inputBigDecimal(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = sc.nextLine().trim();
                BigDecimal number = new BigDecimal(input);

                if (number.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Lỗi: Số tiền phải lớn hơn 0!");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Sai định dạng số. Vui lòng nhập lại!");
            }
        }
    }

    // 4. Nhập Email chuẩn định dạng
    public static String inputEmail(Scanner sc, String prompt) {
        // Regex kiểm tra định dạng email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Lỗi: Không được để trống. Vui lòng nhập lại!");
            } else if (!Pattern.matches(emailRegex, input)) {
                System.out.println("Lỗi: Email không hợp lệ (VD chuẩn: abc@gmail.com). Vui lòng nhập lại!");
            } else {
                return input;
            }
        }
    }
}