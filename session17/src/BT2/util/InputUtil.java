package BT2.util;

import BT2.model.TaskStatusEnum;

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
                    System.out.println("Lỗi: ID phải là số lớn hơn 0!");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Sai định dạng số. Vui lòng nhập lại!");
            }
        }
    }

    public static TaskStatusEnum inputStatus(Scanner sc) {
        while (true) {
            System.out.print("Nhập trạng thái (1. Chưa hoàn thành | 2. Đã hoàn thành): ");
            String choice = sc.nextLine().trim();
            if (choice.equals("1")) {
                return TaskStatusEnum.CHUA_HOAN_THANH;
            } else if (choice.equals("2")) {
                return TaskStatusEnum.HOAN_THANH;
            } else {
                System.out.println("Lỗi: Chỉ chọn 1 hoặc 2!");
            }
        }
    }
}
