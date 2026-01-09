import java.util.Scanner;

public class BT5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        String kq="";

        do{
            System.out.println("\n-----Biến số thành chữ-----");
            System.out.print("Mời bạn nhập số (100-999): ");
            n = Integer.parseInt(sc.nextLine());

            if (n<100 || n>999)
                System.out.println("Vui lòng chỉ nhập các số từ 100 - 999 !");
        } while (n<100 || n>999);

        int temp= n;
        int hundreds, tens, units;

        // Lấy hàng đơn vị
        units = temp%10;
        temp = temp/10;

        // Lấy hàng chục
        tens = temp%10;
        temp = temp/10;

        // Lấy hàng đơn vị
        hundreds = temp%10;


        if (tens ==0 && units ==0){
            kq= numberToString(hundreds) + " trăm";
        } else {

            if (tens ==0 && units !=0){
                kq = numberToString(hundreds) + " trăm linh " +  numberToString(units);  ;
            } else
            if (tens !=0 && units ==0) {
                if (tens == 1)
                    kq = numberToString(hundreds) + " trăm mười";
                else
                    kq = numberToString(hundreds) + " trăm " +  numberToString(tens) + " mươi";
            } else {
                if (tens == 1)
                    kq = numberToString(hundreds) + " trăm mười " + numberToString(units);
                else
                kq = numberToString(hundreds) + " trăm " +  numberToString(tens) + " mươi " + numberToString(units);
            }

        }

        System.out.println("Kết quả: "+kq+" !");




    }

    public static String numberToString(int number){
        switch (number){
            case 1:
                return "một";
            case 2:
                return "hai";
            case 3:
                return "ba";
            case 4:
                return "bốn";
            case 5:
                return "năm";
            case 6:
                return "sáu";
            case 7:
                return "bảy";
            case 8:
                return "tám";
            case 9:
                return "chín";
            case 0:
                return "không";
        }

        return null;
    };
}
