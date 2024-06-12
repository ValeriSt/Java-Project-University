import enums.PageSize;
import enums.PaperType;
import enums.PrintingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import printing.PrintingMachine;
import publications.Publications;

import javax.print.PrintException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PrintingMachineTest {
    private PrintingMachine printingMachine;

    @BeforeEach
    public void setUp() {
        printingMachine = new PrintingMachine("M1", 100, true);
    }

    @Test
    public void testLoadPaperSuccessfully() {
        printingMachine.loadPaper(50);
        assertEquals(50, printingMachine.getCurrentPaperLoad());
    }

    @Test
    public void testLoadPaperExceedingCapacity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> printingMachine.loadPaper(150));
        assertEquals("The machine cannot hold this much paper.", exception.getMessage());
    }

    @Test
    public void testPrintPublicationSuccessfully() throws PrintException {
        Publications publication = new Publications("Test Publication", 10, PageSize.A4, PaperType.REGULAR);
        printingMachine.loadPaper(50);
        printingMachine.printPublication(publication, 3, PrintingMode.BLACK_AND_WHITE);
        assertEquals(20, printingMachine.getCurrentPaperLoad());
        assertEquals(30, printingMachine.getPrintedPages());
        Map<String, Integer> printedPublications = printingMachine.getPrintedPublications();
        assertTrue(printedPublications.containsKey("Test Publication"));
        assertEquals(3, printedPublications.get("Test Publication"));
    }

    @Test
    public void testPrintPublicationNotEnoughPaper() {
        Publications publication = new Publications("Test Publication", 10, PageSize.A4, PaperType.REGULAR);
        printingMachine.loadPaper(20);
        assertThrows(PrintException.class, () -> printingMachine.printPublication(publication, 3, PrintingMode.BLACK_AND_WHITE));
    }

    @Test
    public void testPrintPublicationUnsupportedColorPrinting() {
        Publications publication = new Publications("Test Publication", 10, PageSize.A4, PaperType.REGULAR);
        PrintingMachine bwPrintingMachine = new PrintingMachine("M2", 100, false);
        bwPrintingMachine.loadPaper(50);
        assertThrows(PrintException.class, () -> bwPrintingMachine.printPublication(publication, 3, PrintingMode.COLOR));
    }


    @Test
    public void testPrintPublicationZeroCopies() {
        Publications publication = new Publications("Test Publication", 10, PageSize.A4, PaperType.REGULAR);
        printingMachine.loadPaper(50);
        assertThrows(IllegalArgumentException.class, () -> printingMachine.printPublication(publication, 0, PrintingMode.BLACK_AND_WHITE));
    }

    @Test
    public void testPrintPublicationNegativeCopies() {
        Publications publication = new Publications("Test Publication", 10, PageSize.A4, PaperType.REGULAR);
        printingMachine.loadPaper(50);
        assertThrows(IllegalArgumentException.class, () -> printingMachine.printPublication(publication, -1, PrintingMode.BLACK_AND_WHITE));
    }

    @Test
    public void testPrintDifferentPublications() throws PrintException {
        Publications publication1 = new Publications("Publication One", 5, PageSize.A4, PaperType.REGULAR);
        Publications publication2 = new Publications("Publication Two", 3, PageSize.A3, PaperType.GLOSSY);
        printingMachine.loadPaper(100);  // Increased paper load
        printingMachine.printPublication(publication1, 5, PrintingMode.BLACK_AND_WHITE);
        printingMachine.printPublication(publication2, 10, PrintingMode.BLACK_AND_WHITE);
        assertEquals(45, printingMachine.getCurrentPaperLoad());  // Updated expected paper load
        assertEquals(55, printingMachine.getPrintedPages());
        Map<String, Integer> printedPublications = printingMachine.getPrintedPublications();
        assertTrue(printedPublications.containsKey("Publication One"));
        assertEquals(5, printedPublications.get("Publication One"));
        assertTrue(printedPublications.containsKey("Publication Two"));
        assertEquals(10, printedPublications.get("Publication Two"));
    }



    @Test
    public void testToString() {
        String expected = "Machine ID: M1, Max Paper: 100, Supports Color: true, Printed Pages: 0";
        assertEquals(expected, printingMachine.toString());
    }

}
