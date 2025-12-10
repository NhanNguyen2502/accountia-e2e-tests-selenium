package no.genie.accountiae2etestsselenium.testcases;

import no.genie.accountiae2etestsselenium.common.BaseSetup;
import no.genie.accountiae2etestsselenium.constant.ConstantGlobal;
import no.genie.accountiae2etestsselenium.helpers.PropertiesHelper;
import no.genie.accountiae2etestsselenium.pages.LoginPage;
import org.testng.annotations.Test;


public class LoginTest extends BaseSetup {

    @Test
    public void loginTestInvalidEmail() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginInvalidEmail(PropertiesHelper.getValue("invalid_email"), PropertiesHelper.getValue("password"));
    }

    @Test
    public void loginTestInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginInvalidEmail(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("invalid_password"));
    }

    @Test
    public void loginTestValid() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("password"));
    }

}
