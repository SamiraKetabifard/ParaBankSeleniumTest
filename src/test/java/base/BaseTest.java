package base;

import com.example.utils.ConfigReader;
import com.example.utils.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverSetUp.getDriver();
        driver.get(ConfigReader.getUrl());
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSetUp.quit();
    }
}