package BT6;

public class Car extends MotorVehicle{
    public Car() {
    }

    public Car(String brand, int year, String fuelType) {
        super(brand, year, fuelType);
    }

    @Override
    public void startEngine() {
        System.out.printf("%s (Car) engine starts: Vroom Vroom!\n", super.getBrand());
    }

    public void openTrunk(){
        System.out.printf("%s trunk is opening...\n",super.getBrand());
    }
}
