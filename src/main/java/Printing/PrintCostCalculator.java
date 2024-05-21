package printing;
import enums.PaperType;
import publications.Publications;

public interface PrintCostCalculator {
    double calculatePrintingCost(Publications publication, PaperType paperType);
}