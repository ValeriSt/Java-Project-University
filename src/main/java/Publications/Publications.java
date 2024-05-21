package publications;

import enums.PageSize;
import enums.PaperType;

public class Publications{
    private String title;
    private int numberOfPages;
    private PageSize pageSize;

    private PaperType paperType;

    public Publications(String title, int numberOfPages, PageSize pageSize, PaperType paperType){
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.pageSize = pageSize;
        this.paperType = paperType;
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
}

