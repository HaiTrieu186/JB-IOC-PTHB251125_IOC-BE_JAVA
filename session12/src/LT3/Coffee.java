package LT3;

public class Coffee extends Drink implements IPromotion  {
    public Coffee(int id, String name, double price) {
        super(id, name, price);
    }

    public Coffee() {
    }

    @Override
    public void prepare() {
        System.out.println("Pha bằng máy");
    }

    @Override
    public double applyDiscount(double percentage) {
        return super.getPrice()*(1- percentage);
    }
}
