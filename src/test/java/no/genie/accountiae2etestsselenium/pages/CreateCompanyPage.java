package no.genie.accountiae2etestsselenium.pages;

import no.genie.accountiae2etestsselenium.elements.CreateCompanyElements;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static no.genie.accountiae2etestsselenium.constant.ConstantGlobal.*;
import static no.genie.accountiae2etestsselenium.keywords.WebUI.*;

public class CreateCompanyPage extends CreateCompanyElements {
    public void enterCountry(String countryName) {
        Assert.assertTrue(waitForElementPresent(createCompanyPageTitle, IMPLICIT_WAIT), "Create Company Page Title is not displayed");
        clickElement(createCompanyCountry);
        sendKeyToElement(createCompanyCountry, countryName);
        List<WebElement> countryElements = findElements(createCompanyCountryElements);
        waitForElementPresent(createCompanyCountryElements, 5);
        for (WebElement countryElement : countryElements) {
            if (countryElement.getText().equals(countryName)) {
                countryElement.click();
                System.out.println("Selected country: " + countryName);
                break;
            }
        }
    }

    public void enterCompanyName(String companyName) {
        clickElement(createCompanyName);
        sendKeyToElement(createCompanyName, companyName);
    }

    public CompanyListPage clickCreateCompanyButton() {
        clickElement(createCompanyButton);
        Assert.assertTrue(waitForElementNotPresent(createCompanyPageTitle, IMPLICIT_WAIT), "Create Company Button is still displayed");
        return new CompanyListPage();
    }

}
