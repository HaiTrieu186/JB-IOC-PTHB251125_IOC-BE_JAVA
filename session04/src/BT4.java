import java.util.Scanner;

public class BT4 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, find, temp;

        do {
            System.out.print("Mời bạn nhập số lượng phần từ: ");
            n= Integer.parseInt(sc.nextLine());

            if (n<0){
                System.out.println(RED + "Lỗi: Vui lòng nhập số lượng hợp lệ (n>0)!" + RESET);
            }
        } while (n<0);

        int[] a=new int[n];
        int[] b=new int[n];

        if (n==0){
            System.out.println("Mảng không có phần tử");
            System.exit(0);
        }

        System.out.println("\n********************************");
        for (int i = 0; i < n; i++) {
            System.out.printf("Mời bạn nhập phần từ thứ %d: ", i+1);
            temp= Integer.parseInt(sc.nextLine());

            a[i]=temp;
        }

        System.out.println("Mảng trước sắp xếp là: ");
        for (int i : a) {
            System.out.print(i+" ");
        }

        int j=0;
        for (int i : a) {
            if (i%2==0) {
                b[j] = i;
                j++;
            }
        }

        for (int i : a) {
            if (i%2!=0) {
                b[j] = i;
                j++;
            }
        }

        System.out.println("\nMảng sau sắp xếp là: ");
        for (int i : b) {
            System.out.print(i+" ");
        }






    }
}
