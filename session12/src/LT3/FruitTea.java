package LT3;

public class FruitTea extends Drink implements IPromotion {
    public FruitTea() {
    }

    public FruitTea(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public void prepare() {
        System.out.println("Lắc đá với trái cây tươi");
    }

    @Override
    public double applyDiscount(double percentage) {
        return super.getPrice()*(1- percentage);
    }
}
