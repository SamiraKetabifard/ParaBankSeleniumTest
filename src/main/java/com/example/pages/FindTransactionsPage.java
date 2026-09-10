package com.example.pages;

import com.example.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FindTransactionsPage extends BasePage {

    private By findTransactions =
            By.linkText("Find Transactions");

    private By accountDropdown =
            By.id("accountId");

    private By transactionId =
            By.id("transactionId");

    private By findByIdButton =
            By.id("findById");

    private By transactionDate =
            By.id("transactionDate");

    private By findByDateButton =
            By.id("findByDate");

    private By fromDate =
            By.id("fromDate");

    private By toDate =
            By.id("toDate");

    private By findByDateRangeButton =
            By.id("findByDateRange");

    private By amount =
            By.id("amount");

    private By findByAmountButton =
            By.id("findByAmount");


    public FindTransactionsPage(WebDriver driver) {
        super(driver);
    }


    public void open() {
        click(findTransactions);

        waitForVisibility(accountDropdown);
        waitForVisibility(transactionId);
        waitForVisibility(transactionDate);
        waitForVisibility(fromDate);
        waitForVisibility(toDate);
        waitForVisibility(amount);
    }


    public void selectAccountByIndex(int index) {

        WebElement element =
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        accountDropdown));

        Select select = new Select(element);

        wait.until(driver ->
                select.getOptions().size() > index);

        select.selectByIndex(index);
    }


    public void enterTransactionId(String value) {
        type(transactionId, value);
    }


    public void clickFindByTransactionId() {
        click(findByIdButton);
    }


    public void enterDate(String value) {
        type(transactionDate, value);
    }


    public void clickFindByDate() {
        click(findByDateButton);
    }


    public void enterFromDate(String value) {
        type(fromDate, value);
    }


    public void enterToDate(String value) {
        type(toDate, value);
    }


    public void clickFindByDateRange() {
        click(findByDateRangeButton);
    }


    public void enterAmount(String value) {
        type(amount, value);
    }


    public void clickFindByAmount() {
        click(findByAmountButton);
    }
    private By transactionResults =
            By.cssSelector("#transactionTable");

    public boolean isResultDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(transactionResults)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}