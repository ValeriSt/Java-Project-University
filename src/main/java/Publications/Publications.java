package publications;

import enums.PageSize;
import enums.PaperType;

import java.util.Map;

public class Publications{
    private final String title;
    private final int numberOfPages;
    private final PageSize pageSize;

    private final PaperType paperType;

    public Publications(String title, int numberOfPages, PageSize pageSize, PaperType paperType){
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.pageSize = pageSize;
        this.paperType = paperType;
    }

    private static final Map<PageSize, Double> sizeMultiplier = Map.of(
            PageSize.A5, 0.2,
            PageSize.A4, 0.4,
            PageSize.A3, 0.6,
            PageSize.A2, 0.8,
            PageSize.A1, 1.0
    );

    private static final Map<PaperType, Double> basePrices = Map.of(
            PaperType.REGULAR, 1.0,
            PaperType.GLOSSY, 1.5,
            PaperType.NEWSPAPER, 0.8
    );

    public double getCost() {
        return numberOfPages * sizeMultiplier.get(pageSize) * basePrices.get(paperType);
    }


    public String getTitle(){
        return title;
    }

    public int getNumberOfPages(){
        return numberOfPages;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Pages: " + numberOfPages + ", Size: " + pageSize + ", Type: " + paperType;
    }

}

