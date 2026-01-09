import java.util.Scanner;

public class BT3 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option=-1, totalEmployee=0;
        double salary, totalSalary=0, avgSalary=0, maxSalary=0, minSalary=500000000, totalBonus=0;

        do {
            System.out.println("\n***************MENU NHẬP LƯƠNG***************");
            System.out.printf("* 1.  %-37s*\n","Nhập lương nhân viên");
            System.out.printf("* 2.  %-37s*\n","Hiển thị thống kê");
            System.out.printf("* 3.  %-37s*\n","Tỉnh tổng số tiền thưởng cho nhân viên");
            System.out.printf("* 4.  %-37s*\n","Thoát");
            System.out.println("********************************************");
            System.out.printf(" - Lựa chọn của bạn: ");
            option= Integer.parseInt(sc.nextLine());

            if (option != 1 && option != 2 && option != 3 && option != 4) {
                System.out.println(RED + "Lỗi: Vui lòng chỉ nhập 1 - 2 - 3 - 4!" + RESET);
                continue;
            }

            if (option == 4) {
                System.out.println(RED + "Kết thúc chương trình, Chào tạm biệt !!!" + RESET);
                System.exit(0);
            }

            if (option == 1){
                System.out.println("\n----------Nhập lương nhân viên (Nhập -1 để dừng)----------");
                do {
                    System.out.printf("\n-----Nhân viên thứ %-5d-----\n",(totalEmployee+1));
                    System.out.print("Nhập lương: ");
                    salary = Double.parseDouble(sc.nextLine());

                    if (salary ==-1){
                        System.out.println("-----------------------------");
                        System.out.println("\n--------------------------------------------------------");
                        break;
                    }

                    if (salary < 0 || salary > 500000000) {
                        System.out.println(RED + "Lỗi: Lương không hợp lệ  0 VNĐ - 500.000.000 VNĐ !" + RESET);
                    } else {
                        totalEmployee++;
                        totalSalary+=salary;
                        avgSalary=totalSalary/totalEmployee;
                        maxSalary= maxSalary <=  salary ?  salary : maxSalary;
                        minSalary= minSalary >=  salary ? salary : minSalary;
                        totalBonus+=getBonus(salary);

                        System.out.println("--> Phân loại: "+ phanLoaiThuNhap(salary));
                        System.out.println("-----------------------------");
                    }
                } while (true);
            }

            if (option == 2){
                System.out.println("\n----------KẾT QUẢ THỐNG KÊ----------");
                if (totalEmployee==0){
                    System.out.println(RED + "Chưa có dữ liệu!" + RESET);
                } else{
                    System.out.println("Số nhân viên: "+totalEmployee);
                    System.out.printf("Lương trung bình: %.0f VNĐ\n",avgSalary);
                    System.out.printf("Lương cao nhất: %.0f VNĐ\n",maxSalary);
                    System.out.printf("Lương thấp nhất: %.0f VNĐ\n",minSalary);
                }
                System.out.println("------------------------------------");
            }

            if (option == 3){
                System.out.println("\n----------Tính tổng số tiền thưởng nhân viên----------");
                if (totalBonus==0){
                    System.out.println(RED + "Chưa có dữ liệu!" + RESET);
                } else{
                    System.out.printf("Tổng số tiền thưởng nhân viên: %.0f VNĐ\n",+totalBonus);
                }
                System.out.println("------------------------------------------------------");
            }


        }while(true);



    }

    public static String phanLoaiThuNhap(double salary){
        if (salary<5000000) {
            return "Thu nhập thấp";
        } else
        if (salary>=5000000 && salary<15000000) {
            return "Thu nhập trung bình";
        } else
        if (salary>=15000000 && salary<50000000) {
            return "Thu nhập khá";
        } else
            return "Thu nhâp cao";
    }

    public static double getBonus(double salary){
        if (salary>=0 && salary<5000000) {
            return salary*0.05;
        } else
        if (salary>=5000000 && salary<15000000) {
            return salary*0.1;
        } else
        if (salary>=15000000 && salary<50000000) {
            return salary*0.15;
        }
        if (salary>=50000000 && salary<100000000) {
            return salary*0.20;
        } else
            return salary*0.25;
    }
}
