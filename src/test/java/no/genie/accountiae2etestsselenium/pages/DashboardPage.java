package no.genie.accountiae2etestsselenium.pages;

import no.genie.accountiae2etestsselenium.elements.DashboardElements;
import org.testng.Assert;

import static no.genie.accountiae2etestsselenium.keywords.WebUI.*;
import static no.genie.accountiae2etestsselenium.constant.ConstantGlobal.*;
import static no.genie.accountiae2etestsselenium.drivers.DriverManager.*;

public class DashboardPage extends DashboardElements {

    public boolean isDashboardDisplayed() {
        waitForPageLoaded();
        return waitForElementPresent(dashboardTitle, IMPLICIT_WAIT);
    }

    public void verifyWelcomeMessage() {
        Assert.assertTrue(waitForElementPresent(welcomeMessage, IMPLICIT_WAIT), "Welcome message is not displayed");
    }

    public void verifySidebarMenu() {
        Assert.assertTrue(waitForElementPresent(sidebarMenu, IMPLICIT_WAIT), "Sidebar menu is not displayed");
    }

    public void clickUserProfile() {
        clickElement(userProfileIcon);
    }

    public void logout() {
        clickElement(userProfileIcon);
        Assert.assertTrue(waitForElementPresent(logoutButton, IMPLICIT_WAIT), "Logout button is not displayed");
        clickElement(logoutButton);
        waitForPageLoaded();
    }

    public CompanyListPage navigateToCompanyList() {
        clickElement(companyListLink);
        waitForPageLoaded();
        return new CompanyListPage();
    }

    public int getNotificationCount() {
        if (waitForElementPresent(notificationBadge, 5)) {
            String countText = getWebElement(notificationBadge).getText();
            return Integer.parseInt(countText);
        }
        return 0;
    }
}