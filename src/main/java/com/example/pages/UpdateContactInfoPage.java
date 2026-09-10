package com.example.pages;

import com.example.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpdateContactInfoPage extends BasePage {

    private By firstName =
            By.id("customer.firstName");

    private By lastName =
            By.id("customer.lastName");

    private By address =
            By.id("customer.address.street");

    private By city =
            By.id("customer.address.city");

    private By state =
            By.id("customer.address.state");

    private By zipCode =
            By.id("customer.address.zipCode");

    private By phone =
            By.id("customer.phoneNumber");


    private By updateButton =
            By.xpath("//input[@value='Update Profile']");


    private By successMessage =
            By.xpath("//h1[contains(text(),'Profile Updated')]");


    private By errorMessage =
            By.cssSelector("span.error");


    public UpdateContactInfoPage(WebDriver driver) {
        super(driver);
    }


    public UpdateContactInfoPage updateProfile(
            String first,
            String last,
            String addr,
            String cityName,
            String stateName,
            String zip,
            String phoneNumber
    ){

        type(firstName, first);
        type(lastName, last);
        type(address, addr);
        type(city, cityName);
        type(state, stateName);
        type(zipCode, zip);
        type(phone, phoneNumber);

        click(updateButton);

        return this;
    }
    public boolean isProfileUpdated(){

        return isDisplayed(successMessage);

    }
    public boolean isErrorDisplayed(){

        return isDisplayed(errorMessage);

    }
    public String getErrorMessage(){

        return getText(errorMessage);

    }

}