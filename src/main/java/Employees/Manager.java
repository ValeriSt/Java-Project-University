package employees;

public class Manager extends BaseEmployee {
    private double revenue;
    private double bonusThreshold;

    public Manager(double baseSalary, double revenue, double bonusThreshold) {
        super(baseSalary);
        this.revenue = revenue;
        this.bonusThreshold = bonusThreshold;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + calculateBonus();
    }

    private double calculateBonus() {
        return revenue > bonusThreshold ? baseSalary * 0.1 : 0;
    }
}