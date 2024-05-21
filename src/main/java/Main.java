import java.util.ArrayList;
import java.util.List;

import employees.Employee;
import employees.MachineOperator;
import enums.PageSize;
import enums.PaperType;
import printing.PrintingPress;
import employees.Manager;
import publications.Publications;

public class Main {
public static void main(String[] args) {
    // Create some employees
    MachineOperator operator = new MachineOperator(2000.0);
    Manager manager = new Manager(3000.0, 50000.0, 40000.0);

    // Create a list of employees and add the employees to it
    List<Employee> employees = new ArrayList<>();
    employees.add(operator);
    employees.add(manager);

    // Create some publications
    Publications book = new Publications("Book", 100, PageSize.A4, PaperType.NORMAL);
    Publications poster = new Publications("Poster", 50, PageSize.A1, PaperType.GLOSSY);

    // Create a list of publications and add the publications to it
    List<Publications> publications = new ArrayList<>();
    publications.add(book);
    publications.add(poster);

    // Create a printing press with the employees and publications
    PrintingPress printingPress = new PrintingPress();
    printingPress.setEmployees(employees);
    printingPress.setPublications(publications);

    // Calculate and print the total salary costs
    double totalSalaryCosts = printingPress.calculateTotalSalaryCosts();
    System.out.println("Total salary costs: " + totalSalaryCosts);

    // Calculate and print the total paper costs
    double totalPaperCosts = printingPress.calculateTotalPaperCosts();
    System.out.println("Total paper costs: " + totalPaperCosts);
}
}