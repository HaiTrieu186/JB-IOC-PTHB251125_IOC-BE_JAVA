package BT2;

import java.util.Scanner;

public class Main {
    private static final AttendanceManager attendanceManager = new AttendanceManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("""
                    ------------- MENU QUẢN LÝ ĐIỂM DANH -------------
                    1. Thếm sinh viên
                    2. Sửa sinh viên
                    3. Xóa sinh viên
                    4. Hiển thị danh sách sinh viên
                    5. Thoát
                    """);
            System.out.print("Mời bạn lựa chọn (1-5): ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:{
                    Student student = new Student();
                    student.inputData(sc);

                    if (!attendanceManager.checkIdExist(student.getId())) {
                        attendanceManager.add(student);
                        System.out.println("Sinh viên đã được thêm thành công !");
                        break;
                    }

                    System.out.println("ID Đã tồn tại vui lòng nhập lại !");
                    int id;
                    do {
                        System.out.print("Mời bạn nhập id: ");
                        id=Integer.parseInt(sc.nextLine());

                        if (attendanceManager.checkIdExist(id)){
                            System.out.println("ID Đã tồn tại vui lòng nhập lại !");
                        }else {
                            student.setId(id);
                            attendanceManager.add(student);
                            System.out.println("Sinh viên đã được thêm thành công !");
                            break;
                        }

                    }while (true);

                    break;}
                case 2:{
                    System.out.println("------- DANH SÁCH HIỆN TẠI -------");
                    attendanceManager.display();

                    System.out.print("Mời bạn nhập id cần sửa: ");
                    int id=Integer.parseInt(sc.nextLine());

                    if (!attendanceManager.checkIdExist(id)){
                        System.out.println("ID không tồn tại");
                    } else{
                        Student student = attendanceManager.findById(id);
                        System.out.print("Mời bạn nhập tên mới: ");
                        student.setName(sc.nextLine());
                    }

                    System.out.println("Sinh viên đã được sửa thành công !");

                    break;}
                case 3:{
                    System.out.println("------- DANH SÁCH HIỆN TẠI -------");
                    attendanceManager.display();

                    System.out.print("Mời bạn nhập id cần sửa: ");
                    int id=Integer.parseInt(sc.nextLine());

                    if (!attendanceManager.checkIdExist(id)){
                        System.out.println("ID không tồn tại");
                        break;
                    } else{
                        attendanceManager.delete(id);
                    }
                    System.out.println("Đã xóa thành công sinh viên");
                    break;}
                case 4:{
                    if (attendanceManager.getStudents().isEmpty()){
                        System.out.println("Chưa có sinh viên nào !");
                        break;
                    }

                    System.out.println("-------- DANH SÁCH HIỆN TẠI --------");
                    attendanceManager.display();
                    break;}
                case 5:{
                    System.out.println("Thoát chương trình!");
                    sc.close();
                    System.exit(0);
                }
                default:{
                    System.out.println("Vui lòng chọn lựa chọn hợp lệ");
                }
            }
        }
    }
}
