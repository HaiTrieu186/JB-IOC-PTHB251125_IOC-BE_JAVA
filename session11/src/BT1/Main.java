package BT1;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle("ABC",5.0);
        shapes[1] = new Circle("DEF",5.0);
        shapes[2]= new Rectangle("GHI",12.5, 7.6);

        for (Shape shape : shapes) {
            System.out.println(shape.getName());
            System.out.println(shape.getArea());
            System.out.println(shape.getPerimeter());

            if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                circle.draw();
            } else if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                rectangle.draw();
            }

        }
    }
}
