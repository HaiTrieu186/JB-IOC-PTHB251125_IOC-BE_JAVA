package BT5;

import java.util.Scanner;

public class Student {
    private static int countStudent=0;

    private int id;
    private String name;
    private double gpa;
    private double SCORE_FACTOR;

    public Student(){
        SCORE_FACTOR=0.25;
        countStudent++;
    };

    public Student(int id, String name, double gpa) {
        this();
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public void input(Scanner sc){
        System.out.print("+ Mời bạn nhập tên: ");
        this.name = sc.nextLine();

        double score;
        do {
            System.out.print("+ Mời bạn nhập điểm: ");
            score = Double.parseDouble(sc.nextLine());

            if (score < 0.0 ||  score > 10.0) {
                System.out.println("Điểm không hợp lệ, vui lòng nhập lại !");
            } else {
                this.gpa = score;
                break;
            }
        }while(true);
    }

    public void print(){
        System.out.printf("| %-3s | %-20s | %-5s |\n",this.id,this.name,this.gpa);
    }

    public static int getTotalStudents(){
        return countStudent;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
