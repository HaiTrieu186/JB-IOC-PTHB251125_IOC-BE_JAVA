import java.util.Scanner;

public class BT4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a,b,c;
        do{
            System.out.print("Mời bạn nhập cạnh a: ");
            a = Integer.parseInt(sc.nextLine());

            System.out.print("Mời bạn nhập cạnh b: ");
            b = Integer.parseInt(sc.nextLine());

            System.out.print("Mời bạn nhập cạnh c: ");
            c = Integer.parseInt(sc.nextLine());

            if (a<=0 || b<=0 || c<=0)
                System.out.println("Vui lòng nhập độ lớn cạnh lớn hơn 0 !");
        }while (a<=0 || b<=0 || c<=0);

        boolean check = (a+b)>c && (a+c)>b && (c+b)>a;

        if (!check){
            System.out.println("Ba cạnh không tạo thành tam giác");
        } else{
            if (a==b && a==c && c==b)
                System.out.println("Tam giác bạn nhập là TAM GIÁC ĐỀU");
            else if (a==b || a==c || c==b)
                System.out.println("Tam giác bạn vừa nhập là TAM GIÁC CÂN");
            else if ((a*a + b*b) == c*c || (c*c + b*b) == a*a || (a*a + c*c) == b*b)
                System.out.println("Tam giác bạn vừa nhập là TAM GIÁC VUÔNG");
            else System.out.println("Tam giác bạn vừa nhập là 1 TAM GIÁC THƯỜNG");


        }

    }
}
