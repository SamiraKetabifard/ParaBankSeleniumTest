package com.example.pages;

import com.example.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AccountsOverviewPage extends BasePage {

    private By accountsTitle =
            By.xpath("//h1[contains(text(),'Accounts Overview')]");

    private By logout =
            By.linkText("Log Out");

    private By openNewAccount =
            By.linkText("Open New Account");

    private By accountsOverviewLink =
            By.linkText("Accounts Overview");

    private By accountTable =
            By.id("accountTable");

    private By accountRows =
            By.cssSelector("#accountTable tbody tr:has(td a)");

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountsOverviewDisplayed() {
        return isDisplayed(accountsTitle);
    }

    public void clickLogout() {
        click(logout);
    }

    public boolean isLogoutSuccessful() {
        return driver.getCurrentUrl()
                .contains("index.htm");
    }

    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    public OpenAccountPage clickOpenNewAccount() {
        click(openNewAccount);
        return new OpenAccountPage(driver);
    }

    public void goToAccountsOverview() {
        click(accountsOverviewLink);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        accountTable
                )
        );
    }

    private List<WebElement> getAccountRows() {

        // Wait until account rows are actually loaded
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("#accountTable tbody tr td a")
                )
        );

        return driver.findElements(accountRows);
    }

    public double getBalanceByRowIndex(int rowIndex) {

        List<WebElement> cells =
                getAccountRows()
                        .get(rowIndex)
                        .findElements(By.tagName("td"));

        String balanceText = cells.get(1).getText();

        return Double.parseDouble(
                balanceText
                        .replace("$", "")
                        .replace(",", "")
                        .trim()
        );
    }

    public String getAccountIdByRowIndex(int rowIndex) {

        List<WebElement> cells =
                getAccountRows()
                        .get(rowIndex)
                        .findElements(By.tagName("td"));

        return cells.get(0).getText();
    }

    public int getAccountCount() {

        return getAccountRows().size();
    }
}