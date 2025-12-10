package no.genie.accountiae2etestsselenium.elements;

import org.openqa.selenium.By;

public class LoginPageElements {
    public static By emailTextbox = By.xpath("//input[@id='username']");
    public static By passwordTextbox = By.xpath("//input[@id='password']");
    public static By loginButton = By.xpath("//input[@id='kc-login-button']");
    public static By invalidPasswordMessage = By.xpath("//span[@id='input-error']");
    public static By companyListTitle = By.xpath("//span[@data-cy='company-list-title']");
}
