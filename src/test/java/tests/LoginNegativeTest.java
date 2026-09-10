package tests;

import base.BaseTest;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtils;

public class LoginNegativeTest extends BaseTest {

    @DataProvider(name = "loginNegativeData")
    public Object[][] loginNegativeData() {

        ExcelUtils excel =
                new ExcelUtils(
                        "src/test/resources/LoginData.xlsx",
                        "Sheet1"
                );

        return excel.getSheetDataAsArray();
    }

    @Test(dataProvider = "loginNegativeData")
    public void invalidLoginTest(
            String username,
            String password,
            String expectedResult,
            String description
    ) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);


        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                description + " - Error message should be displayed"
        );
    }
}
