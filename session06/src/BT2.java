import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BT2 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PHONE_REGEX = "^(0|\\+84|84)[35789]\\d{8}$";


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice;
        boolean flag=false;
        StringBuilder fullName= new StringBuilder();
        String email = "", phone = "", password = "";;

        do{
            System.out.println("""
                    \n+-------------------------QUẢN LÝ ĐIỂM SV--------------------------+
                    1. Nhập thông tin người dùng
                    2. Chuẩn hóa họ teên
                    3. Kiểm tra email hợp lệ
                    4. Kiểm tra số điện thoại hợp lệ
                    5. Kiểm tra mật khẩu hợp lệ
                    6. Thoát
                    +-------------------------MENU--------------------------+""");
            System.out.print("Vui lòng chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            if (choice>=2 && choice<=5) {
                if (!flag){
                    System.out.println(RED + "Lỗi: Vui lòng nhập thông tin nguười dùng trước !" + RESET);
                    continue;
                }
            }

            switch (choice) {
                case 1:{
                    flag=true;
                    fullName.setLength(0);
                    System.out.print("Mời bạn nhập họ tên: ");
                    fullName.append(sc.nextLine());

                    System.out.print("Mời bạn nhập email: ");
                    email = sc.nextLine();

                    System.out.print("Mời bạn nhập số điện thoại ");
                    phone = sc.nextLine();

                    System.out.print("Mời bạn nhập mật khẩu: ");
                    password = sc.nextLine();

                    break;
                }
                case 2:{
                    String temp= fullName.toString().trim().toLowerCase();
                    String[] words = temp.split("\\s+");

                    for (int i = 0; i < words.length; i++) {
                        words[i] = words[i].substring(0,1).toUpperCase() +  words[i].substring(1);
                    }

                    fullName.setLength(0);

                    for (int i = 0; i < words.length; i++) {
                        fullName.append(words[i]);

                        if (i != words.length - 1) {
                            fullName.append(" ");
                        }
                    }

                    System.out.println("Họ và tên sau chuẩn hóa: "+ fullName);


                    break;
                }
                case 3:{
                    Pattern pattern=Pattern.compile(EMAIL_REGEX);
                    Matcher matcher= pattern.matcher(email);
                    if (matcher.matches())
                        System.out.println("Email hợp lệ !");
                    else
                        System.out.println("Email không hợp lệ !");
                    break;
                }
                case 4:{
                    Pattern pattern=Pattern.compile(PHONE_REGEX);
                    Matcher matcher= pattern.matcher(phone);

                    if (matcher.matches())
                        System.out.println("Số điện thoại hợp lệ !");
                    else
                        System.out.println("Số điện thoại không hợp lệ !");
                    break;
                }
                case 5:{
                    if (isValidPassword(password))
                        System.out.println("Mật khẩu hợp lệ !");
                    else
                        System.out.println("Mật khẩu không hợp lệ !");

                    break;
                }

                case 6:{
                    System.out.println("Kết thúc chương trình !");
                    sc.close();
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println(RED + "Lỗi: Vui lòng nhập số lượng hợp lệ (1-6)!" + RESET);
                    break;
                }
            }

        }while (true);



    }
    public static boolean isValidPassword(String password) {
        // Kiểm tra độ dài tối thiểu 8 ký tự
        if (password == null || password.length() < 8) {
            return false;
        }

        // Kiểm tra có chữ hoa hay không
        boolean hasUppercase = password.matches(".*[A-Z].*");

        // Kiểm tra có chữ thường hay không
        boolean hasLowercase = password.matches(".*[a-z].*");

        // Kiểm tra có số hay không
        boolean hasDigit = password.matches(".*[0-9].*");

        // Kiểm tra có ký tự đặc biệt hay không
        boolean hasSpecial = password.matches(".*[^A-Za-z0-9].*");

        return hasUppercase && hasLowercase && hasDigit && hasSpecial;
    }

}
