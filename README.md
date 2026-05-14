# Accountia E2E Tests Selenium

End-to-End automated testing project for the Accountia web application, built with **Java 17**, **Selenium WebDriver**, **TestNG**, and **Maven**.

## Technologies

- Java 17
- Selenium Java 4.38.0
- TestNG 7.11.0
- Maven
- Spring Boot 3.5.7
- DataFaker
- Gson
- Chrome / Firefox

## Project Structure

```text
accountia-e2e-tests-selenium
├── pom.xml
├── suites
│   ├── MasterSuite.xml
│   ├── SuiteLoginTest.xml
│   └── SuiteCompanyListTest.xml
├── src
│   └── test
│       ├── java
│       │   └── no/genie/accountiae2etestsselenium
│       │       ├── common
│       │       ├── elements
│       │       ├── pages
│       │       └── testcases
│       └── resources
│           └── configs.properties
└── target
```

## Folder Overview

- `common`: Shared setup classes such as WebDriver initialization, base setup, and listeners.
- `elements`: Web element locators for each page.
- `pages`: Page Object classes that contain UI actions and page flows.
- `testcases`: TestNG test cases.
- `suites`: TestNG XML suite files.
- `configs.properties`: Environment and test configuration.

## Configuration

Update the configuration file before running tests:

```text
src/test/resources/configs.properties
```

Example:

```properties
url=https://your-accountia-url.com
email=your_email@example.com
password=your_password
headless=false
implicit_wait=30
page_load_timeout=30
SCREENSHOT_PATH=reports/screenshots
RECORDVIDEO_PATH=reports/records
```

Set `headless=true` to run tests without opening the browser UI.

## Prerequisites

Install the following tools:

- Java 17
- Maven
- Google Chrome or Mozilla Firefox

Verify installation:

```bash
java -version
mvn -version
```

## Install Dependencies

```bash
mvn clean install -DskipTests
```

Or use Maven Wrapper:

```bash
./mvnw clean install -DskipTests
```

On Windows:

```bash
mvnw.cmd clean install -DskipTests
```

## Running Tests

Run all tests using the default suite:

```bash
mvn clean test
```

Run a specific suite:

```bash
mvn clean test -DsuiteXmlFile=suites/SuiteLoginTest.xml
```

```bash
mvn clean test -DsuiteXmlFile=suites/SuiteCompanyListTest.xml
```

## Browser Selection

The browser is configured in the TestNG XML suite files:

```xml
<parameter name="browser" value="chrome"/>
```

To run with Firefox:

```xml
<parameter name="browser" value="firefox"/>
```

## Test Suites

- `MasterSuite.xml`: Runs multiple suites.
- `SuiteLoginTest.xml`: Runs login test cases.
- `SuiteCompanyListTest.xml`: Runs company list test cases.

## Test Reports

After running tests, reports are generated at:

```text
target/surefire-reports
```

## Adding a New Test

1. Add locators in the `elements` package.
2. Add page actions in the `pages` package.
3. Add test methods in the `testcases` package.
4. Register the test class in the related TestNG XML suite.

Example:

```xml
<class name="no.genie.accountiae2etestsselenium.testcases.NewFeatureTest"/>
```

## Common Issues

### Firefox SessionNotCreatedException

Make sure `FirefoxOptions` is configured before creating the driver:

```java
FirefoxOptions options = new FirefoxOptions();
options.addArguments("--headless");

WebDriver driver = new FirefoxDriver(options);
```

### TestNG XML Empty Tag Warning

Use self-closing tags for empty XML tags:

```xml
<listener class-name="no.genie.accountiae2etestsselenium.common.TestListener"/>
```

## Notes

- Do not hard-code credentials in test classes.
- Do not commit real passwords or API keys.
- Keep locators in `elements`, actions in `pages`, and test flows in `testcases`.
- Run `mvn clean test` before pushing code.