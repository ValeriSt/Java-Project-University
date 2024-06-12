package printing;

import employees.Employee;
import publications.Publications;


import javax.print.PrintException;
import java.io.*;
import java.util.*;

public class PrintingPress {
    private final List<Employee> employees = new ArrayList<>();
    private final List<PrintingMachine> printingMachines = new ArrayList<>();

    private List<String> printedPublicationsLog = new ArrayList<>();
    private double discountThreshold;
    private double discountRate;

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
        //totalCosts += cost;
        printedPublicationsLog.add("Publication: " + publication + ", Copies: " + copies + ", Revenue: " + (cost - discount));
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<PrintingMachine> getPrintingMachines() {
        return printingMachines;
    }

    public List<String> getPrintedPublicationsLog() {
        return printedPublicationsLog;
    }

    public double getDiscountThreshold() {
        return discountThreshold;
    }

    public double getDiscountRate() {
        return discountRate;
    }
    public double getTotalCosts() {
        return totalCosts;
    }

    public void saveToFile(String filename) throws PrintException {
        try (PrintWriter outFile = new PrintWriter(new FileWriter(filename))) {
            outFile.printf("Total Revenue: $%.2f%n", totalRevenue);
            outFile.printf("Total Costs: $%.2f%n", totalCosts);
            outFile.println("Employees' salaries:");
            for (Employee emp : employees) {
                outFile.printf("%s - $%.2f%n", emp.getName(), emp.getSalary(totalRevenue));
            }
            outFile.println("Printed Publications:");
            for (String log : printedPublicationsLog) {
                outFile.println(log);
            }
            outFile.println("Machines:");
            for (PrintingMachine machine : printingMachines) {
                outFile.println(machine);
                machine.getPrintedPublications().forEach((title, count) ->
                        outFile.printf("  - %s: %d copies%n", title, count));
            }
        } catch (IOException e) {
            throw new PrintException("Error writing to file: " + e.getMessage());
        }
    }

   public double getTotalRevenueWithDiscount() {
       return totalRevenue - (totalRevenue * discountRate);
   }
}

