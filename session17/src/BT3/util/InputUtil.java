package BT3.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtil {

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

    public static double inputDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double number = Double.parseDouble(sc.nextLine().trim());
                if (number <= 0) {
                    System.out.println("Lỗi: Giá tiền phải lớn hơn 0!");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Sai định dạng số thực. Vui lòng nhập lại!");
            }
        }
    }

    public static BigDecimal inputBigDecimal(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = sc.nextLine().trim();
                BigDecimal number = new BigDecimal(input);

                // So sánh number với 0 (BigDecimal.ZERO)
                if (number.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Lỗi: Giá tiền phải lớn hơn 0!");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Sai định dạng số thực. Vui lòng nhập lại!");
            }
        }
    }
}