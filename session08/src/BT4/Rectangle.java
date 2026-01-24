package BT4;

public class Rectangle {

    private double widht;
    private double height;

    public Rectangle(){}

    public Rectangle(double widht, double height) {
        this.widht = widht;
        this.height = height;
    }

    public double getWidht() {
        return widht;
    }

    public void setWidht(double widht) {
        this.widht = widht;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return this.widht * this.height;
    }
    public double getPerimeter() {
        return 2*(this.widht + this.height);
    }

    @Override
    public String toString() {
        return "Rectangle("
        + "widht=" + widht + ", height=" + height + ", area=" + getArea() + ", perimeter=" + getPerimeter() + ")";
    }

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(3,4);
        Rectangle r2 = new Rectangle(5,2);
        Rectangle r3 = new Rectangle(4.5, 3.5);

        System.out.println("Hình 1: "+r1);
        System.out.println("Hình 2: "+r2);
        System.out.println("Hình 3: "+r3);

        double maxArea = r1.getArea();
        StringBuilder kq=new StringBuilder();

        if (r2.getArea()>maxArea){
            maxArea=r2.getArea();
        }

        if (r3.getArea()>maxArea){
            maxArea=r3.getArea();
        }

        if (r1.getArea() == maxArea){
            kq.append("Rectangle 1: width="+r1.getWidht()+" height="+r1.getHeight()+"\n");

        }

        if (r2.getArea() == maxArea){
            kq.append("Rectangle 2: width="+r2.getWidht()+" height="+r2.getHeight()+"\n");
        }

        if (r3.getArea() == maxArea){
            kq.append("Rectangle 3: width="+r3.getWidht()+" height="+r3.getHeight()+"\n");
        }

        System.out.println("Largest area = "+maxArea);
        if (!kq.isEmpty()){
            System.out.println(kq);
        }



    }


}
