package BT3;

public class Cat extends Animal {
    private String breed;

    public Cat() {};

    public Cat(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    public void meow() {
        System.out.println("Meow");
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
