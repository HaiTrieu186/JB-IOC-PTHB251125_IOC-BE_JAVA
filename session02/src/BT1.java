import java.util.Scanner;

public class BT1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n;
        do {
            System.out.println("\n-----Tính tổng từ 1 đến n-----");
            System.out.print("Mời bạn nhập n: ");
            n = Integer.parseInt(sc.nextLine());

            if (n<=0)
                System.out.println("Số nhập vào không hợp lệ !");
        }while (n<=0);


        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        System.out.printf("Tổng của %d số đầu tiên là: %d",n,sum);
    }
}
