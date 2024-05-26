package printing;
import publications.Publications;
import java.util.HashMap;
import java.util.Map;

public class PrintingMachine {
    private final int maxPaperLoad;
    private int currentPaperLoad;
    private int pagesPerMinute;
    private final boolean colorPrinting;
    private final Map<Publications, Integer> printedPublications;

    public PrintingMachine(int maxPaperLoad, int pagesPerMinute, boolean colorPrinting) {
        this.maxPaperLoad = maxPaperLoad;
        this.pagesPerMinute = pagesPerMinute;
        this.colorPrinting = colorPrinting;
        this.currentPaperLoad = 0;
        this.printedPublications = new HashMap<>();
    }

    public void loadPaper(int paperAmount) {
        if (currentPaperLoad + paperAmount > maxPaperLoad) {
            throw new IllegalArgumentException("The machine cannot hold this much paper.");
        }
        currentPaperLoad += paperAmount;
    }

    public void printPublication(Publications publication, int copies) {
        if (!colorPrinting && publication.isColor()) {
            throw new IllegalArgumentException("This machine cannot print in color.");
        }
        int pagesNeeded = publication.getNumberOfPages() * copies;
        if (pagesNeeded > currentPaperLoad) {
            throw new IllegalArgumentException("Not enough paper loaded to print this publication.");
        }
        currentPaperLoad -= pagesNeeded;
        printedPublications.put(publication, printedPublications.getOrDefault(publication, 0) + copies);
    }

    public int getPrintedPages() {
        return printedPublications.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getNumberOfPages() * entry.getValue())
                .sum();
    }

    public int getPagesPerMinute() {
        return pagesPerMinute;
    }

    public void setPagesPerMinute(int pagesPerMinute) {
        this.pagesPerMinute = pagesPerMinute;
    }
}