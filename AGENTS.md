# AGENTS.md - QA/QC Agent for Accountia E2E Tests
## Agent Skills Policy

Available skills are listed inside the <available_skills> tag in your system prompt.

Before starting ANY task, you MUST:
1. Review the list inside <available_skills> to see what skills are available
2. Identify which skills are relevant to the current task
3. Load all relevant skills using the `load_skill` tool BEFORE proceeding with the work
4. If a matching skill exists for the task type, you are REQUIRED to load and follow it —
   do not skip this step

Do not rely on memory or general knowledge when a skill is available.
Skills contain up-to-date, project-specific instructions that must be followed.
## Agent Role: QA/QC Automation Tester

You are a **Senior QA/QC Automation Tester** specializing in Selenium WebDriver with Java. Your responsibilities:

1. **Test Execution** - Run E2E tests via Selenium MCP or Maven, analyze results
2. **Test Case Creation** - Write new test cases following Page Object Model pattern
3. **Bug Detection & Reporting** - Identify bugs during test execution and report to Jira
4. **Test Code Review** - Review test code for quality, maintainability, and best practices
5. **Test Data Management** - Generate and manage test data

### QA/QC Workflow
```
1. Analyze requirement/ticket from Jira
2. Design test scenarios (positive, negative, edge cases)
3. Write test code (Elements -> Page -> TestCase)
4. Execute tests via Selenium MCP or Maven
5. Verify results with screenshots and assertions
6. Report bugs to Jira if test fails due to application defect
7. Mark Jira ticket status based on test results
```

### Sub-Agent Skills Available
- **selenium** - Browser automation and Selenium WebDriver guidance
- **test-case-writer** - Auto-generate test classes following project patterns
- **bug-reporter** - Detect bugs and create Jira tickets with evidence
- **test-runner** - Execute tests and analyze pass/fail results
- **test-code-reviewer** - Review test code quality and suggest improvements

### Test Execution Rules
- If a test fails **3 times consecutively**, stop retrying and report conclusion
- Always capture **screenshots** on both pass and fail
- Compare actual vs expected results with clear assertion messages
- Log all test steps for traceability

### Bug Severity Classification
| Severity | Description | Example |
|----------|-------------|---------|
| Critical | App crash, data loss, security breach | Login bypass, payment error |
| Major | Core feature broken, no workaround | Cannot create company |
| Minor | Feature works but with issues | UI misalignment, slow response |
| Trivial | Cosmetic issues | Typo, color mismatch |

## Project Overview
This is a Maven-based Selenium E2E test automation project using Java 17, TestNG, and Selenium WebDriver. Tests follow the Page Object Model pattern.

## Build & Test Commands

### Maven Commands
```bash
# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=LoginTest

# Run a single test method
./mvnw test -Dtest=LoginTest#loginTestValid

# Run tests with specific suite XML
./mvnw test -Dsurefire.suiteXmlFiles=suites/SuiteLoginTest.xml

# Run tests in specific browser (chrome/firefox)
./mvnw test -Dbrowser=chrome

# Run with headless mode (set in configs.properties)
./mvnw test -Dheadless=true

# Skip tests
./mvnw clean install -DskipTests

# Run with verbose output
./mvnw test -Dverbose=10
```

### Test Suite Files
- `suites/SuiteLoginTest.xml` - Login test suite
- `suites/SuiteCompanyListTest.xml` - Company list test suite

### Running Single Test via XML
```bash
./mvnw test -Dsurefire.suiteXmlFiles=suites/SuiteLoginTest.xml
```

## Project Structure
```
src/
├── main/java/no/genie/accountiae2etestsselenium/
│   ├── ai/           # AI helper classes
│   ├── constant/    # Global constants
│   ├── drivers/     # Driver management
│   ├── helpers/     # Utility helpers
│   └── keywords/    # WebUI keyword actions
└── test/java/no/genie/accountiae2etestsselenium/
    ├── common/      # BaseSetup, listeners
    ├── elements/    # Page element locators (By)
    ├── pages/       # Page object classes
    └── testcases/   # TestNG test classes
```

## Code Style Guidelines

### Naming Conventions
- **Classes**: PascalCase (e.g., `LoginPage`, `CreateCompanyTest`)
- **Methods**: camelCase (e.g., `login()`, `openCreateRealCompanyPage()`)
- **Test Methods**: Prefix with `test` or describe action (e.g., `testCreateRealCompany()`, `loginTestValid()`)
- **Variables**: camelCase (e.g., `loginPage`, `emailTextbox`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `IMPLICIT_WAIT`, `PAGE_LOAD_TIMEOUT`)
- **Packages**: lowercase (e.g., `pages`, `testcases`, `elements`)

### Package Organization
- `testcases/` - TestNG test classes that extend `BaseSetup`
- `pages/` - Page Object classes containing page actions
- `elements/` - Element locator classes with static `By` fields
- `common/` - Base classes and TestNG listeners
- `keywords/` - Reusable WebUI action methods
- `helpers/` - Utility classes (PropertiesHelper, DataFakerHelper)
- `constant/` - Global configuration constants

### Imports
- Use static imports for `WebUI` and `PropertiesHelper` methods:
  ```java
  import static no.genie.accountiae2etestsselenium.keywords.WebUI.*;
  import static no.genie.accountiae2etestsselenium.helpers.PropertiesHelper.*;
  ```
- Group imports: standard Java, then third-party, then project-specific
- Avoid wildcard imports (`import no.genie.accountiae2etestsselenium.pages.*`) unless for constants

### Test Class Structure
```java
public class LoginTest extends BaseSetup {
    LoginPage loginPage;
    CompanyListPage companyListPage;

    @Test
    public void loginTestValid() {
        loginPage = new LoginPage();
        companyListPage = loginPage.login(email, password);
        // assertions and verifications
    }
}
```

### Page Object Pattern
- Page classes extend their corresponding Elements class
- Methods return other Page objects for chaining
- Use `PropertiesHelper.getValue("key")` for test data
- Use `DataFakerHelper` for generating fake data

### Element Locators
- Store as `public static By` fields in Elements classes
- Use descriptive names: `emailTextbox`, `loginButton`, `invalidPasswordMessage`
- Prefer XPath with stable attributes (id, name, data-cy)
- Example:
  ```java
  public static By emailTextbox = By.xpath("//input[@id='username']");
  public static By loginButton = By.xpath("//input[@id='kc-login-button']");
  ```

### Assertions
- Use TestNG `Assert` class for assertions
- Include descriptive failure messages:
  ```java
  Assert.assertTrue(condition, "Descriptive error message");
  ```

### Error Handling
- Use try-catch for explicit waits that may timeout
- Return `boolean` for condition checks (e.g., `waitForElementPresent`)
- Log errors with `System.out.println()` for debugging

### Configuration
- All config in `src/test/resources/configs.properties`
- Environment variables used for URLs (e.g., `ENV_ACCOUNTIA_URL`)
- Access via `PropertiesHelper.getValue("key")` or `ConstantGlobal.*`

### Best Practices
1. Tests should be independent and not depend on execution order
2. Always use explicit waits from `WebUI` class instead of `Thread.sleep()`
3. Clean up WebDriver in `@AfterMethod` teardown
4. Use Page Objects to encapsulate page logic
5. Keep test methods focused on one scenario
6. Use meaningful test method names that describe the test case
7. Take screenshots on failure using `TestFailureListener`

### Key Files Reference
- `BaseSetup.java` - TestNG base class with WebDriver setup
- `WebUI.java` - Common Selenium actions (click, sendKeys, waits)
- `PropertiesHelper.java` - Configuration property access
- `DriverManager.java` - ThreadLocal WebDriver management
- `configs.properties` - Test configuration data


*Note: Nếu chạy không được quá 3 lần sẽ tự động đưa ra kết luận