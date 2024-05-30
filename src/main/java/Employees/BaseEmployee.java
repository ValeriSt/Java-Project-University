package employees;

import interfaces.Employee;

public abstract class BaseEmployee implements Employee {
    protected double baseSalary;

    public BaseEmployee(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
}