package LT2;

import java.util.Scanner;

public class Computer extends Assest {

    private static double KHAU_HAO=0.2;
    private String ram;
    private String cpu;

    public Computer(String assestCode, String name, double purchasePrice, String ram, String cpu) {
        super(assestCode, name, purchasePrice);
        this.ram = ram;
        this.cpu = cpu;
    }

    public Computer() {
    }

    public static double getKhauHao() {
        return KHAU_HAO;
    }


    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    @Override
    public void inputData(Scanner sc) {
        super.inputData(sc);

        System.out.print("Mời bạn nhập ram: ");
        this.ram = sc.nextLine();

        System.out.print("Mời bạn nhập cpu: ");
        this.cpu = sc.nextLine();
    }


    @Override
    public void getMarketValue() {;
        System.out.println("Giá trị trước khấu hao: "+ super.getPurchasePrice());
        System.out.println("Giá trị sau khấu hao: "+ (super.getPurchasePrice())*(1-KHAU_HAO));
    }
}
