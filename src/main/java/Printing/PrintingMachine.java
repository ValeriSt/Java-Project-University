package printing;
import enums.PrintingMode;
import publications.Publications;

import javax.print.PrintException;
import java.util.HashMap;
import java.util.Map;

public class PrintingMachine {
    private final String name;
    private final int maxPaperLoad;
    private int currentPaperLoad = 0;

    private int printedPages = 0;
    private final boolean supportsColorPrinting;
    private final Map<String, Integer> printedPublications = new HashMap<>();


    public PrintingMachine(String name, int maxPaperLoad, boolean supportsColorPrinting) {
        this.name = name;
        this.maxPaperLoad = maxPaperLoad;
        this.supportsColorPrinting = supportsColorPrinting;
    }

    public void loadPaper(int paperAmount) {
        if (paperAmount <= 0) {
            throw new IllegalArgumentException("Paper amount must be positive.");
        }
        if (currentPaperLoad + paperAmount > maxPaperLoad) {
            throw new IllegalArgumentException("The machine cannot hold this much paper.");
        }
        currentPaperLoad += paperAmount;
    }

    public void printPublication(Publications publication, int copies, PrintingMode mode) throws PrintException {
        if (copies <= 0) {
            throw new IllegalArgumentException("Number of copies must be positive.");
        }
        if (!supportsColorPrinting && mode == PrintingMode.COLOR) {
            throw new PrintException("This machine cannot print in color.");
        }
        int pagesNeeded = publication.getNumberOfPages() * copies;
        if (pagesNeeded > currentPaperLoad) {
            throw new PrintException("Not enough paper loaded to print this publication.");
        }
        currentPaperLoad -= pagesNeeded;
        printedPages += pagesNeeded;
        printedPublications.put(publication.getTitle(), printedPublications.getOrDefault(publication.getTitle(), 0) + copies);
    }

    public int getPrintedPages() {
        return printedPages;
    }

    public Map<String, Integer> getPrintedPublications() {
        return printedPublications;
    }

    public int getCurrentPaperLoad() {
        return currentPaperLoad;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Machine ID: " + name + ", Max Paper: " + maxPaperLoad + ", Supports Color: " + supportsColorPrinting + ", Printed Pages: " + printedPages;
    }
}