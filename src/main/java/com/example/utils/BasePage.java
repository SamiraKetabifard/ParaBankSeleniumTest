package com.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // waits
    public void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    // actions
    public void click(By locator) {
        waitForClickable(locator);
        driver.findElement(locator).click();
    }
    // interactions
    public void type(By locator, String text) {
        waitForVisibility(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
    public String getText(By locator) {
        waitForVisibility(locator);
        return driver.findElement(locator).getText();
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public String getPageTitle() {
        return driver.getTitle();
    }
}
