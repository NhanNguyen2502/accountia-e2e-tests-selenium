package no.genie.accountiae2etestsselenium.pages;

import no.genie.accountiae2etestsselenium.elements.LoginPageElements;
import org.testng.Assert;

import static no.genie.accountiae2etestsselenium.helpers.PropertiesHelper.*;
import static no.genie.accountiae2etestsselenium.keywords.WebUI.*;

public class LoginPage extends LoginPageElements {

    public void loginInvalidEmail(String email, String password) {
        openURL(getValue("url"));
        //Verify login page is displayed
        sendKeyToElement(emailTextbox, email);
        sendKeyToElement(passwordTextbox, password);
        clickElement(loginButton);
    }

    public void loginInvalidPassword(String email, String password) {
        openURL(getValue("url"));
        //Verify login page is displayed
        sendKeyToElement(emailTextbox, email);
        sendKeyToElement(passwordTextbox, password);
        clickElement(loginButton);
        Assert.assertTrue(waitForElementPresent(invalidPasswordMessage, 10), "Invalid username or password message is not displayed");

    }

    public CompanyListPage login(String email, String password) {
        openURL(getValue("url"));
        //Verify login page is displayed
        waitForPageLoaded();
        sendKeyToElement(emailTextbox, email);
        sendKeyToElement(passwordTextbox, password);
        clickElement(loginButton);
        waitForPageLoaded();
        Assert.assertTrue(waitForElementNotPresent(invalidPasswordMessage, 30), "Login failed");
        return new CompanyListPage();
    }

}
