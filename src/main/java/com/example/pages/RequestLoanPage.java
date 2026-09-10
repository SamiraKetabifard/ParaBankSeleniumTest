package com.example.pages;

import com.example.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RequestLoanPage extends BasePage {

    private By requestLoan =
            By.linkText("Request Loan");

    private By amount =
            By.id("amount");

    private By downPayment =
            By.id("downPayment");

    private By fromAccount =
            By.id("fromAccountId");

    private By applyButton =
            By.xpath("//input[@type='button' and @value='Apply Now']");

    private By approvedMessage =
            By.xpath("//p[contains(text(),'Congratulations, your loan has been approved.')]");

    public RequestLoanPage(WebDriver driver) {
        super(driver);
    }

    public void goToRequestLoan() {

        click(requestLoan);

        waitForVisibility(amount);
        waitForVisibility(downPayment);
        waitForVisibility(fromAccount);
    }

    public void enterAmount(String value) {

        type(amount, value);
    }

    public void enterDownPayment(String value) {

        type(downPayment, value);
    }

    public void selectFromAccountByIndex(int index) {

        WebElement element =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                fromAccount
                        )
                );

        Select select = new Select(element);

        select.selectByIndex(index);
    }

    public void clickApplyNow() {

        click(applyButton);
    }

    public void requestLoan(
            String loanAmount,
            String payment,
            int accountIndex) {

        enterAmount(loanAmount);
        enterDownPayment(payment);
        selectFromAccountByIndex(accountIndex);
        clickApplyNow();
    }

    public boolean isLoanApproved() {

        try {
            return wait.until(driver ->
                    driver.findElements(approvedMessage)
                            .stream()
                            .anyMatch(WebElement::isDisplayed)
            );
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasErrorMessage(String text) {

        try {
            return wait.until(driver ->
                    driver.findElements(By.cssSelector("p.error"))
                            .stream()
                            .anyMatch(element ->
                                    element.isDisplayed()
                                            && element.getText().contains(text)
                            )
            );
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasInternalError() {

        return hasErrorMessage(
                "An internal error has occurred and has been logged."
        );
    }

    public boolean hasLargeAmountError() {

        return hasErrorMessage(
                "We cannot grant a loan in that amount with your available funds."
        );
    }

    public boolean hasInsufficientFundsError() {

        return hasErrorMessage(
                "You do not have sufficient funds for the given down payment."
        );
    }
}