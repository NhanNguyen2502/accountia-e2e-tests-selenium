package no.genie.accountiae2etestsselenium.testcases;

import no.genie.accountiae2etestsselenium.common.BaseSetup;
import no.genie.accountiae2etestsselenium.helpers.PropertiesHelper;
import no.genie.accountiae2etestsselenium.pages.CompanyListPage;
import no.genie.accountiae2etestsselenium.pages.DashboardPage;
import no.genie.accountiae2etestsselenium.pages.LoginPage;
import org.testng.annotations.Test;

public class DashboardTest extends BaseSetup {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CompanyListPage companyListPage;

    @Test
    public void testDashboardDisplayed() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        companyListPage = loginPage.login(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("password"));
        dashboardPage.isDashboardDisplayed();
    }

    @Test
    public void testVerifyWelcomeMessage() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        companyListPage = loginPage.login(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("password"));
        dashboardPage.verifyWelcomeMessage();
    }

    @Test
    public void testVerifySidebarMenu() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        companyListPage = loginPage.login(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("password"));
        dashboardPage.verifySidebarMenu();
    }

    @Test
    public void testNavigateToCompanyListFromDashboard() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        companyListPage = loginPage.login(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("password"));
        companyListPage = dashboardPage.navigateToCompanyList();
    }

    @Test
    public void testLogout() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        companyListPage = loginPage.login(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("password"));
        dashboardPage.logout();
    }
}