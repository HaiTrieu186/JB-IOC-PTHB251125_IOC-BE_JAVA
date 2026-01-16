import java.util.Random;
import java.util.Scanner;

public class BT4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=-1;
        StringBuilder s1=new StringBuilder();
        StringBuilder s2=new StringBuilder();
        Random rand=new Random();

        int i=1;
        do {
            System.out.print("Mời bạn nhập n [1-1000]: ");
            n= Integer.parseInt(sc.nextLine());

            if (n<1 || n>1000){
                System.out.println("Vui lòng nhập n trong khoảng 1-1000");
            }
        }while (n<1 || n>1000);




        ///  CÁCH 1: dùng ASCII
        int randomNumber;
        while(i<=n){
            // Random từ 48-122
            randomNumber=48+rand.nextInt(75);

            if (isRightASCII(randomNumber)){
                s1.append((char) randomNumber);
                i++;
            }
        }



        // CÁCH 2: dùng chuỗi có sẵn
        String validChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int j=0;j<n;j++){
            char c= validChars.charAt(rand.nextInt(validChars.length()));
            s2.append(c);
        }


        System.out.println("Chuỗi sau sinh ngẫu nhiên (cách dùng ASCII)       : "+s1);
        System.out.println("Chuỗi sau sinh ngẫu nhiên (cách dùng chuỗi có sẵn): "+s2);
    }


    public static boolean isRightASCII(int number) {
        return (number >= 48 && number <= 57) ||
                (number >= 65 && number <= 90) ||
                (number >= 97 && number <= 122);
    };
}
