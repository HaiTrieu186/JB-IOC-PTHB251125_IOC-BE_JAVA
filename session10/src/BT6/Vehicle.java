package BT6;

public class Vehicle {
    private String brand;
    private int year;

    public Vehicle() {
    }

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void showInfo(){
        System.out.printf("Brand: %s, Year: %d\n", this.brand, this.year);
    }

    public void startEngine(){

    }

    public void move(){
        System.out.printf("%s is moving\n", this.brand);
    }

    public void move(int speed){
        System.out.printf("%s is moving at %d km/h.\n",this.brand, speed);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
