package no.genie.accountiae2etestsselenium.elements;

import org.openqa.selenium.By;

public class CreateCompanyElements {
    public static By createCompanyPageTitle = By.xpath("//app-page-title");
    public static By createCompanyCountry = By.xpath("//app-country//input");
    public static By createCompanyCountryElements = By.xpath("//mat-option[@data-cy='country-option']//span//span");
    public static By createCompanyName = By.xpath("//textarea[@data-cy='company-name-input']");
    public static By createCompanyButton = By.xpath("//act-button[@id='create-legal-company-button']");
}
