import java.util.Scanner;

public class BT6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        do{
            System.out.println("\n-----Tìm số Amstrong trong khoảng 1-n-----");
            System.out.print("Mời bạn nhập số n (nguyên dương): ");
            n = Integer.parseInt(sc.nextLine());

            if (n<0)
                System.out.println("Vui lòng chỉ nhập số nguyên dương!");
        } while (n<0);

        int temp, digitCount;
        double sum;

        System.out.print("Kết quả: ");
        for (int i=0;i<=n;i++){
            temp=i;
            digitCount=0;
            sum=0.0;

            while (temp!=0){
                digitCount++;
                temp=temp/10;
            }

            temp=i;

            while (temp!=0){
                sum+= Math.pow(temp%10,digitCount);
                temp=temp/10;
            }

            if (sum == i)
            System.out.print(i+" ");
        }


    }
}
