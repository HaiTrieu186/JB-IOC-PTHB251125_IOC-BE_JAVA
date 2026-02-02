package LT1;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice=-1;
        Staff[] staffs=new Staff[100];
        int total=0;

        do{
            System.out.println("""
                    ------------ MENU ------------
                    1. Thêm mới nhân viên
                    2. Hiển thị danh sách
                    3. Cập nhật thông tin (Theo ID)
                    4. Xóa nhân viên (Theo ID)
                    5. Thoát.
                    """);
            System.out.print("Mời bạn nhập lựa chọn: ");
            choice= Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:{
                    if (total==100) {
                        System.out.println("Đã đầy, không thể thêm mới !");
                        break;
                    }

                    int c=0;
                    do {
                        System.out.println("\n-- Mời bạn chọn ---");
                        System.out.println("1. Lecturer");
                        System.out.println("2. AdminStaff");
                        System.out.print("Mời bạn chọn loại nhân viên (1-2): ");
                        c=Integer.parseInt(sc.nextLine());

                        if (c!=1 && c!=2) {
                            System.out.println("Vui lòng chọn lựa chọn hợp lệ");
                        } else
                            break;
                    }while (true);

                    if (c==1){
                        Lecturer l=new Lecturer();
                        l.inputData(sc);
                        staffs[total++]=l;
                    }else {
                        AdminStaff a=new AdminStaff();
                        a.inputData(sc);
                        staffs[total++]=a;
                    }
                    break;
                }
                case 2:{
                    if (total==0) {
                        System.out.println("Chưa có nhân viên nào !");
                    }

                    Locale vnLocale = new Locale("vi", "VN");
                    NumberFormat vnFormat = NumberFormat.getCurrencyInstance(vnLocale);

                    for (int i = 0; i < total; i++) {
                        Staff staff=staffs[i];

                        System.out.println("----- Nhân viên thứ "+(i+1)+" -----");
                        System.out.println("ID: "+ staff.getId());
                        System.out.println("Họ tên: "+ staff.getName());
                        System.out.println("Lương: "+ vnFormat.format(staff.calculateSalary()));
                        System.out.println();
                    }
                    break;
                }
                case 3:{
                    if (total==0) {
                        System.out.println("Chưa có nhân viên nào !");
                        break;
                    }

                    System.out.print("Nhập ID nhân viên cần sửa: ");
                    int idEdit = Integer.parseInt(sc.nextLine());

                    int index = findStaffById(staffs, total, idEdit);

                    if (index == -1) {
                        System.err.println("Không tìm thấy ID này!");
                    } else {
                        Staff staffFound=staffs[index];
                        System.out.println("Đang sửa nhân viên: " + staffFound.getName());

                        System.out.print("Nhập tên mới (Enter để giữ nguyên): ");
                        String newName = sc.nextLine();
                        if (!newName.isEmpty()) staffFound.setName(newName);

                        if (staffFound instanceof Lecturer) {
                            System.out.print("Mời bạn nhập số giờ dạy (-1 để giữ nguyên): ");
                            int newTeachingHours = Integer.parseInt(sc.nextLine());

                            if (newTeachingHours!=-1) ((Lecturer) staffFound).setTeachingHours(newTeachingHours);
                        }

                        if  (staffFound instanceof AdminStaff) {
                            System.out.print("Mời bạn nhập bonus (-1 để giữ nguyên): ");
                            double newBonus = Double.parseDouble(sc.nextLine());

                            if (newBonus!=-1)  ((AdminStaff) staffFound).setBonus(newBonus);
                        }
                        System.out.println("Update xong!");
                    }
                    break;
                }
                case 4:{
                    if (total==0) {
                        System.out.println("Chưa có nhân viên nào !");
                        break;
                    }

                    System.out.print("Nhập ID nhân viên cần xóa: ");
                    int idEdit = Integer.parseInt(sc.nextLine());

                    int index = findStaffById(staffs, total, idEdit);

                    if (index==-1) {
                        System.err.println("Không tìm thấy ID này!");
                    } else {
                        for (int i=index; i<total-1; i++) {
                            staffs[i]=staffs[i+1];
                        }
                        staffs[total - 1] = null;
                        total--;
                    }

                    break;
                }
                case 5:{
                    System.out.println("Kết thúc chương trình !");
                    sc.close();
                    System.exit(0);
                }
                default:{
                    System.err.println("Vui loòng chon từ 1-5 !");
                }
            }

        }while (true);


    }

    public static int findStaffById(Staff[] staffs, int total, int idCanTim) {
        for (int i = 0; i < total; i++) {
            if (staffs[i].getId() == idCanTim) {
                return i;
            }
        }
        return -1; // Không thấy thì trả về index =-1
    }
}
