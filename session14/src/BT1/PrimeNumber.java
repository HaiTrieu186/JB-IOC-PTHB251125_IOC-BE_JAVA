package BT1;

import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a;
        while(true){
            try {
                System.out.print("Mời bạn nhập số nguyên: ");
                a= Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng chỉ nhập số nguyên !");
            }
        }

        try {
            System.out.println("Kết quả: "+ (checkPrimeNumber(a)? "Là số nguyên số" : "Không phải số nguyên tố"));
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }

    }


    public static boolean checkPrimeNumber(int a){
        if (a<=0)
            throw new IllegalArgumentException("Không hợp lệ để kiểm tra số nguyên tố, phải là số nguyên dương (a>0)");

        if (a==1)
            return false;

       int limit = (int)Math.sqrt(a);
       for (int i=2; i<=limit; i++) {
           if (a%i==0) return false;
       }

        return true;
    }

}
