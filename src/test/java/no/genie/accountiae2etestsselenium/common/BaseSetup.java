package no.genie.accountiae2etestsselenium.common;

import no.genie.accountiae2etestsselenium.constant.ConstantGlobal;
import no.genie.accountiae2etestsselenium.drivers.DriverManager;
import no.genie.accountiae2etestsselenium.helpers.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;


public class BaseSetup {


    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = setupDriver(browser);
        PropertiesHelper.getAllFiles();
        //Set driver value to ThreadLocal
        DriverManager.setDriver(driver);
    }

    public WebDriver setupDriver(String browserName) {
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

    private WebDriver innitChromeDriver() {
        System.out.println("Launching Chrome browser...");
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (ConstantGlobal.HEADLESS == true) {
            options.addArguments("--headless=new");
            options.addArguments("window-size=1800,900");
        }
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver innitFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        //WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        FirefoxOptions options = new FirefoxOptions();
        if (ConstantGlobal.HEADLESS == true) {
            options.addArguments("--headless");
            options.addArguments("window-size=1800,900");
        }
        driver.manage().window().maximize();
        return driver;
    }


    @AfterMethod
    public void tearDown() throws InterruptedException {
        if (DriverManager.getDriver() != null) {
            Thread.sleep(2000);
            DriverManager.getDriver().quit();
        }
        // DriverManager.quit();
    }
}
