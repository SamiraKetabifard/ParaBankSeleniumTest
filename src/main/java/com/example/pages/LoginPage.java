package com.example.pages;

import com.example.utils.BasePage;
import com.example.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {


    private By username =
            By.name("username");


    private By password =
            By.name("password");


    private By loginButton =
            By.xpath("//input[@value='Log In']");


    private By errorMessage =
            By.cssSelector("p.error");


    public LoginPage(WebDriver driver) {

        super(driver);

    }


    public LoginPage enterUsername(String user) {

        type(username, user);

        return this;
    }


    public LoginPage enterPassword(String pass) {

        type(password, pass);

        return this;
    }


    public LoginPage clickLogin() {

        click(loginButton);

        return this;
    }


    // برای تست های معمولی (مثلا negative)
    public LoginPage login(String user, String pass) {

        enterUsername(user);
        enterPassword(pass);
        clickLogin();

        return this;
    }

    public AccountsOverviewPage loginHappyPath() {

        enterUsername(
                ConfigReader.getUsername()
        );

        enterPassword(
                ConfigReader.getPassword()
        );

        clickLogin();

        waitForUrlContains("overview.htm");

        return new AccountsOverviewPage(driver);

    }


    public boolean isErrorMessageDisplayed() {

        return isDisplayed(errorMessage);

    }


    public String getErrorMessage() {

        return getText(errorMessage);

    }

}