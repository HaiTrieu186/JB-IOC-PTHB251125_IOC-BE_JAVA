package LT03;

public class Student {
    private int id;
    private String fullName;
    private int age;
    private double gpa;

    private static int count=0;
    private final double MIN_GPA=0.0;
    private final double MAX_GPA=4.0;

    public Student() {
        this.id=++count;
    }

    public Student(String fullName, int age, double gpa) {
        this();
        this.fullName = fullName;
        this.age = age;
        this.gpa = gpa;
    }

    public static int getCount() {
        return count;
    }

    public void printInfo() {
        System.out.println("+ ID: " + id);
        System.out.println("+ Full Name: " + fullName);
        System.out.println("+ Age: " + age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
