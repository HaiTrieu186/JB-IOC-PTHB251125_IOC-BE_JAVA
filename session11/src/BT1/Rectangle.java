package BT1;

public class Rectangle extends Shape  implements Drawable{
    private double width;
    private double height;

    public Rectangle() {
    }

    public Rectangle(String name, double width, double height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double  getArea() {
        return this.width*this.height;
    }

    @Override
    public double  getPerimeter() {
        return (this.width+this.height)*2;
    }


    @Override
    public void draw() {
        System.out.printf("Hình chữ nhật - %s: \n\t Chiều dài: %.2f, \n\t Chiều rộng: %.2f \n",super.getName(),this.width,this.height);
    }
}
