package com.example.pages;

import com.example.utils.BasePage;
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


    private By accountsOverview =
            By.xpath("//h1[contains(text(),'Accounts Overview')]");



    public LoginPage(WebDriver driver){

        super(driver);

    }



    public LoginPage enterUsername(String user){

        type(username,user);

        return this;

    }



    public LoginPage enterPassword(String pass){

        type(password,pass);

        return this;

    }



    public LoginPage clickLogin(){

        click(loginButton);

        return this;

    }



    public LoginPage login(String user,String pass){

        enterUsername(user);
        enterPassword(pass);
        clickLogin();

        return this;

    }



    public boolean isLoginSuccessful(){

        return isDisplayed(accountsOverview);

    }
    public boolean isErrorMessageDisplayed(){

        return driver.findElement(errorMessage).isDisplayed();

    }

    public String getErrorMessage(){

        return getText(errorMessage);

    }

}