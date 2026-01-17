import java.util.Arrays;
import java.util.Scanner;

public class BT5 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean isActive=false;
        StringBuilder sb=new StringBuilder();

        do{
            System.out.println("""
                    +-------------------------MENU--------------------------+
                    1. Nhập chuỗi
                    2. Đếm số ký tự thường, hoa, số, đặc biệt 
                    3. Đảo ngược chuỗi
                    4. Kiểm tra Palindrome;
                    5. Chuẩn hóa chuỗi (Xóa khoảng trắng dư thừa, viết hoa chữ cái đầu
                    6. Thoát
                    +-------------------------MENU--------------------------+
                    """);
            System.out.print("Vui lòng chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            if (choice>=2 && choice<=5) {
                if (!isActive) {
                    System.out.println(RED + "Lỗi: Vui lòng nhập chuỗi trước (chọn 1)!" + RESET);
                    continue;
                }
            }

            switch (choice) {
                case 1:{
                    sb.setLength(0);
                    System.out.print("Mời bạn nhập chuỗi: ");
                    sb.append(sc.nextLine());
                    isActive=true;
                    break;
                }
                case 2:{
                    int countLowerChar=0, countUpperChar=0, countSpecialChar=0, countNumber=0;

                    for (int i=0; i<sb.length(); i++) {
                        if (sb.charAt(i)>= 65 && sb.charAt(i)<= 90)
                            countLowerChar++;
                        else

                        if (sb.charAt(i)>= 97 && sb.charAt(i)<= 122)
                           countUpperChar++;
                        else

                        if (sb.charAt(i)>= 48 && sb.charAt(i)<= 57)
                            countNumber++;
                        else
                            countSpecialChar++;
                    }

                    System.out.println("Số ký tự thường: "+countLowerChar);
                    System.out.println("Số ký tự hoa: "+countUpperChar);
                    System.out.println("Số ký tự số: "+countNumber);
                    System.out.println("Số ký tự đặc biệt: "+countSpecialChar);
                    break;
                }
                case 3:{
                    StringBuilder temp = new StringBuilder(sb.toString());
                    temp.reverse();

                    System.out.println("Chuỗi đảo ngược: "+ temp);
                    break;
                }
                case 4:{
                    String original= sb.toString();
                    String reversed = new StringBuilder(original).reverse().toString();

                    if (original.equals(reversed)) {
                        System.out.println("Đây là chuỗi Palindrome");
                    } else {
                        System.out.println("Không phải chuỗi Palindrome");
                    }

                    break;
                }
                case 5:{
                    String temp=sb.toString().toLowerCase().trim();
                    String[] words= temp.split("\\s+");

//                    System.out.println(Arrays.toString(word));

                    //Viết hoa
                    for (int i=0;i<words.length;i++) {
                        String capital = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);;
                        words[i]=capital;
                    }

                    sb.setLength(0);

                    for(int i=0;i<words.length;i++) {
                        sb.append(words[i]);
                        if (i!=words.length-1) {
                            sb.append(" ");
                        }
                    }

                    System.out.println("Chuỗi sau chuẩn hóa: "+ sb);


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

}
