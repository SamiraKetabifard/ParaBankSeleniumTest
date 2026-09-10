package com.example.pages;

import com.example.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;

public class OpenAccountPage extends BasePage {

    private By accountType =
            By.id("type");

    private By openAccountButton =
            By.xpath("//input[@value='Open New Account']");


    private By successMessage =
            By.xpath("//p[contains(text(),'Congratulations, your account is now open.')]");

    public OpenAccountPage(WebDriver driver) {

        super(driver);

    }

    public OpenAccountPage selectAccountType(String type) {

        Select select =
                new Select(driver.findElement(accountType));

        select.selectByVisibleText(type);

        return this;

    }

    public void clickOpenAccount(){

        click(openAccountButton);

    }

    public boolean isAccountCreated(){

        return isDisplayed(successMessage);

    }

}