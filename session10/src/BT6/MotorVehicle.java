package BT6;

public class MotorVehicle extends Vehicle{
    private String fuelType;

    public MotorVehicle() {
    }

    public MotorVehicle(String brand, int year, String fuelType) {
        super(brand, year);
        this.fuelType = fuelType;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("-------------------------");
    }
}
