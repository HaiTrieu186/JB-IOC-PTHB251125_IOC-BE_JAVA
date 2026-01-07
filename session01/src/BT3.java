import java.util.Scanner;

public class BT3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Phân số đầu tiên ---");
        System.out.print("Mời bạn nhập tử số: ");
        int a = Integer.parseInt(sc.nextLine());
        System.out.print("Mời bạn nhập mẫu số: ");
        int b = Integer.parseInt(sc.nextLine());

        System.out.println("\n--- Phân số thứ hai ---");
        System.out.print("Mời bạn nhập tử số: ");
        int c = Integer.parseInt(sc.nextLine());
        System.out.print("Mời bạn nhập mẫu số: ");
        int d = Integer.parseInt(sc.nextLine());

        // Phân số mới
        int f=(a*d+b*c);
        int g=(b*d);


        System.out.println("\nTổng hai phân số là: "+ f +"/"+g);
    }
}
