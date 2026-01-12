import java.util.Scanner;

public class BT2 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m,n, sumEven=0, sumOdd=0;

        do {
            System.out.print("Mời bạn nhập số hàng của mảng : ");
            m= Integer.parseInt(sc.nextLine());

            System.out.print("Mời bạn nhập số cột của mảng : ");
            n= Integer.parseInt(sc.nextLine());

            if (n<=0 || m<=0){
                System.out.println(RED + "Lỗi: Vui lòng nhập số hàng và cột hợp lệ (Lớn hơn 0)!" + RESET);
            }
        } while (n<=0 || m<=0);

        int[][] a= new int[m][n];

        for (int i=0; i<m; i++)
            for (int j=0; j<n; j++){
                System.out.printf("Nhập phần từ [%d][%d]: ", i, j);
                a[i][j]= Integer.parseInt(sc.nextLine());
            }

        System.out.println("Mảng vừa nhập là");
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++){
                System.out.printf("%-5d ", a[i][j]);
            }
            System.out.println();
        }

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++){
               if (a[i][j]%2==0)
                   sumEven+=a[i][j];
               else
                   sumOdd+=a[i][j];
            }
        }

        System.out.println("Tổng các số chẵn: "+ sumEven);
        System.out.println("Tổng các số lẻ: "+ sumOdd);

    }
}
