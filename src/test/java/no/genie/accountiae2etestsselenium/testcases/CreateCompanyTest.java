package no.genie.accountiae2etestsselenium.testcases;

import no.genie.accountiae2etestsselenium.common.BaseSetup;
import no.genie.accountiae2etestsselenium.constant.ConstantGlobal;
import no.genie.accountiae2etestsselenium.helpers.DataFakerHelper;
import no.genie.accountiae2etestsselenium.helpers.PropertiesHelper;
import no.genie.accountiae2etestsselenium.pages.CompanyListPage;
import no.genie.accountiae2etestsselenium.pages.CreateCompanyPage;
import no.genie.accountiae2etestsselenium.pages.LoginPage;
import org.testng.annotations.Test;

public class CreateCompanyTest extends BaseSetup {
    LoginPage loginPage;
    CompanyListPage companyListPage;
    CreateCompanyPage createCompanyPage;

    @Test
    public void testCreateRealCompany() {
        loginPage = new LoginPage();
        companyListPage = loginPage.login(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("password"));
        createCompanyPage = companyListPage.openCreateRealCompanyPage();
        createCompanyPage.enterCountry("Iraq");
        createCompanyPage.enterCompanyName(DataFakerHelper.getDataFaker().company().name());
        //createCompanyPage.clickCreateCompanyButton();
    }
}
