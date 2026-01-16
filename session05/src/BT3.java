import java.util.Scanner;

public class BT3 {
    public static final String checkLenght= ".{8,}";
    public static final String atLeast1Upper= ".*[A-Z]+.*";
    public static final String atLeast1Lower= ".*[a-z]+.*";
    public static final String atLeast1Number= ".*[0-9]+.*";
    public static final String atLeast1SpecailCharacter= ".*[^0-9a-zA-Z]+.*";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        boolean check=true;

        if (!s.matches(checkLenght)) {
            check=false;
            System.out.println("Mật khẩu không hợp lệ: phải ít nhất 8 kí tự");
        }

        if (!s.matches(atLeast1Upper)) {
            check=false;
            System.out.println("Mất khẩu không hợp lệ: phải ít nhất 1 chữ in hoa");
        }

        if (!s.matches(atLeast1Lower)) {
            check=false;
            System.out.println("Mất khẩu không hợp lệ: phải ít nhất 1 chữ in thường");
        }

        if (!s.matches(atLeast1Number)) {
            check=false;
            System.out.println("Mất khẩu không hợp lệ: phải ít nhất 1 chữ số");
        }

        if (!s.matches(atLeast1SpecailCharacter)) {
            check=false;
            System.out.println("Mật khẩu không hợp lệ: phải ít nhất 1 ký tự đặc biệt");
        }

        if (check)
            System.out.println("Mật khẩu hợp lệ");

    }
}
