package no.genie.accountiae2etestsselenium.elements;

import org.openqa.selenium.By;

public class CompanyElements {
    public static By createCompanyButton = By.xpath("//act-button[@data-cy='create-company-btn']");
    public static By createRealCompany = By.xpath("//mat-card//act-button[@data-cy='move-to-create-legal-company-button']//button");
    public static By createDemoCompany = By.xpath("//mat-card//act-button[@data-cy='move-to-create-test-company-button']//button");
}
