package BT1;

public class Dog extends Animal {

    public Dog() {
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {


        System.out.println("Some animall sound");
    }
}
