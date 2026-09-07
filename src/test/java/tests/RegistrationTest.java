package tests;

import base.BaseTest;
import com.example.pages.RegistrationPage;
import com.example.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class RegistrationTest extends BaseTest {

    private RegistrationPage registrationPage;

    @BeforeMethod
    public void setupPage() {
        registrationPage = new RegistrationPage(driver);
    }

    @DataProvider(name = "registerData")
    public Object[][] registerData() {

        JsonUtils json =
                new JsonUtils("src/test/resources/Register.json");

        List<Map<String, Object>> data =
                json.getRegisterData();

        Object[][] result = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }

        return result;
    }

    @Test(dataProvider = "registerData")
    public void verifyRegistration(Map<String, Object> data) {

        System.out.println(
                "Running test case: " + data.get("testCase")
        );

        registrationPage
                .clickRegisterLink()
                .fillRegistrationForm(
                        data.get("firstName").toString(),
                        data.get("lastName").toString(),
                        data.get("address").toString(),
                        data.get("city").toString(),
                        data.get("state").toString(),
                        data.get("zipCode").toString(),
                        data.get("phone").toString(),
                        data.get("ssn").toString(),
                        data.get("username").toString(),
                        data.get("password").toString(),
                        data.get("confirmPassword").toString()).clickRegisterButton();

        String expectedResult =
                data.get("expectedResult").toString();
        if (expectedResult.equals("success")) {

            Assert.assertTrue(
                    registrationPage
                            .getWelcomeMessage()
                            .contains("Welcome"),
                    "Welcome message was not displayed.");
            Assert.assertTrue(
                    registrationPage
                            .getSuccessMessage()
                            .contains("Your account was created successfully"),
                    "Registration success message was not displayed.");
        } else {
            Assert.assertFalse(
                    driver.getCurrentUrl().contains("overview.htm"),
                    "Invalid registration was accepted.");
        }
    }
}