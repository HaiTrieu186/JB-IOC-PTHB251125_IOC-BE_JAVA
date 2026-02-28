package BT3.presentation;

import BT3.business.BookManager;
import BT3.exception.LibraryException;
import BT3.model.Book;
import BT3.util.InputUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final BookManager bookManager = new BookManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ THƯ VIỆN =====");
            System.out.println("1. Thêm sách mới");
            System.out.println("2. Hiển thị tất cả sách");
            System.out.println("3. Cập nhật thông tin sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Tìm kiếm sách theo tác giả");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn chức năng (0-5): ");

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddBook(sc);
                    break;
                case 2:
                    handleListBooks();
                    break;
                case 3:
                    handleUpdateBook(sc);
                    break;
                case 4:
                    handleDeleteBook(sc);
                    break;
                case 5:
                    handleSearchBookByAuthor(sc);
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình quản lý thư viện!");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 0 đến 5!");
            }
        }
    }

    // 1. Thêm sách
    private static void handleAddBook(Scanner sc) {
        System.out.println("\n--- THÊM SÁCH MỚI ---");
        String title = InputUtil.inputString(sc, "Nhập tiêu đề sách: ");
        String author = InputUtil.inputString(sc, "Nhập tên tác giả: ");
        int year = InputUtil.inputInt(sc, "Nhập năm xuất bản: ");
        BigDecimal price = InputUtil.inputBigDecimal(sc, "Nhập giá tiền: ");

        // id để 0 vì ko truyền vào, DB sẽ tự tăng
        Book newBook = new Book(0, title, author, year, price);

        try {
            bookManager.addBook(newBook);
            System.out.println("Đã thêm sách mới thành công!");
        } catch (LibraryException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // 2. Hiển thị tất cả sách
    private static void handleListBooks() {
        System.out.println("\n--- DANH SÁCH TOÀN BỘ SÁCH ---");
        List<Book> list = bookManager.listAllBooks();
        displayBooks(list);
    }

    // 3. Cập nhật sách
    private static void handleUpdateBook(Scanner sc) {
        System.out.println("\n--- CẬP NHẬT THÔNG TIN SÁCH ---");
        int id = InputUtil.inputInt(sc, "Nhập ID sách cần cập nhật: ");

        String title = InputUtil.inputString(sc, "Nhập tiêu đề mới: ");
        String author = InputUtil.inputString(sc, "Nhập tác giả mới: ");
        int year = InputUtil.inputInt(sc, "Nhập năm xuất bản mới: ");
        BigDecimal price = InputUtil.inputBigDecimal(sc, "Nhập giá tiền mới: ");

        Book updatedBook = new Book(id, title, author, year, price);

        try {
            bookManager.updateBook(id, updatedBook);
            System.out.println("Cập nhật thông tin sách thành công!");
        } catch (LibraryException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // 4. Xóa sách
    private static void handleDeleteBook(Scanner sc) {
        System.out.println("\n--- XÓA SÁCH ---");
        int id = InputUtil.inputInt(sc, "Nhập ID sách cần xóa: ");

        try {
            bookManager.deleteBook(id);
            System.out.println("Đã xóa sách thành công!");
        } catch (LibraryException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // 5. Tìm kiếm theo tác giả
    private static void handleSearchBookByAuthor(Scanner sc) {
        System.out.println("\n--- TÌM KIẾM SÁCH ---");
        String author = InputUtil.inputString(sc, "Nhập tên tác giả cần tìm: ");

        List<Book> list = bookManager.findBooksByAuthor(author);
        if (list.isEmpty()) {
            System.out.println("Không tìm thấy cuốn sách nào của tác giả: " + author);
        } else {
            System.out.println("Kết quả tìm kiếm cho tác giả '" + author + "':");
            displayBooks(list);
        }
    }

    // Hàm hiển thị danh sách Book
    private static void displayBooks(List<Book> list) {
        if (list.isEmpty()) {
            System.out.println("Thư viện hiện tại đang trống.");
            return;
        }

        System.out.printf("+%s+%s+%s+%s+%s+\n",
                "-".repeat(6), "-".repeat(32), "-".repeat(25), "-".repeat(8), "-".repeat(15));
        System.out.printf("| %-4s | %-30s | %-23s | %-6s | %-13s |\n",
                "ID", "Tiêu đề", "Tác giả", "Năm XB", "Giá tiền");
        System.out.printf("+%s+%s+%s+%s+%s+\n",
                "-".repeat(6), "-".repeat(32), "-".repeat(25), "-".repeat(8), "-".repeat(15));

        for (Book b : list) {
            System.out.printf("| %-4d | %-30s | %-23s | %-6d | %-13s |\n",
                    b.getId(),
                    b.getTitle(),
                    b.getAuthor(),
                    b.getPublishedYear(),
                    // format lại BigDecimal để in ra đẹp hơn (VD: 45000.00)
                    String.format("%.2f", b.getPrice()));
        }
        System.out.printf("+%s+%s+%s+%s+%s+\n",
                "-".repeat(6), "-".repeat(32), "-".repeat(25), "-".repeat(8), "-".repeat(15));
    }
}