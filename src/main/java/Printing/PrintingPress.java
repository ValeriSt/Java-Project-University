package printing;

import interfaces.Employee;
import enums.PageSize;
import enums.PaperType;
import interfaces.PrintCostCalculator;
import publications.Publications;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class PrintingPress implements PrintCostCalculator {
    private List<Employee> employees;
    private Map<PaperType, Double> basePrices;
    private Map<PageSize, Double> sizeMultipliers;
    private List<Publications> publications;

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
    }


    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setPublications(List<Publications> publications) {
        this.publications = publications;
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

    public void writeDataToFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.println("Failed to create file: " + filename);
                    return;
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String data = publications.toString() +
                    "\nTotal Salary Costs: " + calculateTotalSalaryCosts() +
                    "\nTotal Paper Costs: " + calculateTotalPaperCosts();
            writer.write(data);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void readDataFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }
    }

    @Override
    public double calculatePrintingCost(Publications publication, PaperType paperType) {
        double basePrice = basePrices.get(paperType);
        double sizeMultiplier = sizeMultipliers.get(publication.getPageSize());
        return publication.getNumberOfPages() * basePrice * sizeMultiplier;
    }


}

