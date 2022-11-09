package com.automation.pageObjectModel.service;

import com.automation.io.CsvUtil;
import com.automation.model.ICsvModel;
import com.automation.pageObjectModel.io.EnvironmentVarUtil;
import com.automation.pageObjectModel.shared.Browser;
import com.automation.pageObjectModel.shared.Environment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractSeleniumTest {
    private static WebDriver driver;
    private final static String DATA_FILE_PATH_FORMAT = "src/test/resources/data/{0}/inputdata.csv";
    private static ICsvModel testData;

    @BeforeAll
    protected static void setup() {
        final String targetEnvName = EnvironmentVarUtil.getTargetEnv();

        final List<String> environments = Arrays
                .stream(Environment.values())
                .map(Enum::name)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        final String filePath = Optional.of(targetEnvName)
                .filter(envName -> environments.contains(envName.toLowerCase()))
                .map(envName -> MessageFormat.format(DATA_FILE_PATH_FORMAT, envName))
                .orElseThrow(RuntimeException::new);

        testData = CsvUtil.initializeTestData(filePath);

        switch (Browser.valueOf(testData.getDriverName().toUpperCase())) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Only Chrome and Firefox browsers are supported");
        }
    }

    @AfterAll
    protected static void cleanUp() {
        driver.quit();
    }

    protected WebDriver webDriver() {
        return driver;
    }

    protected ICsvModel getTestData() {
        return testData;
    }
}
