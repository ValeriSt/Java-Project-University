import employees.Employee;
import employees.MachineOperator;
import employees.Manager;
import enums.PageSize;
import enums.PaperType;
import enums.PrintingMode;
import org.junit.jupiter.api.Test;
import printing.PrintingMachine;
import printing.PrintingPress;
import publications.Publications;

import javax.print.PrintException;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

class PrintingPressTest {

    @Test
    void testGetTotalRevenue() {
        PrintingPress press = new PrintingPress(1000, 0.1);
        assertEquals(0.0, press.getTotalRevenue(), 0.001);
    }

    @Test
    void testGetEmployees() {
        PrintingPress press = new PrintingPress(1000, 0.1);
        assertEquals(0, press.getEmployees().size());
    }

    @Test
    void testGetPrintingMachines() {
        PrintingPress press = new PrintingPress(1000, 0.1);
        assertEquals(0, press.getPrintingMachines().size());
    }

    @Test
    void testGetPrintedPublicationsLog() {
        PrintingPress press = new PrintingPress(1000, 0.1);
        assertEquals(0, press.getPrintedPublicationsLog().size());
    }

    @Test
    void testPrintingPress() {
        PrintingPress press = new PrintingPress(1000, 0.1);
        assertEquals(1000, press.getDiscountThreshold(), 0.001);
        assertEquals(0.1, press.getDiscountRate(), 0.001);
    }

    @Test
    void testGetDiscountThreshold() {
        PrintingPress press = new PrintingPress(1000, 0.1);
        assertEquals(1000, press.getDiscountThreshold(), 0.001);
    }

    @Test
    void testGetDiscountRate() {
        PrintingPress press = new PrintingPress(1000, 0.1);
        assertEquals(0.1, press.getDiscountRate(), 0.001);
    }

    @Test
    void testAddPublication() {
        PrintingPress press = new PrintingPress(1000, 0.1);
        Publications book = new Publications("C++ Programming", 300, PageSize.A4, PaperType.REGULAR);
        press.addPublication(book, 10);
        double expectedRevenue = 10 * book.getCost();
        assertEquals(expectedRevenue, press.getTotalRevenue(), 0.001);
    }

    @Test
    void testAddEmployee() {
        PrintingPress house = new PrintingPress(1000, 0.1);
        Employee operator = new MachineOperator("John Doe", 1000);
        house.addEmployee(operator);
        assertEquals(1, house.getEmployees().size());
        assertEquals("John Doe", house.getEmployees().get(0).getName());
    }

    @Test
    void testAddPrintingMachine() {
        PrintingPress press = new PrintingPress(1000, 0.1);
        PrintingMachine machine = new PrintingMachine("M1", 10000, true);
        press.addPrintingMachine(machine);
        assertEquals(1, press.getPrintingMachines().size());
        assertEquals("M1", press.getPrintingMachines().get(0).getName());
    }

    @Test
    void testSaveToFile() throws PrintException {
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

        File file = new File("report.txt");
        assertTrue(file.exists());
        // Further checks can be done by reading the file content if necessary
        file.delete(); // Clean up the test file
    }
}