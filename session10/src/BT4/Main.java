package BT4;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();

        car.acclerate();
        car.printStatus();
        car.acclerate(20);
        car.printStatus();
        car.acclerate(20,1);
        car.printStatus();
    }
}
