package BT1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private String name;
    private String email;
    private String phone;

    public Person() {

    }

    public Person(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void inputData(Scanner sc){
        String s="";

        while(true){
            System.out.print("Nhập tên người dùng: ");
            s=sc.nextLine();

            if (s.isBlank())
                System.out.println("Vui lòng không để trống !");
            else
                break;
        }
        this.name=s;

        while(true){
            System.out.print("Nhập email người dùng: ");
            s=sc.nextLine();

            if (!isValidEmail(s)){
                System.out.println("Email không hơp lệ, vui lòng nhập lại");
                continue;
            }

            if (s.isBlank())
                System.out.println("Vui lòng không để trống !");
            else
                break;
        }
        this.email=s;

        while(true){
            System.out.print("Nhập số điện thoại người dùng: ");
            s=sc.nextLine();

            if (s.isBlank())
                System.out.println("Vui lòng không để trống !");
            else
                break;
        }
        this.phone=s;

    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void outputData() {
        System.out.printf("Tên: %s, Email: %s, Số điện thoại: %s",this.name,this.email,this.phone);
    }

}