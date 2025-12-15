package no.genie.accountiae2etestsselenium.elements;

import org.openqa.selenium.By;

public class CompanyElements {
    public static By createCompanyButton = By.xpath("//act-button[@data-cy='create-company-btn']");
    public static By createRealCompany = By.xpath("//mat-card//act-button[@data-cy='move-to-create-legal-company-button']//button");
    public static By createDemoCompany = By.xpath("//mat-card//act-button[@data-cy='move-to-create-test-company-button']//button");
    //Select company elements
    public static By companyToggleEnableStatusButtons = By.xpath("//app-company-toggle-status//label[.//input[@aria-checked='true']]");
    public static By companyToggleDisableStatusButtons = By.xpath("//app-company-toggle-status//label[.//input[@aria-checked='false']]");
    public static By confirmDisableCompanyPopup = By.xpath("//app-confirm-dialog//act-dialog");
    public static By confirmDisableCompanyButton = By.xpath("//button[@data-cy='dialog-yes-button']");
    public static By confirmEnableCompanyButton = By.xpath("//button[@data-cy='dialog-yes-button']");
}
