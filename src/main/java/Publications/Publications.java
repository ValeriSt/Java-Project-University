package publications;

import enums.PageSize;
import enums.PaperType;

public class Publications {
    private String title;
    private int numberOfPages;
    private PageSize pageSize;

    private PaperType paperType;

    private String colour;

    private int numberOfCopies;
    private double pricePerCopy;
    private int discountThreshold;
    private double discountPercentage;

    public Publications(String title, int numberOfPages, PageSize pageSize, PaperType paperType, String isColour, int discountPercentage, int discountThreshold) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.pageSize = pageSize;
        this.paperType = paperType;
        this.colour = isColour;
        this.discountPercentage = discountPercentage;
        this.discountThreshold = discountThreshold;
    }

    public double calculateIncome() {
        double income = numberOfCopies * pricePerCopy;
        if (numberOfCopies > discountThreshold) {
            income -= income * discountPercentage;
        }
        return income;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public PageSize getPageSize() {
        return pageSize;
    }

    public void setPageSize(PageSize pageSize) {
        this.pageSize = pageSize;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    public void setDiscountThreshold(int discountThreshold) {
        this.discountThreshold = discountThreshold;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String isColor() {
        return colour;
    }

    public void setColor(String color) {
        this.colour = color;
    }

    @Override
    public String toString() {
        return "Publications : " + " " +
                title + " " +
                numberOfPages + " " +
                pageSize + " " +
                paperType + " " +
                colour + "\n";
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void setPricePerCopy(double pricePerCopy) {
        this.pricePerCopy = pricePerCopy;
    }
}

