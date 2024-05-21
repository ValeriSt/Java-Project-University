package employees;

public class Manager implements Employee {
    private double baseSalary;
    private double revenue;
    private double bonusThreshold;

    public Manager(double baseSalary, double revenue, double bonusThreshold) {
        this.baseSalary = baseSalary;
        this.revenue = revenue;
        this.bonusThreshold = bonusThreshold;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setBonusThreshold(double bonusThreshold) {
        this.bonusThreshold = bonusThreshold;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getBonusThreshold() {
        return bonusThreshold;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + calculateBonus();
    }

    private double calculateBonus() {
        return revenue > bonusThreshold ? baseSalary * 0.1 : 0;
    }
}