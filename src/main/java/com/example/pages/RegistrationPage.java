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

    By successMessage =
            By.xpath("//p[contains(text(),'Your account was created successfully')]");
    By usernameError =
            By.id("customer.username.errors");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }


    public RegistrationPage clickRegisterLink() {

        click(By.linkText("Register"));

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


        type(firstName, firstNameValue);
        type(lastName, lastNameValue);
        type(address, addressValue);
        type(city, cityValue);
        type(state, stateValue);
        type(zipCode, zipCodeValue);
        type(phone, phoneValue);
        type(ssn, ssnValue);
        type(username, usernameValue);
        type(password, passwordValue);
        type(confirmPassword, confirmPasswordValue);


        return this;
    }


    public RegistrationPage clickRegisterButton() {

        click(registerButton);

        return this;
    }


    public boolean isErrorDisplayed() {

        return driver.findElements(errorMessage).size() > 0;
    }


    public boolean isRegistrationSuccessful() {

        return driver.findElements(successMessage).size() > 0;
    }


    public String getSuccessMessage() {

        return getText(successMessage);
    }


    public String getErrorMessage() {

        if(isErrorDisplayed()) {

            return getText(errorMessage);

        }

        return "";
    }
    public boolean isUsernameErrorDisplayed() {

        return driver.findElements(usernameError).size() > 0;

    }

}