import java.util.Arrays;
import java.util.List;
import enums.PageSize;
import enums.PaperType;
import publications.Publications;
import printing.PrintingPress;

public class Main {
    public static void main(String[] args) {
        // Create a PrintingPress object
        PrintingPress printingPress = new PrintingPress();

        Publications publication1 = new Publications("Book1", 100, PageSize.A4, PaperType.GLOSSY, "Color", 10, 100);
        publication1.setNumberOfCopies(500);
        publication1.setPricePerCopy(10.0);

        Publications publication2 = new Publications("Book2", 200, PageSize.A3, PaperType.NORMAL, "Black/White", 5, 200);
        publication2.setNumberOfCopies(300);
        publication2.setPricePerCopy(15.0);

        List<Publications> publications = Arrays.asList(publication1, publication2);

        // Set the list of publications to the PrintingPress object
        printingPress.setPublications(publications);

        // Write data to file
        printingPress.writeDataToFile("output.txt");

        // Read data from file
        printingPress.readDataFromFile("output.txt");
    }
}