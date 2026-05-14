package no.genie.accountiae2etestsselenium.drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public DriverManager() {
    }

    public static WebDriver getDriver() {
        return DriverManager.driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        DriverManager.driver.set(driverInstance);
    }

    public static void quit() {
        if (DriverManager.driver.get() != null) {
            DriverManager.driver.get().quit();
        }
        DriverManager.driver.remove();
    }
}

