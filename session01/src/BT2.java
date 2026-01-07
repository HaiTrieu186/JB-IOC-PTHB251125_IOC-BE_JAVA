import java.util.Scanner;

public class BT2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int firstNumber = 0;
        int secondNumber = 0;

        System.out.print("Mời bạn nhập số 1: ");
        firstNumber = Integer.parseInt(sc.nextLine());
        System.out.print("Mời bạn nhập số 2: ");
        secondNumber = Integer.parseInt(sc.nextLine());

        System.out.println("Hai số vừa nhập là : " + firstNumber + ", " + secondNumber);
        System.out.println("Tổng: " + (firstNumber + secondNumber));
        System.out.println("Hiệu: " + (firstNumber - secondNumber));
        System.out.println("Tích: " + (firstNumber * secondNumber));
        System.out.println("Thương: " + (firstNumber / secondNumber));
        System.out.println("Phần dư: " + (firstNumber % secondNumber));




    }
}
