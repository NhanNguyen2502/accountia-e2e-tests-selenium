package no.genie.accountiae2etestsselenium.pages;

import no.genie.accountiae2etestsselenium.elements.CompanyElements;
import no.genie.accountiae2etestsselenium.elements.CreateCompanyElements;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static no.genie.accountiae2etestsselenium.drivers.DriverManager.*;
import static no.genie.accountiae2etestsselenium.keywords.WebUI.*;
import static no.genie.accountiae2etestsselenium.constant.ConstantGlobal.*;

public class CompanyListPage extends CompanyElements {

    public void openCreateCompanyPage() {
        Assert.assertTrue(waitForElementPresent(createCompanyButton, IMPLICIT_WAIT), "Create Company button is not displayed");
        clickElement(createCompanyButton);
        Assert.assertTrue(waitForElementPresent(createRealCompany, IMPLICIT_WAIT), "Create Real Company button is not displayed");
        Assert.assertTrue(waitForElementPresent(createDemoCompany, IMPLICIT_WAIT), "Create Demo Company button is not displayed");
    }

    public void checkCreateRealAndDemoCompanyButtonDisabled() {
        clickElement(createCompanyButton);
        Assert.assertTrue(isElementDisabled(createRealCompany), "Create Real Company button is not disabled");
        Assert.assertTrue(isElementDisabled(createDemoCompany), "Create Demo Company button is not disabled");
    }

    public void disableCompany() {
        waitForPageLoaded();
        Random rand = new Random();
        List<WebElement> toggleButtons = findElements(companyToggleEnableStatusButtons);
        if (toggleButtons.size() > 0) {
            var randomIndex = rand.nextDouble(0, toggleButtons.size());
            toggleButtons.get((int) randomIndex).click();
            Assert.assertTrue(waitForElementPresent(confirmDisableCompanyPopup, IMPLICIT_WAIT), "Confirm disable company popup is not displayed");
            clickElement(confirmDisableCompanyButton);
            System.out.println("Disabled company at index: " + (int) (randomIndex + 1));
        } else {
            System.out.println("All companies have been disabled.");
            Assert.assertTrue(waitForElementNotPresent(confirmDisableCompanyPopup, IMPLICIT_WAIT), "Confirm disable company popup is displayed");
        }
    }

    public void enableCompany() {
        waitForPageLoaded();
        Random rand = new Random();
        List<WebElement> toggleButtons = findElements(companyToggleDisableStatusButtons);
        if (toggleButtons.size() > 0) {
            var randomIndex = rand.nextDouble(0, toggleButtons.size());
            toggleButtons.get((int) randomIndex).click();
            Assert.assertTrue(waitForElementPresent(confirmDisableCompanyPopup, IMPLICIT_WAIT), "Confirm enable company popup is not displayed");
            clickElement(confirmEnableCompanyButton);
            System.out.println("Enabled company at index: " + (int) (randomIndex + 1));
        } else {
            System.out.println("All companies have been enabled.");
        }
    }

    public CreateCompanyPage openCreateRealCompanyPage() {
        waitForPageLoaded();
        clickElement(createCompanyButton);
        clickElement(createRealCompany);
        return new CreateCompanyPage();
    }
}
