package BT3;

public class Computer {
    public double calculatePrice(double basePrice){
        return basePrice;
    }
    public double calculatePrice(double basePrice, double tax){
        return basePrice+tax;
    }
    public double calculatePrice(double basePrice, double tax, double discount){
        return basePrice*(1 - discount/100) + tax;
    }
}
