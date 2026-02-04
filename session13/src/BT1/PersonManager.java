package BT1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PersonManager {
    private static List<Person> persons = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("""
                    ************* MENU QUẢN LÝ NGƯỜI DÙNG *************
                    1.Thêm người dùng
                    2. Xóa người dùng
                    3. Hiển thị danh sách người dùng
                    4. Thoát
                    """);
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:{
                    Person person = new Person();
                    person.inputData(sc);

                    if (findPersonByEmail(person.getEmail()) == null) {
                        persons.add(person);
                    } else {
                        System.out.println("Email đã tồn tại, vui lòng nhập lại !");
                        String s;
                        while(true){
                            System.out.print("Nhập email người dùng: ");
                            s=sc.nextLine();

                            if (s.isBlank())
                                System.out.println("Vui lòng không để trống !");
                            else
                                break;
                        }
                        person.setEmail(s);
                        persons.add(person);
                    }


                    break;}
                case 2:{
                    String mail;
                    while(true){
                        System.out.print("Nhập email cần tìm: ");
                        mail=sc.nextLine();

                        if (mail.isBlank())
                            System.out.println("Vui lòng không để trống !");
                        else
                            break;
                    }

                    Person person = findPersonByEmail(mail);

                    if (person != null) {
                       persons.remove(person);
                        System.out.println("Đã xóa thành công");
                    }else
                        System.out.println("Không có người dùng với email: "+ mail);



                    break;}
                case 3:{
                    if (persons.isEmpty()) {
                        System.out.println("Chưa có người dùng nào !");
                        break;
                    }

                    System.out.println("------- DANH SÁCH NGƯỜI DÙNG ------");

                    for (Person person : persons) {
                        person.outputData();
                    }
                    break;}
                case 4:{
                    System.out.println("Thoát chương trình");
                    sc.close();
                    System.exit(0);
                    break;}
                default:{
                    System.out.println("Lựa chọn không hợp lệ !");
                }
            }

        }

    }

    public static Person findPersonByEmail(String email){
        for(Person person:persons){
            if(person.getEmail().equals(email)){
                return person;
            }
        }
        return null;
    }
}
