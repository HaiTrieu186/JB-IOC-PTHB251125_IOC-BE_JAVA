package BT3;

public abstract class Employee {

    public static double baseSalary=1000000;
    public static double baseSalaryByHour=20000;
    private int id;
    private String name;

    public Employee() {
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double calculateSalary();
    public void showInfo(){
        System.out.println("Employee ID: " + id);
        System.out.println("Employee Name: " + name);
    };
}
