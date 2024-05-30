import employees.MachineOperator;
import employees.Manager;
import enums.PageSize;
import enums.PaperType;
import enums.PrintingMode;
import printing.PrintingPress;
import printing.PrintingMachine;
import publications.Publications;

import javax.print.PrintException;

public class Main {
    public static void main(String[] args) {
        // Create some employees
        try {
            PrintingPress press = new PrintingPress(1000, 0.1);
            press.addEmployee(new MachineOperator("John Doe", 1000));
            press.addEmployee(new Manager("Jane Smith", 1500, 5000, 0.2));

            PrintingMachine machine1 = new PrintingMachine("M1", 10000, true);
            PrintingMachine machine2 = new PrintingMachine("M2", 500, false);
            press.addPrintingMachine(machine1);
            press.addPrintingMachine(machine2);

            Publications book = new Publications("C++ Programming", 300, PageSize.A4, PaperType.REGULAR);
            press.addPublication(book, 10);

            machine1.loadPaper(10000);
            machine1.printPublication(book, 10, PrintingMode.COLOR);

            press.saveToFile("report.txt");
        } catch (PrintException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}