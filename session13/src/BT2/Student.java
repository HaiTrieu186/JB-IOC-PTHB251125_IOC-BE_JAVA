package BT2;

import java.util.Scanner;

public class Student {
    private int id;
    private String name;

    public Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void inputData(Scanner sc){
        System.out.println("Nhập ID sinh viên: ");
        this.id = Integer.parseInt(sc.nextLine());
        System.out.println("Mời bạn nhập tên: ");
        this.name = sc.nextLine();
    }

    public void displayData() {
        System.out.println("Student id : " + id);
        System.out.println("Student name : " + name);
    }
}
