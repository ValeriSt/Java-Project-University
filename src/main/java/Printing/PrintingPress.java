package printing;

import employees.Employee;
import publications.Publications;


import javax.print.PrintException;
import java.io.*;
import java.util.*;

public class PrintingPress {
    private final List<Employee> employees = new ArrayList<>();
    private final List<PrintingMachine> printingMachines = new ArrayList<>();
    private final double discountThreshold;
    private final double discountRate;

    private double totalRevenue = 0.0;
    private double totalCosts = 0.0;

    public PrintingPress(double discountThreshold, double discountRate) {
        this.discountThreshold = discountThreshold;
        this.discountRate = discountRate;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addPrintingMachine(PrintingMachine machine) {
        printingMachines.add(machine);
    }

    public void addPublication(Publications publication, int copies) {

        double cost = publication.getCost() * copies;
        totalCosts += cost;
        double discount = 0;
        if (copies > discountThreshold) {
            discount = cost * discountRate;
        }
        totalRevenue += (cost - discount);
        totalCosts += cost;

    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalCosts() {
        return totalCosts;
    }

    public void saveToFile(String filename) throws PrintException {
        try (PrintWriter outFile = new PrintWriter(new FileWriter(filename))) {
            outFile.printf("Total Revenue: $%.2f%n", totalRevenue);
            outFile.printf("Total Costs: $%.2f%n", totalCosts);
            outFile.println("Employees:");
            for (Employee emp : employees) {
                outFile.printf("%s - $%.2f%n", emp.getName(), emp.getSalary(totalRevenue));
            }
        } catch (IOException e) {
            throw new PrintException("Error writing to file: " + e.getMessage());
        }
    }

}

