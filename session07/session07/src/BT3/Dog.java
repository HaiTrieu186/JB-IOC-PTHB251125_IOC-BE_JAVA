package BT3;

public class Dog extends Animal {
    private String color;


    public Dog() {};

    public Dog(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    public void bark() {
        System.out.println("Bark");;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
