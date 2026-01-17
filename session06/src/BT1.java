import java.util.Arrays;
import java.util.Scanner;

public class BT1 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice;
        double[] studentPointList=null;
        boolean flag=false;

        do{
            System.out.println("""
                    \n+-------------------------QUẢN LÝ ĐIỂM SV--------------------------+
                    1. Nhập danh sách điểm sinh viên
                    2. In danh sách điểm
                    3. Tính điểm trung bình của các sinh viên
                    4. Tìm điểm cao nhất và thấp nhất
                    5. Đếm số lượng sinh viên đạt và trượt
                    6. Sắp xếp điểm tăng dần
                    7. Thống kê số lượng sinh viên giỏi và xuất sắc
                    8. Thoát
                    +-------------------------MENU--------------------------+""");
            System.out.print("Vui lòng chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            if (choice>=2 && choice<=7) {
                if (!flag){
                    System.out.println(RED + "Lỗi: Vui lòng nhập danh sách sinh viên trước!" + RESET);
                    continue;
                }
            }

            switch (choice) {
                case 1:{
                    flag=true;
                    int number;
                     do {
                         System.out.print("Mời bạn nhập số lượng sinh viên: ");
                         number = Integer.parseInt(sc.nextLine());

                         if (number<=0) {
                             System.out.println(RED + "Lỗi: Vui lòng nhập số lượng sinh viên hợp lệ!" + RESET);
                         }
                     }while (number<=0);

                        studentPointList= new double[number];
                        for (int i=0; i<studentPointList.length; i++){
                            System.out.print("Sinh viên thứ "+(i+1)+": ");
                            studentPointList[i]=Double.parseDouble(sc.nextLine());

                            if (studentPointList[i]<0 || studentPointList[i]>10) {
                                System.out.println(RED + "Lỗi: Vui lòng nhập điểm hợp lệ [0-10]!" + RESET);
                                i--;
                            }
                        }
                    break;
                }
                case 2:{

                    System.out.println("------ Danh sách sinh viên ------");
                    printList(studentPointList);
                    break;
                }
                case 3:{
                    double sumPoint=0.0;

                    for (double point:studentPointList){
                        sumPoint+=point;
                    }

                    System.out.printf("Điểm trung bình là: %.2f\n", sumPoint/studentPointList.length);

                    break;
                }
                case 4:{
                    double maxPoint=studentPointList[0],
                            minPoint=studentPointList[0];

                    for (double point:studentPointList){
                        if (point>maxPoint){
                            maxPoint=point;
                        }
                        if (point<minPoint){
                            minPoint=point;
                        }
                    }


                    System.out.printf("Điểm cao nhất là : %.2f\n", maxPoint);
                    System.out.printf("Điểm thấp nhất là: %.2f\n", minPoint);
                    break;
                }
                case 5:{
                    int countPass=0, countFail=0;

                    for (double point:studentPointList){
                        if (point >=5)
                            countPass++;
                        else
                            countFail++;
                    }

                    System.out.println("Số sinh viên trượt là: "+countFail);
                    System.out.println("Số sinh viên đạt là  : "+countPass);
                    break;
                }
                case 6:{
                    sortList(studentPointList, 0);
                    System.out.println("Đã sắp xếp danh sách điểm tăng dần");
                    break;
                }
                case 7:{
                    int countGood=0;

                    for (double point:studentPointList){
                        if (point >=8)
                            countGood++;
                    }

                    System.out.println("Số sinh viên giỏi và xuất sắc là: "+countGood);

                    break;
                }
                case 8:{
                    System.out.println("Kết thúc chương trình !");
                    sc.close();
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println(RED + "Lỗi: Vui lòng nhập số lượng hợp lệ (1-8)!" + RESET);
                    break;
                }
            }

        }while (true);



    }

    public static void printList(double[] ds){
        System.out.printf("\n+%s+%s+\n","-".repeat(7),"-".repeat(32));
        System.out.printf("| %-5s | %-30s |\n","STT","Điểm số");
        System.out.printf("+%s+%s+\n","-".repeat(7),"-".repeat(32));

        for (int i=0;i<ds.length;i++) {
            System.out.printf("| %-5s | %-30s |\n",(i+1),ds[i]);
        }
        System.out.printf("+%s+%s+\n","-".repeat(7),"-".repeat(32));
    }

    public static void sortList(double[] ds, int order){
        double temp;

        for (int i=0;i<ds.length-1;i++){
            for (int j=0;j<ds.length-i-1;j++){

                boolean check= order==0 ? ds[j]>ds[j+1] :ds[j]<ds[j+1];
                if (check){
                    temp=ds[j];
                    ds[j]=ds[j+1];
                    ds[j+1]=temp;
                }
            }
        }
    }
}
