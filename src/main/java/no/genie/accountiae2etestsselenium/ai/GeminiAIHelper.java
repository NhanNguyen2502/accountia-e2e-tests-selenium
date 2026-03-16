package no.genie.accountiae2etestsselenium.ai;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static no.genie.accountiae2etestsselenium.helpers.PropertiesHelper.*;

public class GeminiAIHelper {
    private static GeminiAIHelper instance;
    private static String apiKey;
    private static HttpClient httpClient;

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    private GeminiAIHelper() {
        // Luôn bảo mật API Key trong biến môi trường, không hard-code
        apiKey = System.getenv(getValue("GEMINI_API_KEY"));
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public static GeminiAIHelper getInstance() {
        if (instance == null) {
            instance = new GeminiAIHelper();
        }
        return instance;
    }

    public static String analyzeTestFailure(String testName, String exceptionMessage, String currentUrl) {
        String endpoint = API_URL + apiKey;
        System.out.println("Gọi Gemini API với endpoint: " + apiKey);

        // Làm sạch chuỗi đầu vào để tránh vỡ format JSON payload
        String safeException = exceptionMessage.replace("\"", "'").replace("\n", " ");

        String prompt = """
                You are an expert QA Automation Engineer. A Selenium test case named '%s' just failed.
                Here is the error message/stack trace: '%s'.
                The failure occurred on this URL: '%s'.
                            
                Please provide a concise Root Cause Analysis (RCA).
                Structure your response in Markdown with two short sections:
                1. **Root Cause**: Briefly explain why it failed.
                2. **Suggested Fix**: Provide actionable steps or code adjustments to fix the issue.
                """.formatted(testName, safeException, currentUrl);

        // Tạo JSON payload thủ công (khuyến nghị dùng thư viện Gson/Jackson trong thực tế)
        String payload = """
                {
                  "contents": [{
                    "parts": [{"text": "%s"}]
                  }],
                  "generationConfig": {
                    "temperature": 0.3
                  }
                }
                """.formatted(prompt.replace("\n", "\\n"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // TODO: Parse chuỗi JSON response từ Gemini để lấy nội dung text thực tế.
            // (Trong ví dụ này trả về raw JSON, bạn cần bóc tách field "text" trong object "candidates")
            return extractAndFormatAIResponse(response.body());
        } catch (Exception e) {
            return "Không thể kết nối tới Gemini ai để phân tích lỗi: " + e.getMessage();
        }
    }


    /**
     * Bóc tách JSON và làm sạch văn bản trả về
     */
    public static String extractAndFormatAIResponse(String rawJson) {
        if (rawJson == null || rawJson.isEmpty()) {
            return "Không nhận được phản hồi từ AI.";
        }

        try {
            JsonObject jsonObject = JsonParser.parseString(rawJson).getAsJsonObject();

            // 1. Xử lý trường hợp API trả về lỗi (như sai API Key, sai model)
            if (jsonObject.has("error")) {
                JsonObject errorObj = jsonObject.getAsJsonObject("error");
                return "❌ API Error (" + errorObj.get("code").getAsInt() + "): "
                        + errorObj.get("message").getAsString();
            }

            // 2. Bóc tách JSON theo đúng cấu trúc của Google API
            String extractedText = jsonObject
                    .getAsJsonArray("candidates").get(0).getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts").get(0).getAsJsonObject()
                    .get("text").getAsString();

            // 3. Làm sạch thẻ Markdown thừa và format lại
            return formatForConsole(extractedText);

        } catch (Exception e) {
            return "⚠️ Lỗi khi đọc phản hồi từ AI: " + e.getMessage() + "\nRaw data: " + rawJson;
        }
    }

    /**
     * Làm sạch và tạo khung viền đẹp mắt cho Console
     */
    private static String formatForConsole(String rawText) {
        // Xóa các block code markdown (như ```json hay ```) mà AI hay tự thêm vào
        String cleanText = rawText.replaceAll("```(json|markdown|html)?", "").trim();

        // Tạo khung viền
        String separator = "=====================================================================";
        return String.format("\n%s\n%s\n%s\n", separator, cleanText, separator);
    }
}
