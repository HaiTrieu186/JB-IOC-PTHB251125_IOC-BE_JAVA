package BT3;

public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();


        System.out.println("[Using basePrice only]");
        System.out.println("Final price: "+ computer.calculatePrice(1000));
        System.out.println("[Using basePrice only + tax]");
        System.out.println("Final price: "+ computer.calculatePrice(1000,100));
        System.out.println("[Using basePrice only + tax - discount]");
        System.out.println("Final price: "+ computer.calculatePrice(1000,100, 5));
    }
}
