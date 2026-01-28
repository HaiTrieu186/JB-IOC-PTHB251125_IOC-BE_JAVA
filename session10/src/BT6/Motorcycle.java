package BT6;

public class Motorcycle extends MotorVehicle{

    public Motorcycle() {
    }

    public Motorcycle(String brand, int year, String fuelType) {
        super(brand, year, fuelType);
    }

    @Override
    public void startEngine() {
        System.out.printf("%s (Motorcycle) engine starts: Brum Brum!\n", super.getBrand());
    }

    public void doWheelie(){
        System.out.printf("%s is doing a wheelie...\n",super.getBrand());
    }
}
