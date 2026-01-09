import java.util.Scanner;

public class BT3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("\n-----Tính tổng các chữ số-----");
        System.out.print("Mời bạn số: ");
        n = Integer.parseInt(sc.nextLine());

        int sum=0;
        while(n>0){
            sum+=n%10;
            n/=10;
        }

        System.out.printf("Tổng của các chữ số trong %d là: %d",n,sum);
    }
}
