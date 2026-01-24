package LT01;

public class Main {
    public static void main(String[] args) {

        // Cách 1 dùng constructor
        Rectangle rec1=new Rectangle(5.2,4.5);

        // Cách 2: dùng setter
        Rectangle rec2=new Rectangle();
        rec2.setHeight(6);
        rec2.setWidth(7);

        rec1.printInfo();
        rec2.printInfo();

    }
}
