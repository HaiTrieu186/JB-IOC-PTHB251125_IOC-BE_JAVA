import java.util.Scanner;

public class BT5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Mời bạn nhập cân nặng: ");
        float weight = Float.parseFloat(sc.nextLine());
        System.out.print("Mời bạn nhập chiều cao: ");
        float height = Float.parseFloat(sc.nextLine());

        float bmi = weight/(height*height);

        System.out.printf("Chỉ số BMI là: %.2f\n",bmi);
    }
}
