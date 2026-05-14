package no.genie.accountiae2etestsselenium.common;

import no.genie.accountiae2etestsselenium.constant.ConstantGlobal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
    /**
     * Create WebDriver instance based on browser name
     *
     * @param browserName browser name ("chrome", "firefox")
     * @return WebDriver instance
     */
    public static WebDriver setUpdDriver(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = innitChromeDriver();
                break;
            case "firefox":
                driver = innitFirefoxDriver();
                break;
            default:
                System.out.println("Browser not supported. Launching Chrome as default.");
                driver = innitChromeDriver();
                break;
        }
        return driver;
    }

    /**
     * Create Chrome WebDriver with configured options
     *
     * @return ChromeDriver instance
     */
    private static WebDriver innitChromeDriver() {
        System.out.println("Launching Chrome browser...");

        // Disable detailed Selenium logs on the Console
        System.setProperty("webdriver.chrome.silentOutput", "true");
        // Disable CDP version warning logs
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF);
        ChromeOptions options = new ChromeOptions();
        if (ConstantGlobal.HEADLESS == true) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1800,900");
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Create Firefox WebDriver with configured options
     *
     * @return FirefoxDriver instance
     */
    private static WebDriver innitFirefoxDriver() {
        System.out.println("Launching Firefox browser...");

        // Disable detailed Selenium logs for Firefox
        System.setProperty("webdriver.gecko.driver.silent", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF);

        // Configure Firefox options first
        FirefoxOptions options = new FirefoxOptions();
        if (ConstantGlobal.HEADLESS == true) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1800,900");
        }

        // Add additional stability options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // Set preferences for better stability
        options.addPreference("dom.webdriver.enabled", false);
        options.addPreference("useAutomationExtension", false);

        try {
            // Create driver with options
            WebDriver driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            return driver;
        } catch (Exception e) {
            System.err.println("Failed to initialize Firefox driver: " + e.getMessage());
            System.out.println("Falling back to Chrome driver...");
            return innitChromeDriver();
        }
    }
}
