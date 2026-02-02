package LT1;

public abstract class Staff {
    private int id;
    private String name;
    private double baseSalary;

    public Staff() {
    }

    public Staff(double baseSalary, String name, int id) {
        this.baseSalary = baseSalary;
        this.name = name;
        this.id = id;
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

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract double calculateSalary();
}
