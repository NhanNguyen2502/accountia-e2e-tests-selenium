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

    static {
        // Setup API key
        apiKey = System.getenv(getValue("GEMINI_API_KEY"));
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

//    public static GeminiAIHelper getInstance() {
//        if (instance == null) {
//            instance = new GeminiAIHelper();
//        }
//        return instance;
//    }

    public static String analyzeTestFailure(String testName, String exceptionMessage, String currentUrl) {
        String endpoint = API_URL + apiKey;
        String safeException = exceptionMessage.replace("\"", "'").replace("\n", " ");

        String prompt = """
                You are a QA Automation Expert. Analyze this Selenium test failure concisely:
                Test: '%s' | Error: '%s' | URL: '%s'
                    
                Respond STRICTLY in English with two short bullet points (maximum 2 sentences each):
                - **Root Cause**: [Briefly explain the exact reason for the failure]
                - **Suggested Fix**: [Provide a quick, actionable solution or code snippet]
                """.formatted(testName, safeException, currentUrl);

        // Generate the JSON payload for Gemini API
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
            return extractAndFormatAIResponse(response.body());
        } catch (Exception e) {
            return "Connect to Gemini failed: " + e.getMessage();
        }
    }


    /**
     * Extract JSON and clean the returned text.
     */
    public static String extractAndFormatAIResponse(String rawJson) {
        if (rawJson == null || rawJson.isEmpty()) {
            return "No response received from the AI.";
        }

        try {
            JsonObject jsonObject = JsonParser.parseString(rawJson).getAsJsonObject();

            // 1.Handling API error cases (such as incorrect API key, incorrect model)
            if (jsonObject.has("error")) {
                JsonObject errorObj = jsonObject.getAsJsonObject("error");
                return "❌ API Error (" + errorObj.get("code").getAsInt() + "): "
                        + errorObj.get("message").getAsString();
            }

            // 2.Extracting JSON according to the correct structure of the Google API.
            String extractedText = jsonObject
                    .getAsJsonArray("candidates").get(0).getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts").get(0).getAsJsonObject()
                    .get("text").getAsString();

            // 3.Clean up excess Markdown tags and reformat them.
            return formatForConsole(extractedText);

        } catch (Exception e) {
            return "⚠️ Error when reading feedback from AI: " + e.getMessage() + "\nRaw data: " + rawJson;
        }
    }

    /**
     * Clean and create a beautiful border for your console.
     */
    private static String formatForConsole(String rawText) {
        // Remove markdown code blocks (such as ```json or ```) that AI often adds automatically.
        String cleanText = rawText.replaceAll("```(json|markdown|html)?", "").trim();
        cleanText = cleanText.replaceAll("\\.\\s+", ".\n");
        // Create a border
        String separator = "=====================================================================";
        return String.format("\n%s\n%s\n%s\n", separator, cleanText, separator);
    }
}
