import java.util.Scanner;

public class BT4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Mời bạn nhập chiều rộng: ");
        float width = Float.parseFloat(sc.nextLine());
        System.out.print("Mời bạn nhập chiều cao: ");
        float height = Float.parseFloat(sc.nextLine());

        float area = (width*height);
        float perimeter = 2*(width+height);

        System.out.printf("Diện tích hình chữ nhật là: %.2f\n",area);
        System.out.printf("Chu vi hình chữ nhật là: %.2f",perimeter);


    }
}
