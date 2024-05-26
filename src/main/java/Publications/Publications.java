package publications;

import enums.PageSize;
import enums.PaperType;

public class Publications{
    private String title;
    private int numberOfPages;
    private PageSize pageSize;

    private PaperType paperType;

    private boolean color;

    public Publications(String title, int numberOfPages, PageSize pageSize, PaperType paperType, boolean color){
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.pageSize = pageSize;
        this.paperType = paperType;
        this.color = color;

    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public int getNumberOfPages(){
        return numberOfPages;
    }
    public void setNumberOfPages(int numberOfPages){
        this.numberOfPages = numberOfPages;
    }

    public PageSize getPageSize(){
        return pageSize;
    }

    public void setPageSize(PageSize pageSize){
        this.pageSize = pageSize;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    public boolean isColor(){
        return color;
    }

    public void setColor(boolean color){
        this.color = color;
    }
}

