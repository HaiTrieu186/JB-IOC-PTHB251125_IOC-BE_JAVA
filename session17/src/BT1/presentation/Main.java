package BT1.presentation;

import BT1.business.MovieManager;
import BT1.model.Movie;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final MovieManager movieManager = new MovieManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== CHƯƠNG TRÌNH QUẢN LÝ PHIM =====");
            System.out.println("1. Thêm phim");
            System.out.println("2. Liệt kê phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Xóa phim");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn chức năng (0-4): ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    handleAddMovie(sc);
                    break;
                case "2":
                    handleListMovies();
                    break;
                case "3":
                    handleUpdateMovie(sc);
                    break;
                case "4":
                    handleDeleteMovie(sc);
                    break;
                case "0":
                    System.out.println("Kết thúc chương trình!");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    // Xử lý thêm phim
    private static void handleAddMovie(Scanner sc) {
        System.out.println("\n--- THÊM PHIM MỚI ---");
        String title = inputString(sc, "Nhập tiêu đề phim: ");
        String director = inputString(sc, "Nhập tên đạo diễn: ");
        int year = inputInt(sc, "Nhập năm phát hành: ");

        movieManager.addMovie(title, director, year);
    }

    // Xử lý hiển thị danh sách phim
    private static void handleListMovies() {
        System.out.println("\n--- DANH SÁCH PHIM ---");
        List<Movie> list = movieManager.listMovies();
        if (list.isEmpty()) {
            System.out.println("Chưa có bộ phim nào trong cơ sở dữ liệu.");
        } else {
            System.out.printf("+%s+%s+%s+%s+\n","-".repeat(7), "-".repeat(27),"-".repeat(22), "-".repeat(7));
            System.out.printf("| %-5s | %-25s | %-20s | %-5s |\n", "ID", "Tiêu đề", "Đạo diễn", "Năm");
            System.out.printf("+%s+%s+%s+%s+\n","-".repeat(7), "-".repeat(27),"-".repeat(22), "-".repeat(7));
            for (Movie m : list) {
                System.out.printf("| %-5d | %-25s | %-20s | %-5d |\n",
                        m.getId(), m.getTitle(), m.getDirector(), m.getYear());
            }
            System.out.printf("+%s+%s+%s+%s+\n","-".repeat(7), "-".repeat(27),"-".repeat(22), "-".repeat(7));
        }
    }

    // Xử lý sửa thông tin phim
    private static void handleUpdateMovie(Scanner sc) {
        System.out.println("\n--- SỬA THÔNG TIN PHIM ---");
        int id = inputInt(sc, "Nhập ID phim cần sửa: ");

        if (!movieManager.isExist(id)) {
            System.out.println("Lỗi: Không tìm thấy phim có ID = " + id);
            return;
        }

        String title = inputString(sc, "Nhập tiêu đề mới: ");
        String director = inputString(sc, "Nhập đạo diễn mới: ");
        int year = inputInt(sc, "Nhập năm phát hành mới: ");

        boolean success = movieManager.updateMovie(id, title, director, year);
        if (success) {
            System.out.println("Cập nhật thông tin phim thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
        }
    }

    // Xử lý xóa phim
    private static void handleDeleteMovie(Scanner sc) {
        System.out.println("\n--- XÓA PHIM ---");
        int id = inputInt(sc, "Nhập ID phim cần xóa: ");

        if (!movieManager.isExist(id)) {
            System.out.println("Lỗi: Không tìm thấy phim có ID = " + id);
            return;
        }

        boolean success = movieManager.deleteMovie(id);
        if (success) {
            System.out.println("Đã xóa phim thành công!");
        } else {
            System.out.println("Xóa phim thất bại!");
        }
    }

    // Hàm hỗ trợ: Nhập chuỗi
    private static String inputString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Lỗi: Không được để trống giá trị này. Vui lòng nhập lại!");
            } else {
                return input;
            }
        }
    }

    // Hàm hỗ trợ: Nhập số nguyên
    private static int inputInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int number = Integer.parseInt(sc.nextLine().trim());
                if (number <= 0) {
                    System.out.println("Lỗi: Vui lòng nhập số lớn hơn 0!");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Sai định dạng số. Vui lòng nhập lại!");
            }
        }
    }
}