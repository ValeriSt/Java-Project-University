package employees;

public class Manager extends Employee {
    private final double bonusThreshold;

    private final double bonusRate;

    public Manager(String name, double baseSalary, double bonusThreshold, double bonusRate) {
        super(name, baseSalary);
        this.bonusThreshold = bonusThreshold;
        this.bonusRate = bonusRate;
    }


    @Override
    public double getSalary(double revenue) {
        if (revenue > bonusThreshold) {
            return baseSalary + (1.0 + bonusRate);
        } else {
            return baseSalary;
        }
    }
}