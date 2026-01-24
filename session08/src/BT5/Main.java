package BT5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] students = new Student[100];
        int n=0, option=-1;
        do {
            System.out.println("""
                ==== MENU SINH VIÊN ====
                1. Nhập danh sách sinh viên
                2. In danh sách sinh viên
                3. Tìm sinh viên GPA cao nhất
                4. In tổng số sinh viên đã tạo
                0. Thoát
                =========================""");

            System.out.print("Mời bạn nhập lựa chọn: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:{
                    int number=0,
                        maxID=findMaxId(students, n);

                    do{
                        System.out.print("Mời bạn nhập số lượng cần thêm: ");
                        number = Integer.parseInt(sc.nextLine());

                        if (number < 0) {
                            System.out.println("Vui lòng nhập số lượng hợp lệ !");
                            continue;
                        }

                        break;

                    }while (true);

                    System.out.println("\n----- NHẬP THÔNG TIN SINH VIÊN -----");

                    for(int i=1;i<=number;i++){
                        Student temp = new  Student();
                        System.out.println("Mời bạn nhập sinh viên thứ "+i+": ");
                        temp.input(sc);
                        temp.setId(++maxID);
                        students[n++]=temp;
                    }

                    break;
                }
                case 2:{
                    System.out.println("\n==== Danh sách sinh viên ====");
                    printList(students, n);
                    break;
                }
                case 3:{
                    System.out.println("\n==== GPA CAO NHẤT ====");

                    if (n==0){
                        System.out.println("Chưa có sinh viên để hiển thị !");
                        break;
                    }

                    double max = students[0].getGpa();
                    Student[] matchesStudents = new Student[students.length];
                    int count =0;

                    for (int i=1;i<n;i++){
                        if(students[i].getGpa()>max){
                            max = students[i].getGpa();
                        }

                        if (students[i].getGpa()==max){
                            matchesStudents[count++]=students[i];
                        }
                    }

                    System.out.println("Điểm gpa cao nhất: "+max);
                    System.out.println(" ==== Danh sách sinh viên cao điểm nhất ====");
                    printList(matchesStudents, count);

                    break;
                }
                case 4:{
                    System.out.println("Tổng số sinh viên đã tạo: "+ Student.getTotalStudents()+"\n");
                    break;
                }
                case 0:{
                    System.out.println("Kết thúc chương trình !");
                    sc.close();
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("Lựa chọn không hợp lệ (phải chọn 0-4) !");
                }
            }


        }while (true);

    }

    public static int findMaxId(Student[] students, int n){

        if (students == null ||n == 0) {
            return 0;
        }

        int maxID= students[0].getId();

        for(int i=1;i<n;i++){
            if(students[i].getId()>maxID){
                maxID=students[i].getId();
            }
        }

        return maxID;
    }

    public static void printList(Student[] students, int n){
        System.out.printf("+%s+%s+%s+\n",
                "-".repeat(5),
                "-".repeat(22),
                "-".repeat(7));
        System.out.printf("| %-3s | %-20s | %-5s |\n","ID","NAME","GPA");
        System.out.printf("+%s+%s+%s+\n",
                "-".repeat(5),
                "-".repeat(22),
                "-".repeat(7));


        if (n==0){
            System.out.printf("|   %-33s|\n", "CHƯA CÓ SINH VIÊN NÀO !");
        }

        for (int i=0;i<n;i++){
            students[i].print();
        }
        System.out.printf("+%s+%s+%s+\n\n",
                "-".repeat(5),
                "-".repeat(22),
                "-".repeat(7));

    }
}
