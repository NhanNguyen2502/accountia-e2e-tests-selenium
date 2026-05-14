package no.genie.accountiae2etestsselenium.common;

import no.genie.accountiae2etestsselenium.ai.GeminiAIHelper;
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

import static no.genie.accountiae2etestsselenium.common.BrowserFactory.setUpdDriver;


public class BaseSetup {


    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = setUpdDriver(browser);
        PropertiesHelper.getAllFiles();
        //Set driver value to ThreadLocal
        DriverManager.setDriver(driver);
    }


    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
        if (ITestResult.FAILURE == result.getStatus()) {
            System.out.println("❌ Test Failed: " + result.getName());

            // 1. Lấy thông báo lỗi
            Throwable exception = result.getThrowable();
            String errorMessage = (exception != null) ? exception.getMessage() : "Unknown error";

            // 2. Lấy WebDriver từ class Test (giả sử BaseTest của bạn có hàm getDriver())
            // Bạn cần cast instance của test class về BaseTest để lấy driver
            Object testClass = result.getInstance();
            String currentUrl = DriverManager.getDriver().getCurrentUrl() != null ? DriverManager.getDriver().getCurrentUrl() : "Unknown URL";


            // 3. Gọi Gemini ai để phân tích
            System.out.println("🤖 Sending logs to Gemini for analysis...");
            String aiAnalysis = GeminiAIHelper.analyzeTestFailure(result.getName(), errorMessage, currentUrl);

            // 4. In kết quả ra console hoặc đính kèm vào Report
            System.out.println("\n---  🤖 GEMINI ai ANALYSIS ---");
            System.out.println(aiAnalysis);
            System.out.println("-----------------------------\n");
        }

        Thread.sleep(2000);
        DriverManager.quit();

    }
}
