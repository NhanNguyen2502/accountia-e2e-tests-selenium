package no.genie.accountiae2etestsselenium.keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import static no.genie.accountiae2etestsselenium.constant.ConstantGlobal.*;
import static no.genie.accountiae2etestsselenium.drivers.DriverManager.*;

public class WebUI {
//    private static int EXPLICIT_WAIT_TIMEOUT = 30;
//    private static int WAIT_PAGE_LEADED_TIMEOUT = 30;

    public static void openURL(String url) {
        System.out.println("Open URL: " + System.getenv(url));
        getDriver().get(System.getenv(url));
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
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(IMPLICIT_WAIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("Element not visible: " + by.toString());
        }

    }

    public static boolean waitForElementNotPresent(By by, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(IMPLICIT_WAIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean isElementDisabled(By elementLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(IMPLICIT_WAIT), Duration.ofMillis(500));
            WebElement elementDisabled = getDriver().findElement(elementLocator);
            return !elementDisabled.isEnabled();

        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForElementPresent(By by, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(seconds), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void sendKeyToElement(By elementLocator, String text) {
        waitForElementVisible(elementLocator);
        getWebElement(elementLocator).clear();
        getWebElement(elementLocator).sendKeys(text);
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        try {
            wait.until(webDriver -> ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException e) {
            logConsole("Timeout waiting for Page Load Request to complete after " + PAGE_LOAD_TIMEOUT + " seconds");
        }
    }

    public static List<WebElement> findElements(By elementLocator) {
        try {
            waitForElementVisible(elementLocator);
            return getDriver().findElements(elementLocator);
        } catch (Exception e) {
            return null;
        }

    }
}
