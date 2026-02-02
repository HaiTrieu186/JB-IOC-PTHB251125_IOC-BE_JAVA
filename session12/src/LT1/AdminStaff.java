package LT1;

import java.util.Scanner;

public class AdminStaff extends Staff {
    private double bonus;


    public AdminStaff() {}

    public AdminStaff(double baseSalary, String name, int id, double bonus) {
        super(baseSalary, name, id);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void inputData(Scanner sc){
        System.out.print("Mời bạn nhập lương cơ bản: ");
        super.setBaseSalary(Double.parseDouble(sc.nextLine()));

        System.out.print("Mời bạn nhập tên: ");
        super.setName(sc.nextLine());

        System.out.print("Mời bạn nhập id: ");
        super.setId(Integer.parseInt(sc.nextLine()));

        System.out.print("Mời bạn nhập bonus: ");
        this.bonus= Double.parseDouble(sc.nextLine());
    }

    @Override
    public double calculateSalary() {
        return super.getBaseSalary() + bonus;
    }
}
