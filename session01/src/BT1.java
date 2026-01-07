import java.util.Scanner;

public class BT1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mời bạn nhập bán kính hình tròn: ");
        double r = Double.parseDouble(sc.nextLine());

        double a = Math.PI *r*r;
        System.out.printf("Diện tích hình tròn: %10.2f",a);
    }
}
