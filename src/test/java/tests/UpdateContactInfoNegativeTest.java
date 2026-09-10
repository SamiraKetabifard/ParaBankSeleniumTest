package tests;

import base.BaseTest;
import com.example.pages.LoginPage;
import com.example.pages.UpdateContactInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateContactInfoNegativeTest extends BaseTest {


    @Test
    public void emptyFirstNameTest() {

        LoginPage loginPage = new LoginPage(driver);

        // login with valid user
        loginPage.loginHappyPath();


        UpdateContactInfoPage updatePage =
                new UpdateContactInfoPage(driver);


        driver.get(
                "https://parabank.parasoft.com/parabank/updateprofile.htm");
        updatePage.updateProfile(
                "",
                "Smith",
                "Street",
                "City",
                "VA",
                "12345",
                "555555"
        );


        Assert.assertTrue(
                updatePage.isErrorDisplayed(),
                "First name required message should appear"
        );
    }

    @Test
    public void phoneFieldValidationBugTest() {

        LoginPage loginPage = new LoginPage(driver);

        // login with valid user
        loginPage.loginHappyPath();


        UpdateContactInfoPage updatePage =
                new UpdateContactInfoPage(driver);


        driver.get(
                "https://parabank.parasoft.com/parabank/updateprofile.htm");

        updatePage.updateProfile(
                "John",
                "Smith",
                "Street",
                "City",
                "VA",
                "12345",
                "");
        Assert.assertTrue(
                updatePage.isProfileUpdated(),
                "BUG: Profile should be updated because phone has no validation"
        );
    }
}