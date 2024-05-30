package printing;
import enums.PrintingMode;
import publications.Publications;
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
        if (currentPaperLoad + paperAmount > maxPaperLoad) {
            throw new IllegalArgumentException("The machine cannot hold this much paper.");
        }
        currentPaperLoad += paperAmount;
    }

    public void printPublication(Publications publication, int copies, PrintingMode mode) {
        if (!supportsColorPrinting && mode == PrintingMode.COLOR) {
            throw new IllegalArgumentException("This machine cannot print in color.");
        }
        int pagesNeeded = publication.getNumberOfPages() * copies;
        if (pagesNeeded > currentPaperLoad) {
            throw new IllegalArgumentException("Not enough paper loaded to print this publication.");
        }
        currentPaperLoad -= pagesNeeded;
        printedPages += pagesNeeded;
        printedPublications.put(publication.getTitle(), printedPublications.getOrDefault(publication.getTitle(), 0) + copies);
    }

    public int getPrintedPages() {
        return printedPages;
    }

    public String getName() {
        return name;
    }
}