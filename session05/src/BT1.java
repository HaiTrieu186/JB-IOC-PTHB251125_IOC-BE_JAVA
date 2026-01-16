import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BT1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String email=null;
        do {
            System.out.print("Mời bạn nhập email: ");
            email = sc.nextLine();

            if (email.isBlank()){
                System.out.println("Vui lòng không để trống !");
            } else {
                break;
            }
        } while (true);


        email = email.trim();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        boolean match = matcher.matches();

        System.out.printf("Email vừa nhâp: %s", match?"Hợp lệ": "Không hợp lệ");

    }
}
