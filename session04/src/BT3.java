import java.util.Scanner;

public class BT3 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, find, temp;

        do {
            System.out.print("Mời bạn nhập số lượng phần từ: ");
            n= Integer.parseInt(sc.nextLine());

            if (n<=0){
                System.out.println(RED + "Lỗi: Vui lòng nhập số lượng hợp lệ (n>0)!" + RESET);
            }
        } while (n<=0);

        int[] a=new int[n];

        System.out.println("\n********************************");
        for (int i = 0; i < n; i++) {
            System.out.printf("Mời bạn nhập phần từ thứ %d: ", i+1);
            temp= Integer.parseInt(sc.nextLine());

            a[i]=temp;
        }

        System.out.println("Mảng trước sắp xếp giảm dần là: ");
        for (int i : a) {
            System.out.print(i+" ");
        }

        SelectionSort(a);
        System.out.println("\nMảng sau sắp xếp giảm dần là: ");
        for (int i : a) {
            System.out.print(i+" ");
        }



        System.out.println("\n\n********************************");
        System.out.print("Mời bạn nhập số cần tìm: ");
        find= Integer.parseInt(sc.nextLine());

        int idxLinear = LinearSearch(a, find);
        int idxBinary = BinarySearch(a, find);

        System.out.println("Tìm kiếm tuyến tính (-1 là không tìm thấy): ");
        System.out.printf("\t Số %d nằm ở vị trí %d\n",find,idxLinear);

        System.out.println("Tìm kiếm nhị phân (-1 là không tìm thấy): ");
        System.out.printf("\t Số %d nằm ở vị trí %d",find,idxBinary);


    }

    public static void SelectionSort (int[] a){
        int index;
        for (int i = 0; i < a.length; i++){
            index = i;
            for (int j= i+1; j< a.length; j++){
                if (a[i]< a[j])
                    index= j;
            }

            if (index != i){
                int temp = a[i];
                a[i] = a[index];
                a[index] = temp;
            }
        }
    }

    public static int LinearSearch (int[] a, int key){
        for (int i = 0; i < a.length; i++){
            if (a[i] == key)
                return i;
        }
        return -1;
    }

    public static int BinarySearch (int[] a, int key){
        int left= 0, right= a.length-1, mid;

        while (left <= right){
            mid =  (left+right)/2;

            if (key == a[mid])
                return mid;

            if (key > a[mid]){
                left = mid+1;
            }

            if (key < a[mid]){
                right = mid-1;
            }
        }

        return -1;
    }


}
