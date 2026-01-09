import java.util.Scanner;

public class BT2 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option=-1, totalStudent=0;
        double point, totalPoint=0, avgPoint=0, MaxPoint=0, MinPoint=10;

        do {
            System.out.println("\n**********MENU NHẬP ĐIỂM**********");
            System.out.printf("* 1.  %-27s*\n","Nhập điểm học viên");
            System.out.printf("* 2.  %-27s*\n","Hiển thị thống kê");
            System.out.printf("* 3.  %-27s*\n","Thoát");
            System.out.println("**********************************");
            System.out.printf(" - Lựa chọn của bạn: ");
            option= Integer.parseInt(sc.nextLine());

            if (option != 1 && option != 2 && option != 3) {
                  System.out.println(RED + "Lỗi: Vui lòng chỉ nhập 1 - 2 - 3 !" + RESET);
                  continue;
            }

            if (option == 3) {
                System.out.println(RED + "Kết thúc chương trình, Chào tạm biệt !!!" + RESET);
                System.exit(0);
            }

            if (option == 1){
                System.out.println("\n----------Nhập điểm học viên (Nhập -1 để dừng)----------");
                do {
                    System.out.printf("\n-----Sinh viên thứ %-5d-----\n",(totalStudent+1));
                    System.out.print("Nhập diểm: ");
                    point = Double.parseDouble(sc.nextLine());

                    if (point ==-1){
                        System.out.println("----------------------------");
                        System.out.println("\n--------------------------------------------------------");
                        break;
                    }

                    if (point < 0 || point > 10) {
                        System.out.println(RED + "Lỗi: Vui lòng chỉ nhập điểm [0,10] !" + RESET);
                    } else {
                        totalStudent++;
                        totalPoint+=point;
                        avgPoint=totalPoint/totalStudent;
                        MaxPoint= MaxPoint <= point ? point : MaxPoint;
                        MinPoint= MinPoint >= point ? point : MinPoint;


                        System.out.println("Học lực: "+ xepLoai(point));
                        System.out.println("----------------------------");
                    }


                } while (true);
            }

            if (option == 2){
                System.out.println("\n----------KẾT QUẢ THỐNG KÊ----------");
                if (totalStudent==0){
                    System.out.println(RED + "Chưa có dữ liệu!" + RESET);
                } else{
                    System.out.println("Số học viên đã nhập: "+totalStudent);
                    System.out.println("Điểm trung bình: "+ avgPoint);
                    System.out.println("Điểm cao nhất: "+MaxPoint);
                    System.out.println("Điêm thấp nhất: "+MinPoint);
                }
                System.out.println("------------------------------------");
            }


        }while(true);
    }

    public static String xepLoai(double point){
        if (point>=0 && point<5) {
            return "Yếu";
        } else
        if (point>=5 && point<7) {
            return "Trung Bình";
        } else
        if (point>=7 && point<8) {
            return "Khá";
        } else
        if (point>=8 && point<9) {
            return "Giỏi";
        } else
            return "Xuất sắc";
    }
}
