package BT2.presentation;

import BT2.business.TaskManagement;
import BT2.model.Task;
import BT2.model.TaskStatusEnum;
import BT2.util.InputUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final TaskManagement taskManagement = new TaskManagement();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ TO-DO LIST =====");
            System.out.println("1. Thêm công việc");
            System.out.println("2. Liệt kê công việc");
            System.out.println("3. Cập nhật trạng thái");
            System.out.println("4. Xóa công việc");
            System.out.println("5. Tìm kiếm công việc");
            System.out.println("6. Thống kê công việc");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn chức năng (0-6): ");

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddTask(sc);
                    break;
                case 2:
                    handleListTasks();
                    break;
                case 3:
                    handleUpdateTaskStatus(sc);
                    break;
                case 4:
                    handleDeleteTask(sc);
                    break;
                case 5:
                    handleSearchTaskByName(sc);
                    break;
                case 6:
                    handleTaskStatistics();
                    break;
                case 0:
                    System.out.println("Kết thúc chương trình!");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 0 đến 6!");
            }
        }
    }

    private static void handleAddTask(Scanner sc) {
        System.out.println("\n--- THÊM CÔNG VIỆC MỚI ---");
        String taskName = InputUtil.inputString(sc, "Nhập tên công việc: ");
        TaskStatusEnum status = InputUtil.inputStatus(sc);

        taskManagement.addTask(taskName, status.name());
    }

    private static void handleListTasks() {
        System.out.println("\n--- DANH SÁCH CÔNG VIỆC ---");
        List<Task> list = taskManagement.listTasks();
        displayTasks(list);
    }

    private static void handleUpdateTaskStatus(Scanner sc) {
        System.out.println("\n--- CẬP NHẬT TRẠNG THÁI ---");
        int id = InputUtil.inputInt(sc, "Nhập ID công việc cần cập nhật: ");

        TaskStatusEnum status = InputUtil.inputStatus(sc);
        boolean success = taskManagement.updateTaskStatus(id, status.name());

        if (success) {
            System.out.println("Cập nhật trạng thái thành công!");
        } else {
            System.out.println("Cập nhật thất bại (Không tìm thấy ID hoặc có lỗi hệ thống)!");
        }
    }

    private static void handleDeleteTask(Scanner sc) {
        System.out.println("\n--- XÓA CÔNG VIỆC ---");
        int id = InputUtil.inputInt(sc, "Nhập ID công việc cần xóa: ");

        boolean success = taskManagement.deleteTask(id);
        if (success) {
            System.out.println("Đã xóa công việc thành công!");
        } else {
            System.out.println("Xóa thất bại (Không tìm thấy ID hoặc có lỗi hệ thống)!");
        }
    }

    private static void handleSearchTaskByName(Scanner sc) {
        System.out.println("\n--- TÌM KIẾM CÔNG VIỆC ---");
        String taskName = InputUtil.inputString(sc, "Nhập từ khóa tên công việc: ");

        List<Task> list = taskManagement.searchTaskByName(taskName);
        if (list.isEmpty()) {
            System.out.println("Không tìm thấy công việc nào chứa từ khóa: " + taskName);
        } else {
            displayTasks(list);
        }
    }

    private static void handleTaskStatistics() {
        System.out.println("\n--- THỐNG KÊ CÔNG VIỆC ---");
        taskManagement.taskStatistics();
    }

    // Hàm hỗ trợ in bảng danh sách chuẩn Form đã kẻ ô
    private static void displayTasks(List<Task> list) {
        if (list.isEmpty()) {
            System.out.println("Danh sách công việc đang trống.");
        } else {
            System.out.printf("+%s+%s+%s+\n", "-".repeat(7), "-".repeat(32), "-".repeat(22));
            System.out.printf("| %-5s | %-30s | %-20s |\n", "ID", "Tên công việc", "Trạng thái");
            System.out.printf("+%s+%s+%s+\n", "-".repeat(7), "-".repeat(32), "-".repeat(22));

            for (Task t : list) {
                String statusDisplay = t.getStatus() == TaskStatusEnum.HOAN_THANH
                        ? "Đã hoàn thành" : "Chưa hoàn thành";

                System.out.printf("| %-5d | %-30s | %-20s |\n",
                        t.getId(), t.getTask_name(), statusDisplay);
            }
            System.out.printf("+%s+%s+%s+\n", "-".repeat(7), "-".repeat(32), "-".repeat(22));
        }
    }
}