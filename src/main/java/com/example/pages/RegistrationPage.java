package com.example.pages;

import com.example.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {


    By firstName = By.id("customer.firstName");
    By lastName = By.id("customer.lastName");
    By address = By.id("customer.address.street");
    By city = By.id("customer.address.city");
    By state = By.id("customer.address.state");
    By zipCode = By.id("customer.address.zipCode");
    By phone = By.id("customer.phoneNumber");
    By ssn = By.id("customer.ssn");
    By username = By.id("customer.username");
    By password = By.id("customer.password");
    By confirmPassword = By.id("repeatedPassword");

    By registerButton = By.xpath("//input[@value='Register']");

    By errorMessage = By.cssSelector(".error");

    By welcomeMessage = By.cssSelector("#rightPanel h1");

    By successMessage =
            By.xpath("//p[contains(text(),'Your account was created successfully')]");


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }


    public RegistrationPage clickRegisterLink() {

        driver.findElement(By.linkText("Register")).click();

        return this;
    }


    public RegistrationPage fillRegistrationForm(
            String firstNameValue,
            String lastNameValue,
            String addressValue,
            String cityValue,
            String stateValue,
            String zipCodeValue,
            String phoneValue,
            String ssnValue,
            String usernameValue,
            String passwordValue,
            String confirmPasswordValue) {


        driver.findElement(firstName).sendKeys(firstNameValue);

        driver.findElement(lastName).sendKeys(lastNameValue);

        driver.findElement(address).sendKeys(addressValue);

        driver.findElement(city).sendKeys(cityValue);

        driver.findElement(state).sendKeys(stateValue);

        driver.findElement(zipCode).sendKeys(zipCodeValue);

        driver.findElement(phone).sendKeys(phoneValue);

        driver.findElement(ssn).sendKeys(ssnValue);

        driver.findElement(username).sendKeys(usernameValue);

        driver.findElement(password).sendKeys(passwordValue);

        driver.findElement(confirmPassword)
                .sendKeys(confirmPasswordValue);


        return this;
    }


    public RegistrationPage clickRegisterButton() {

        driver.findElement(registerButton).click();

        return this;
    }


    public boolean isErrorDisplayed() {

        return driver.findElements(errorMessage).size() > 0;
    }


    public boolean isRegistrationSuccessful() {

        return driver.findElements(welcomeMessage).size() > 0;
    }


    public String getWelcomeMessage() {

        return driver.findElement(welcomeMessage).getText();
    }


    public String getSuccessMessage() {

        return driver.findElement(successMessage).getText();
    }


    public String getErrorMessage() {

        if(driver.findElements(errorMessage).size() > 0){

            return driver.findElement(errorMessage).getText();

        }

        return "";
    }

}