package BT3;

public class PartTimeEmployee  extends Employee{
    private int workingHour;

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(int id, String name, int workingHour) {
        super(id, name);
        this.workingHour = workingHour;
    }

    public int getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(int workingHour) {
        this.workingHour = workingHour;
    }

    @Override
    public double calculateSalary() {
        return Employee.baseSalaryByHour*workingHour;
    }
}
