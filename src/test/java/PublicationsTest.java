import enums.PageSize;
import enums.PaperType;
import org.junit.jupiter.api.Test;
import publications.Publications;

import static org.junit.jupiter.api.Assertions.*;

public class PublicationsTest {

    @Test
    public void testGetCostRegularPaper() {
        Publications publication = new Publications("Book", 100, PageSize.A4, PaperType.REGULAR);
        double expectedCost = 100 * 0.4 * 1.0; // Pages * sizeMultiplier(A4) * basePrices(REGULAR)
        assertEquals(expectedCost, publication.getCost(), 0.01, "Cost calculation for regular paper is incorrect.");
    }

    @Test
    public void testGetCostGlossyPaper() {
        Publications publication = new Publications("Magazine", 50, PageSize.A3, PaperType.GLOSSY);
        double expectedCost = 50 * 0.6 * 1.5; // Pages * sizeMultiplier(A3) * basePrices(GLOSSY)
        assertEquals(expectedCost, publication.getCost(), 0.01, "Cost calculation for glossy paper is incorrect.");
    }

    @Test
    public void testGetCostNewspaperPaper() {
        Publications publication = new Publications("Daily News", 20, PageSize.A5, PaperType.NEWSPAPER);
        double expectedCost = 20 * 0.2 * 0.8; // Pages * sizeMultiplier(A5) * basePrices(NEWSPAPER)
        assertEquals(expectedCost, publication.getCost(), 0.01, "Cost calculation for newspaper paper is incorrect.");
    }

    @Test
    public void testToString() {
        Publications publication = new Publications("Book", 100, PageSize.A4, PaperType.REGULAR);
        String expectedString = "Title: Book, Pages: 100, Size: A4, Type: REGULAR";
        assertEquals(expectedString, publication.toString(), "String representation of publication is incorrect.");
    }

    @Test
    public void testGetTitle() {
        Publications publication = new Publications("Book", 100, PageSize.A4, PaperType.REGULAR);
        assertEquals("Book", publication.getTitle(), "Incorrect title returned.");
    }

    @Test
    public void testGetNumberOfPages() {
        Publications publication = new Publications("Book", 100, PageSize.A4, PaperType.REGULAR);
        assertEquals(100, publication.getNumberOfPages(), "Incorrect number of pages returned.");
    }

    @Test
    public void testGetCost() {
        Publications publication = new Publications("Book", 100, PageSize.A4, PaperType.REGULAR);
        assertEquals(40.0, publication.getCost(), 0.01, "Incorrect cost returned.");
    }

}
