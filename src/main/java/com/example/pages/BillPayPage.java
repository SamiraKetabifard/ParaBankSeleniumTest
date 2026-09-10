package com.example.pages;

import com.example.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BillPayPage extends BasePage {

    private By billPay =
            By.linkText("Bill Pay");

    private By payeeName =
            By.name("payee.name");

    private By address =
            By.name("payee.address.street");

    private By city =
            By.name("payee.address.city");

    private By state =
            By.name("payee.address.state");

    private By zipCode =
            By.name("payee.address.zipCode");

    private By phone =
            By.name("payee.phoneNumber");

    private By accountNumber =
            By.name("payee.accountNumber");

    private By verifyAccount =
            By.name("verifyAccount");

    private By amount =
            By.name("amount");

    private By fromAccount =
            By.name("fromAccountId");

    private By sendPaymentButton =
            By.xpath("//input[@type='button' and @value='Send Payment']");

    private By successMessage =
            By.xpath("//p[contains(text(),'Bill Payment to')]");

    private By amountEmptyError =
            By.id("validationModel-amount-empty");

    private By invalidAccountError =
            By.id("validationModel-account-invalid");

    private By internalError =
            By.xpath("//p[contains(text(),'An internal error has occurred')]");


    public BillPayPage(WebDriver driver) {
        super(driver);
    }


    public void goToBillPay() {
        click(billPay);

        waitForVisibility(payeeName);
        waitForVisibility(amount);
        waitForVisibility(fromAccount);
    }


    public void enterPayeeName(String value) {
        type(payeeName, value);
    }


    public void enterAddress(String value) {
        type(address, value);
    }


    public void enterCity(String value) {
        type(city, value);
    }


    public void enterState(String value) {
        type(state, value);
    }


    public void enterZipCode(String value) {
        type(zipCode, value);
    }


    public void enterPhone(String value) {
        type(phone, value);
    }


    public void enterAccountNumber(String value) {
        type(accountNumber, value);
    }


    public void enterVerifyAccount(String value) {
        type(verifyAccount, value);
    }


    public void enterAmount(String value) {
        type(amount, value);
    }


    public void selectFromAccountByIndex(int index) {

        WebElement element =
                wait.until(ExpectedConditions.presenceOfElementLocated(fromAccount));

        Select select = new Select(element);

        wait.until(driver ->
                select.getOptions().size() > index
        );

        select.selectByIndex(index);
    }


    public void clickSendPayment() {
        click(sendPaymentButton);
    }


    public void payBill(
            String payee,
            String street,
            String cityName,
            String stateName,
            String zip,
            String phoneNumber,
            String account,
            String verify,
            String paymentAmount,
            int accountIndex) {

        enterPayeeName(payee);
        enterAddress(street);
        enterCity(cityName);
        enterState(stateName);
        enterZipCode(zip);
        enterPhone(phoneNumber);
        enterAccountNumber(account);
        enterVerifyAccount(verify);
        enterAmount(paymentAmount);

        selectFromAccountByIndex(accountIndex);

        clickSendPayment();
    }


    public boolean isPaymentSuccessful() {

        try {
            return wait.until(driver ->
                    driver.findElements(successMessage)
                            .stream()
                            .anyMatch(WebElement::isDisplayed)
            );
        } catch (Exception e) {
            return false;
        }
    }


    public boolean hasAmountEmptyError() {

        try {
            return wait.until(driver ->
                    driver.findElements(amountEmptyError)
                            .stream()
                            .anyMatch(WebElement::isDisplayed)
            );
        } catch (Exception e) {
            return false;
        }
    }


    public boolean hasInvalidAccountError() {

        try {
            return wait.until(driver ->
                    driver.findElements(invalidAccountError)
                            .stream()
                            .anyMatch(WebElement::isDisplayed)
            );
        } catch (Exception e) {
            return false;
        }
    }


    public boolean hasInternalError() {

        try {
            return wait.until(driver ->
                    driver.findElements(internalError)
                            .stream()
                            .anyMatch(WebElement::isDisplayed)
            );
        } catch (Exception e) {
            return false;
        }
    }
}
