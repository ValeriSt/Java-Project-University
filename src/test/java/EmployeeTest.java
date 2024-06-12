import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import employees.Employee;
import employees.MachineOperator;
import employees.Manager;

public class EmployeeTest {
    private MachineOperator machineOperator;
    private Manager manager;

    @BeforeEach
    public void setUp() {
        machineOperator = new MachineOperator("John Doe", 50000);
        manager = new Manager("Jane Smith", 70000, 100000, 0.1);
    }

    @Test
    public void testMachineOperatorSalary() {
        double revenue = 150000;
        double expectedSalary = 50000;
        double actualSalary = machineOperator.getSalary(revenue);
        assertEquals(expectedSalary, actualSalary, "MachineOperator salary should be equal to base salary regardless of revenue.");
    }

    @Test
    public void testManagerSalaryWithoutBonus() {
        double revenue = 80000;
        double expectedSalary = 70000;
        double actualSalary = manager.getSalary(revenue);
        assertEquals(expectedSalary, actualSalary, "Manager salary should be equal to base salary when revenue is below the bonus threshold.");
    }

    @Test
    public void testManagerSalaryWithBonus() {
        double revenue = 150000;
        double expectedSalary = 70000 + (1.0 + 0.1);
        double actualSalary = manager.getSalary(revenue);
        assertEquals(expectedSalary, actualSalary, "Manager salary should include bonus when revenue exceeds the bonus threshold.");
    }

    @Test
    public void testMachineOperatorName() {
        String expectedName = "John Doe";
        String actualName = machineOperator.getName();
        assertEquals(expectedName, actualName, "MachineOperator name should be correct.");
    }

    @Test
    public void testManagerName() {
        String expectedName = "Jane Smith";
        String actualName = manager.getName();
        assertEquals(expectedName, actualName, "Manager name should be correct.");
    }

}
