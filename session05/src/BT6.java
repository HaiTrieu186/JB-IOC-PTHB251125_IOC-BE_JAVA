import java.util.Arrays;
import java.util.Scanner;

public class BT6 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice, studentNumber=0;
        String[] ds=new String[100];

        do{
                System.out.println("""
                    \n+-------------------------MENU--------------------------+
                    1. Thêm tên sinh viên
                    2. Hiển thị danh sách
                    3. Tìm tên sinh viên chứa từ khóa
                    4. Đếm số sinh viên bắt đầu với từ khóa
                    5. Sắp xếp danh sách theo thứ tự A-Z
                    6. Thoát
                    +-------------------------MENU--------------------------+
                   """);
                System.out.print("Vui lòng chọn chức năng: ");
                choice = Integer.parseInt(sc.nextLine());

                if (choice>=3 && choice<=5) {
                    if (studentNumber==0){
                        System.out.println(RED + "Lỗi: Vui lòng thêm sinh viên trước!" + RESET);
                        continue;
                    }
                }

                switch (choice) {
                    case 1:{
                        if (studentNumber== ds.length) {
                           ds= Arrays.copyOf(ds, ds.length*2);
                        }

                        System.out.print("Mời bạn nhập tên sinh viên thứ "+(studentNumber+1)+": ");
                        ds[studentNumber]=sc.nextLine();
                        System.out.println("Đã thêm: "+ RED + ds[studentNumber] + RESET);

                        studentNumber++;

                        break;
                    }
                    case 2:{

                        System.out.println("------ Danh sách sinh viên ------");

                        if(studentNumber==0) {
                            System.out.println("Chưa có sinh viên nào trong danh sách !\n");
                            break;
                        }

                        printList(ds, studentNumber);
                        break;
                    }
                    case 3:{
                        System.out.println("Mời bạn nhập từ khóa: ");
                        String keyword= sc.nextLine().trim().toLowerCase();
                        String[] matchesList= new String[studentNumber];
                        int n=0;

                        System.out.print("Kết quả tìm kiếm: ");

                        for (int i = 0; i < studentNumber; i++) {
                            if (ds[i].toLowerCase().contains(keyword)) {
                                matchesList[n++]=ds[i];
                            }
                        }
                        if (n==0)
                            System.out.println("Không có sinh viên nào trùng khớp !");
                        else
                            printList(matchesList, n);

                        break;
                    }
                    case 4:{
                        System.out.println("Mời bạn nhập từ khóa: ");
                        String keyword= sc.nextLine().trim();
                        int n=0;

                        for (int i = 0; i < studentNumber; i++) {
                            if (ds[i].toLowerCase().startsWith(keyword.toLowerCase())) {
                                n++;
                            }
                        }

                        System.out.println("Số sinh viên bắt đầu bằng "+keyword+" là : "+ n);

                        break;
                    }
                    case 5:{

                        // 0: là A-Z, 1: Z-A
                        sortList(ds, studentNumber, 0);
                        System.out.println("Danh sách đã được sắp xếp A-Z !");
                        break;
                    }
                    case 6:{
                        System.out.println("Kết thúc chương trình !");
                        sc.close();
                        System.exit(0);
                        break;
                    }
                    default:{
                        System.out.println(RED + "Lỗi: Vui lòng nhập số lượng hợp lệ (1-6)!" + RESET);
                        break;
                    }
                }

        }while (true);



    }

    public static void printList(String[] ds, int studentNumber){
        System.out.printf("\n+%s+%s+\n","-".repeat(7),"-".repeat(32));
        System.out.printf("| %-5s | %-30s |\n","STT","Họ và tên");
        System.out.printf("+%s+%s+\n","-".repeat(7),"-".repeat(32));

        for (int i=0;i<studentNumber;i++) {
            System.out.printf("| %-5s | %-30s |\n",(i+1),ds[i]);
        }
        System.out.printf("+%s+%s+\n","-".repeat(7),"-".repeat(32));
    }

    public static void sortList(String[] ds,int studentNumber, int order){
        String temp;

        for (int i=0;i<studentNumber-1;i++){
            for (int j=0;j<studentNumber-i-1;j++){

                boolean check= order==0 ? ds[j].compareToIgnoreCase(ds[j+1]) > 0 : ds[j].compareToIgnoreCase(ds[j+1]) < 0;
                if (check){
                    temp=ds[j];
                    ds[j]=ds[j+1];
                    ds[j+1]=temp;
                }
            }
        }
    }
}
