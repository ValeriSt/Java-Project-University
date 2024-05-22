package printing;

import interfaces.Employee;
import enums.PageSize;
import enums.PaperType;
import interfaces.PrintCostCalculator;
import publications.Publications;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PrintingPress implements PrintCostCalculator {
    private List<Employee> employees;
    private Map<PaperType, Double> basePrices;
    private Map<PageSize, Double> sizeMultipliers;
    private List<Publications> publications;

    private int discountThreshold;
    private double discountRate;

    public PrintingPress() {
        basePrices = new HashMap<>();
        basePrices.put(PaperType.NORMAL, 1.0);
        basePrices.put(PaperType.GLOSSY, 1.5);
        basePrices.put(PaperType.NEWSPAPER, 0.8);

        sizeMultipliers = new HashMap<>();
        sizeMultipliers.put(PageSize.A1, 1.0);
        sizeMultipliers.put(PageSize.A2, 0.8);
        sizeMultipliers.put(PageSize.A3, 0.6);
        sizeMultipliers.put(PageSize.A4, 0.4);
        sizeMultipliers.put(PageSize.A5, 0.2);

        discountThreshold = 1000;
        discountRate = 0.1;
    }


    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Map<PaperType, Double> getBasePrices() {
        return basePrices;
    }

    public void setBasePrices(Map<PaperType, Double> basePrices) {
        this.basePrices = basePrices;
    }

    public Map<PageSize, Double> getSizeMultipliers() {
        return sizeMultipliers;
    }

    public void setSizeMultipliers(Map<PageSize, Double> sizeMultipliers) {
        this.sizeMultipliers = sizeMultipliers;
    }

    public void setPublications(List<Publications> publications) {
        this.publications = publications;
    }

    public int getDiscountThreshold() {
        return discountThreshold;
    }

    public void setDiscountThreshold(int discountThreshold) {
        this.discountThreshold = discountThreshold;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }



    public double calculateTotalSalaryCosts() {
        double totalSalaryCosts = 0.0;
        for (Employee employee : employees) {
            totalSalaryCosts += employee.calculateSalary();
        }
        return totalSalaryCosts;
    }

    public double calculateTotalPaperCosts() {
        double totalPaperCosts = 0.0;
        for (Publications publication : publications) {
            totalPaperCosts += calculatePrintingCost(publication, publication.getPaperType());
        }
        return totalPaperCosts;
    }

    @Override
    public double calculatePrintingCost(Publications publication, PaperType paperType) {
        double basePrice = basePrices.get(paperType);
        double sizeMultiplier = sizeMultipliers.get(publication.getPageSize());
        double cost =  publication.getNumberOfPages() * basePrice * sizeMultiplier;

        if (publication.getNumberOfPages() > discountThreshold) {
            cost -= cost * discountRate;
        }

        return cost;
    }

}

