package LT1;

import java.util.Scanner;

public class Lecturer extends Staff{
    private int teachingHours;

    public Lecturer() {
    }

    public Lecturer(double baseSalary, String name, int id, int teachingHours) {
        super(baseSalary, name, id);
        this.teachingHours = teachingHours;
    }

    public int getTeachingHours() {
        return teachingHours;
    }

    public void setTeachingHours(int teachingHours) {
        this.teachingHours = teachingHours;
    }

    public void inputData(Scanner sc){
        System.out.print("Mời bạn nhập lương cơ bản: ");
        super.setBaseSalary(Double.parseDouble(sc.nextLine()));

        System.out.print("Mời bạn nhập tên: ");
        super.setName(sc.nextLine());

        System.out.print("Mời bạn nhập id: ");
        super.setId(Integer.parseInt(sc.nextLine()));

        System.out.print("Mời bạn nhập số giờ dạy: ");
        this.teachingHours=Integer.parseInt(sc.nextLine());
    }

    @Override
    public double calculateSalary() {
        return super.getBaseSalary() * teachingHours;
    }


}
