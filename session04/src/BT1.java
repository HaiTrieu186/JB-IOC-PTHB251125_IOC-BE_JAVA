import java.util.Scanner;

public class BT1 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int n, temp;

         do {
             System.out.print("Mời bạn nhập số lượng phần từ: ");
             n= Integer.parseInt(sc.nextLine());

             if (n<=0){
                 System.out.println(RED + "Lỗi: Vui lòng nhập số lượng hợp lệ (n>0)!" + RESET);
             }
         } while (n<=0);

         int[] a=new int[n];

         for (int i = 0; i < n; i++) {
             System.out.printf("Mời bạn nhập phần từ thứ %d: ", i+1);
             temp= Integer.parseInt(sc.nextLine());

             a[i]=temp;
         }

        System.out.println("Mảng trước sắp xếp giảm dần là: ");
        for (int i : a) {
            System.out.print(i+" ");
        }

        BubbleSort(a);
        System.out.println("\nMảng sau sắp xếp giảm dần là: ");
        for (int i : a) {
            System.out.print(i+" ");
        }

    }

    public static void BubbleSort (int[] a){
        int temp;
        for (int i = 0; i < a.length - 1; i++)
            for (int j= 0; j< a.length -1 - i; j++){
                if (a[j] < a[j+1]){
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
    }
}
