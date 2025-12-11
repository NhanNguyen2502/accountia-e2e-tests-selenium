package no.genie.accountiae2etestsselenium.testcases;

import no.genie.accountiae2etestsselenium.common.BaseSetup;
import no.genie.accountiae2etestsselenium.helpers.PropertiesHelper;
import no.genie.accountiae2etestsselenium.pages.CompanyListPage;
import no.genie.accountiae2etestsselenium.pages.LoginPage;
import org.testng.annotations.Test;

public class CompanyListTest extends BaseSetup {
    LoginPage loginPage;
    CompanyListPage companyListPage;

    @Test
    public void testOpenCreateCompanyPage() {
        loginPage = new LoginPage();
        companyListPage = loginPage.login(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("password"));
        companyListPage.openCreateCompanyPage();
    }

    @Test
    public void testValidateCreateCompany() {
        loginPage = new LoginPage();
        companyListPage = loginPage.login(PropertiesHelper.getValue("maximum_company_email"), PropertiesHelper.getValue("password"));
        companyListPage.checkCreateRealAndDemoCompanyButtonDisabled();
    }
}
