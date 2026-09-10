package tests;


import base.BaseTest;
import com.example.pages.AccountsOverviewPage;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @Test
    public void verifyLogout() {


        LoginPage loginPage =
                new LoginPage(driver);

        AccountsOverviewPage accountsPage =
                loginPage.loginHappyPath();

        Assert.assertTrue(
                accountsPage.isAccountsOverviewDisplayed(),
                "Login failed");
        accountsPage.clickLogout();

        Assert.assertTrue(
                accountsPage.isLogoutSuccessful(),
                "Logout failed"
        );

    }

}