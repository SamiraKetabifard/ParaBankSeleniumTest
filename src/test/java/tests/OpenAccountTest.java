package tests;

import base.BaseTest;
import com.example.pages.AccountsOverviewPage;
import com.example.pages.LoginPage;
import com.example.pages.OpenAccountPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OpenAccountTest extends BaseTest {

    @DataProvider(name="accountTypes")
    public Object[][] accountTypes(){

        return new Object[][]{

                {"CHECKING"},
                {"SAVINGS"}

        };

    }
    @Test(dataProvider = "accountTypes")
    public void verifyOpenNewAccount(String type){


        LoginPage loginPage =
                new LoginPage(driver);

        AccountsOverviewPage accountsPage =
                loginPage.loginHappyPath();

        OpenAccountPage openAccountPage =
                accountsPage.clickOpenNewAccount();

        openAccountPage
                .selectAccountType(type)
                .clickOpenAccount();

        Assert.assertTrue(
                openAccountPage.isAccountCreated(),
                "Account was not created for " + type
        );

    }

}