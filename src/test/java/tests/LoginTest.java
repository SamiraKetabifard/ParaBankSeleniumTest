package tests;

import base.BaseTest;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {


    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        ExcelUtils excel =
                new ExcelUtils(
                        "src/test/resources/LoginData.xlsx",
                        "Sheet1"
                );

        return excel.getSheetDataAsArray();
    }


    @Test(dataProvider = "loginData")
    public void verifyLogin(
            String username,
            String password,
            String expectedResult,
            String description
    ) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);


        if (expectedResult.equalsIgnoreCase("PASS")) {

            Assert.assertTrue(
                    loginPage.isLoginSuccessful(),
                    description + " - Login should pass but failed"
            );

        } else {

            Assert.assertTrue(
                    loginPage.isErrorMessageDisplayed(),
                    description + " - Error message not displayed"
            );


            String actualError = loginPage.getErrorMessage();


            if (username.isEmpty() || password.isEmpty()) {

                Assert.assertEquals(
                        actualError,
                        "Please enter a username and password.",
                        description + " - Wrong error message"
                );

            } else {

                Assert.assertEquals(
                        actualError,
                        "The username and password could not be verified.",
                        description + " - Wrong error message"
                );
            }
        }
    }
}