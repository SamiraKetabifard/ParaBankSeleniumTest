package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferFundsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By amountField =
            By.id("amount");

    private By fromAccountDropdown =
            By.id("fromAccountId");

    private By toAccountDropdown =
            By.id("toAccountId");

    private By transferButton =
            By.xpath("//input[@type='submit' and @value='Transfer']");

    private By successHeading =
            By.xpath("//h1[contains(text(),'Transfer Complete')]");

    private By errorHeading =
            By.xpath("//h1[contains(text(),'Error!')]");

    private By errorMessage =
            By.cssSelector("p.error");

    private By transferMenuLink =
            By.linkText("Transfer Funds");

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void goToTransferFunds() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        transferMenuLink
                )
        ).click();

        // Wait until the transfer page is loaded
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        amountField
                )
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        fromAccountDropdown
                )
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        toAccountDropdown
                )
        );
    }

    public void enterAmount(String amount) {

        WebElement amountElement =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                amountField
                        )
                );

        amountElement.clear();
        amountElement.sendKeys(amount);
    }

    public void selectFromAccountByIndex(int index) {

        WebElement element =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                fromAccountDropdown
                        )
                );

        Select select = new Select(element);

        wait.until(driver ->
                select.getOptions().size() > index
        );

        select.selectByIndex(index);
    }

    public void selectToAccountByIndex(int index) {

        WebElement element =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                toAccountDropdown
                        )
                );

        Select select = new Select(element);

        wait.until(driver ->
                select.getOptions().size() > index
        );

        select.selectByIndex(index);
    }

    public void clickTransferButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        transferButton
                )
        ).click();
    }

    public void transferFunds(
            String amount,
            int fromIndex,
            int toIndex) {

        enterAmount(amount);

        selectFromAccountByIndex(fromIndex);

        selectToAccountByIndex(toIndex);

        clickTransferButton();
    }

    public boolean isTransferSuccessful() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            successHeading
                    )
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isErrorDisplayed() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            errorHeading
                    )
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public String getErrorMessage() {

        WebElement error =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(errorMessage));

        return error.getAttribute("textContent").trim();
    }
}