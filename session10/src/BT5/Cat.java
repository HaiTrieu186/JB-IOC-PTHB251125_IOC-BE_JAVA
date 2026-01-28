package BT5;

public class Cat extends Mammal {
    public Cat() {
    }

    public Cat(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s says: Meow Meow! \n", super.getName());
    }

    public void climbTree(){
        System.out.printf("%s is climbing a tree.\n",  super.getName());
    }
}
