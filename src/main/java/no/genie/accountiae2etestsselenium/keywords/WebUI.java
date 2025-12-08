package no.genie.accountiae2etestsselenium.keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static no.genie.accountiae2etestsselenium.drivers.DriverManager.*;

public class WebUI {
    private static int EXPLICIT_WAIT_TIMEOUT = 10;
    private static int WAIT_PAGE_LEADED_TIMEOUT = 30;

    public static void openURL(String url) {
        getDriver().get(url);
    }

    public static WebElement getWebElement(By elementLocator) {
        return getDriver().findElement(elementLocator);
    }

    public static void logConsole(String message) {
        System.out.println(message);
    }

    public static void clickElement(By elementLocator) {
        waitForElementVisible(elementLocator);
        getWebElement(elementLocator).click();
    }

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public static void sendKeyToElement(By elementLocator, String text) {
        waitForElementVisible(elementLocator);
        getWebElement(elementLocator).clear();
        getWebElement(elementLocator).sendKeys(text);
    }
}
