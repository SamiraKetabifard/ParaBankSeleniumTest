package tests;

import base.BaseTest;
import com.example.pages.AccountsOverviewPage;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPositiveTest extends BaseTest {

    @Test
    public void successfulLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        AccountsOverviewPage accountsOverviewPage =
                loginPage.loginHappyPath();

        Assert.assertTrue(
                accountsOverviewPage.isAccountsOverviewDisplayed(),
                "Accounts Overview page should be displayed"
        );

        Assert.assertTrue(
                accountsOverviewPage.getCurrentPageUrl()
                        .contains("overview.htm"),
                "User did not reach overview page"
        );
    }
}