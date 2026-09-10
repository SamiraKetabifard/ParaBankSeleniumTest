package tests;

import base.BaseTest;
import com.example.pages.LoginPage;
import com.example.pages.UpdateContactInfoPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class UpdateContactInfoTest extends BaseTest {

    @DataProvider(name = "updateContactData")
    public Object[][] updateContactData() {

        return new Object[][]{

                {
                        "s",
                        "ketabi",
                        "Main Street",
                        "Iran",
                        "IR",
                        "12345",
                        "5555555"
                }

        };
    }
    @Test(dataProvider = "updateContactData")
    public void updateContactInfoTest(
            String firstName,
            String lastName,
            String address,
            String city,
            String state,
            String zipCode,
            String phone){
        LoginPage loginPage = new LoginPage(driver);

        // login valid
        loginPage.loginHappyPath();

        // go to update contact info page
        driver.get(
                "https://parabank.parasoft.com/parabank/updateprofile.htm");

        UpdateContactInfoPage updatePage =
                new UpdateContactInfoPage(driver);

        updatePage.updateProfile(
                firstName,
                lastName,
                address,
                city,
                state,
                zipCode,
                phone);

        Assert.assertTrue(
                updatePage.isProfileUpdated(),
                "Profile was not updated successfully");
    }
}