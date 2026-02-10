package LT02.presentation;

import LT02.Exception.EmptyInputException;
import LT02.business.impl.SubjectManager;
import LT02.model.Subject;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static SubjectManager subjectManager = new SubjectManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            choice = showMenu(sc);
            switch (choice) {
                case 1: {
                    handleAddSubject(sc);
                    break;
                }
                case 2: {
                    handleDeleteSubject(sc);
                    break;
                }
                case 3: {
                    handleUpdateSubject(sc);
                    break;
                }
                case 4: {
                    handleDisplayList();
                    break;
                }
                case 5: {
                    handleFindByName(sc);
                    break;
                }
                case 6: {
                    handleFindByCredits(sc);
                    break;
                }
                case 7: {
                    System.out.println("Thoát chương trình !");
                    sc.close();
                    System.exit(0);
                }
                default: {
                    System.out.println("Lỗi: Vui lòng chọn lựa chọn hợp lệ (1-7) !");
                }
            }
        }
    }

    private static void handleFindByName(Scanner sc) {
        String name;

        System.out.println("\n--- TÌM MÔN HỌC THEO TÊN ---");
        while (true) {
            try {
                name = Subject.inputName(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Subject> matches = subjectManager.findSubjectsByName(name);

        if (matches.isEmpty()) {
            System.out.println("Không tìm thấy môn học nào trùng khớp !");
            return;
        }

        System.out.println("-- KẾT QUẢ TÌM KIẾM --");
        int i = 1;
        SubjectManager.displayList(matches);

    }

    private static void handleFindByCredits(Scanner sc) {
        int credits;

        System.out.println("\n--- TÌM MÔN HỌC THEO TÍN CHỈ ---");
        while (true) {
            try {
                credits = Subject.inputCredits(sc);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Subject> matches = subjectManager.findSubjectsByCredit(credits);

        if (matches.isEmpty()) {
            System.out.println("Không tìm thấy môn học nào trùng khớp !");
            return;
        }

        System.out.println("-- KẾT QUẢ TÌM KIẾM --");
        SubjectManager.displayList(matches);
    }

    private static void handleDeleteSubject(Scanner sc) {
        String codeDelete;
        System.out.println("\n--- XÓA MÔN HỌC---");
        while (true) {
            try {
                codeDelete = Subject.inputCode(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!subjectManager.isExistByCode(codeDelete)) {
            System.out.println("Môn học với ID [" + codeDelete + "] không tồn tại !");
            return;
        }

        subjectManager.delete(codeDelete);
        System.out.println("Môn học với ID [" + codeDelete + "] đã được xóa thành công !");

    }

    private static void handleUpdateSubject(Scanner sc) {
        String codeUpdate;
        System.out.println("\n--- SỬA MÔN HỌC ---");
        while (true) {
            try {
                codeUpdate = Subject.inputCode(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        Subject updateSubject = subjectManager.findByCode(codeUpdate);

        if (updateSubject == null) {
            System.out.println("Môn học với ID [" + codeUpdate + "] không tồn tại !");
            return;
        }

        System.out.println("-- THÔNG TIN MÔN HỌC HIỆN TẠI: --");
        updateSubject.display();

        int choice;
        boolean exitFlag = false;
        while (!exitFlag) {
            choice = showMenuUpdate(sc);
            switch (choice) {
                case 1: {
                    String name;
                    while (true) {
                        try {
                            name=Subject.inputName(sc);
                            break;
                        } catch (EmptyInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    updateSubject.setName(name);
                    break;
                }
                case 2: {
                    int credits ;
                    while (true) {
                        try {
                            credits= Subject.inputCredits(sc);
                            break;
                        }catch (NumberFormatException e){
                            System.out.println(e.getMessage());
                        }catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    updateSubject.setCredits(credits);
                    break;
                }
                case 3: {
                    LocalDate date;
                    while (true) {
                        try {
                            date = Subject.inputStartDate(sc);
                            break;
                        } catch (EmptyInputException e) {
                            System.out.println(e.getMessage());
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    updateSubject.setStartDate(date);
                    break;
                }
                case 0: {
                    System.out.println("Kết thúc cập nhật");
                    exitFlag = true;
                    break;
                }
                default: {
                    System.out.println("Lỗi: Vui lòng chọn lựa chọn hợp lệ (0-4) !");
                }
            }
        }
        subjectManager.update(updateSubject, codeUpdate);
    }

    private static void handleDisplayList() {
        System.out.println("\n--- DANH SÁCH MÔN HỌC ---");
        SubjectManager.displayList(subjectManager.findAll());
    }

    private static void handleAddSubject(Scanner sc) {
        System.out.println("\n--- THÊM MÔN HỌC MỚI ---");
        String code;

        while (true) {
            try {
                code = Subject.inputCode(sc);

                if (subjectManager.isExistByCode(code)) {
                    System.out.println("Lỗi: Code [" + code + "] đã tồn tại, vui lòng nhập ID khác!");
                } else {
                    break;
                }
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        Subject subject=new Subject();
        subject.setCode(code);
        subject.input(sc);

        subjectManager.add(subject);
        System.out.println("Môn học đã được thêm thành công !");
    }

    private static int showMenuUpdate(Scanner sc) {
        int choice;
        System.out.println("- MENU UPDATE MÔN HỌC -");
        System.out.println("""
                1. Update: Tên môn học
                2. Update: Số tín chỉ
                3. Update: Ngày bắt đầu
                0. Thoát
                """);
        while (true) {
            try {
                System.out.print("Mời bạn chọn: ");
                choice = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng chỉ nhập định dạng số hợp lệ (0-3)");
            }
        }
        return choice;
    }

    private static int showMenu(Scanner sc) {
        int choice;
        System.out.println("""
                ------ QUẢN LÝ MÔN HỌC -------
                1. Thêm môn học
                2. Xóa môn học
                3. Sửa môn học
                4. Hiển thị môn học
                5. Tìm kiếm môn học theo tên
                6. Lọc phim môn học số tín chỉ
                7. Thoát
                ---------------------------""");

        while (true) {
            try {
                System.out.print("Mời bạn chọn: ");
                choice = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng chỉ nhập định dạng số hợp lệ (1-7)");
            }
        }

        return choice;
    }
}
