package employees;

public class MachineOperator extends BaseEmployee {
    public MachineOperator(double baseSalary) {
        super(baseSalary);
    }

    @Override
    public double calculateSalary() {
        return baseSalary;
    }
}