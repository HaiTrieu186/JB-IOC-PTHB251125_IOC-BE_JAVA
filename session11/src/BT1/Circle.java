package BT1;

public class Circle extends Shape implements Drawable {
    private double radius;

    public Circle() {
    }

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI*radius*radius;
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public void draw() {
        System.out.printf("Hình tròn - %s: \n\tBán kính: %.2f\n",super.getName(),this.radius);
    }
}
