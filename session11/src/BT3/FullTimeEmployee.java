package BT3;

public class FullTimeEmployee extends Employee implements  BonusEligible{
    @Override
    public double calculateSalary() {
        return Employee.baseSalary+calculateBonus();
    }

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(int id, String name) {
        super(id, name);
    }

    @Override
    public double calculateBonus() {
        return 100;
    }
}
