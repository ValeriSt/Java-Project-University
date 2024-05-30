package employees;

public class MachineOperator extends Employee {
    public MachineOperator(String name, double baseSalary) {
        super(name, baseSalary);
    }
    @Override
    public double getSalary(double revenue) {
        return baseSalary;
    }
}