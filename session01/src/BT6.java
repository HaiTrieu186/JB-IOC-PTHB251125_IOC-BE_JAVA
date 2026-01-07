import java.util.Scanner;

public class BT6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Mời bạn nhập vận tốc (km/h): ");
        float v = Float.parseFloat(sc.nextLine());
        System.out.print("Mời bạn nhập thời gian (gio): ");
        float t = Float.parseFloat(sc.nextLine());

        float s = v*t;

        System.out.printf("Quãng đường (km) là: %.2f\n",s);
    }
}
