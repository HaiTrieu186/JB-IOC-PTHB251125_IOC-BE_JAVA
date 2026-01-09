import java.util.Scanner;

public class BT2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m;
            System.out.println("\n-----Tìm số ngày theo tháng-----");
            System.out.print("Mời bạn nhập tháng 1-12: ");
            m = Integer.parseInt(sc.nextLine());



        switch (m){
            case 1,3,5,7,8,10,12:{
                System.out.printf("Tháng %d có 31 ngày !",m);
                break;
            }
            case 4,6,9,11:{
                System.out.printf("Tháng %d có 30 ngày !",m);
                break;
            }
            case 2:{
                System.out.printf("Tháng 2 có 28 hoặc 29 ngày !");
                break;
            }
            default:{
                System.out.println("Tháng không hợp lệ !");
            }

        }
    }
}
