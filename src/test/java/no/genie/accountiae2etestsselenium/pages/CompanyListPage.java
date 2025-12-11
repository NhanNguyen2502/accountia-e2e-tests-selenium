package no.genie.accountiae2etestsselenium.pages;

import no.genie.accountiae2etestsselenium.elements.CompanyElements;
import org.testng.Assert;

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
}
