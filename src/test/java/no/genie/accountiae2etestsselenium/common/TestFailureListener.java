package no.genie.accountiae2etestsselenium.common;

import no.genie.accountiae2etestsselenium.ai.GeminiAIHelper;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());

        // 1. Lấy thông báo lỗi
        Throwable exception = result.getThrowable();
        String errorMessage = (exception != null) ? exception.getMessage() : "Unknown error";

        // 2. Lấy WebDriver từ class Test (giả sử BaseTest của bạn có hàm getDriver())
        // Bạn cần cast instance của test class về BaseTest để lấy driver
        Object testClass = result.getInstance();
        String currentUrl = "Unknown URL";

        /* Uncomment phần này nếu bạn có BaseTest quản lý driver
        if (testClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testClass).getDriver();
            currentUrl = driver.getCurrentUrl();
        }
        */

        // 3. Gọi Gemini ai để phân tích
        System.out.println("🤖 Đang gửi log cho Gemini ai phân tích...");
        String aiAnalysis = GeminiAIHelper.getInstance()
                .analyzeTestFailure(result.getName(), errorMessage, currentUrl);

        // 4. In kết quả ra console hoặc đính kèm vào Report
        System.out.println("\n--- 🧠 GEMINI ai ANALYSIS ---");
        System.out.println(aiAnalysis);
        System.out.println("-----------------------------\n");

        // Tùy chọn: Chụp thêm màn hình (Screenshot) tại đây.
    }

    // Các method khác của ITestListener có thể để trống
    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }
}
