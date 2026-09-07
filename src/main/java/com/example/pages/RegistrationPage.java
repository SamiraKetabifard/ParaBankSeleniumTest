package com.example.pages;

import com.example.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private By registerLink = By.linkText("Register");

    private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zipCode = By.id("customer.address.zipCode");
    private By phone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By username = By.id("customer.username");
    private By password = By.id("customer.password");
    private By confirmPassword = By.id("repeatedPassword");

    private By registerButton = By.cssSelector("input.button[value='Register']");

    private By welcomeMessage = By.cssSelector("#rightPanel h1");
    private By successMessage = By.cssSelector("#rightPanel p");


    // Open Registration Page
    public RegistrationPage clickRegisterLink() {
        click(registerLink);
        return this;
    }


    // Fill Registration Form
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
            String confirmPasswordValue
    ) {
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


    // Click Register Button
    public RegistrationPage clickRegisterButton() {
        click(registerButton);
        return this;
    }


    // Get Welcome Message
    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }


    // Get Success Message
    public String getSuccessMessage() {
        return getText(successMessage);
    }
}