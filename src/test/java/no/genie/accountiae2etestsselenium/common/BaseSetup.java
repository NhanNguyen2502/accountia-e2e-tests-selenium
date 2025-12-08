package no.genie.accountiae2etestsselenium.common;

import no.genie.accountiae2etestsselenium.constant.ConstantGlobal;
import no.genie.accountiae2etestsselenium.drivers.DriverManager;
import no.genie.accountiae2etestsselenium.helpers.PropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class BaseSetup {


    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = setupDriver(browser);
        PropertiesHelper.getAllFiles();
        //Set giá trị driver đã đc khởi tạo vào ThreadLocal
        DriverManager.setDriver(driver);
    }

    public WebDriver setupDriver(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = innitChromeDriver();
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

    @AfterMethod
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
        // DriverManager.quit();
    }
}
