package base;

import com.example.utils.ConfigReader;
import com.example.utils.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ScreenshotUtil;

public class BaseTest {

    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        driver = DriverSetUp.getDriver();

        driver.get(ConfigReader.getUrl());
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {

            ScreenshotUtil.attachScreenshot(driver);
        }
        DriverSetUp.quit();
    }
    public WebDriver getDriver() {

        return driver;
    }
}