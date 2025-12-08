package no.genie.accountiae2etestsselenium.testcases;

import no.genie.accountiae2etestsselenium.common.BaseSetup;
import no.genie.accountiae2etestsselenium.constant.ConstantGlobal;
import no.genie.accountiae2etestsselenium.helpers.PropertiesHelper;
import no.genie.accountiae2etestsselenium.pages.LoginPage;
import org.testng.annotations.Test;


public class LoginTest extends BaseSetup {

    @Test
    public void loginTestValidEmail() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginInvalidEmail(PropertiesHelper.getValue("email"), PropertiesHelper.getValue("password"));
    }

}
