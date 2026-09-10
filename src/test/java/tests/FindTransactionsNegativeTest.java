package tests;

import base.BaseTest;
import com.example.pages.FindTransactionsPage;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindTransactionsNegativeTest extends BaseTest {

    private void openFindTransactions() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginHappyPath();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.open();
    }
    @Test
    public void invalidTransactionId() {

        openFindTransactions();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.selectAccountByIndex(0);
        findPage.enterTransactionId("dd99d9");
        findPage.clickFindByTransactionId();

        Assert.assertFalse(
                findPage.isResultDisplayed(),
                "Invalid Transaction ID should not return a result"
        );
    }
    @Test
    public void emptyTransactionId() {

        openFindTransactions();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.selectAccountByIndex(0);
        findPage.enterTransactionId("");
        findPage.clickFindByTransactionId();

        Assert.assertFalse(
                findPage.isResultDisplayed(),
                "Empty Transaction ID should not return a result"
        );
    }
    @Test
    public void invalidAmount() {

        openFindTransactions();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.selectAccountByIndex(0);
        findPage.enterAmount("12ab");
        findPage.clickFindByAmount();

        Assert.assertFalse(
                findPage.isResultDisplayed(),
                "Invalid amount should not return a result"
        );
    }
    @Test
    public void negativeAmount() {

        openFindTransactions();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.selectAccountByIndex(0);
        findPage.enterAmount("-500");
        findPage.clickFindByAmount();

        Assert.assertFalse(
                findPage.isResultDisplayed(),
                "Negative amount should not return a result"
        );
    }


    @Test
    public void invalidDateFormat() {

        openFindTransactions();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.selectAccountByIndex(0);
        findPage.enterDate("03082025");
        findPage.clickFindByDate();

        Assert.assertFalse(
                findPage.isResultDisplayed(),
                "Invalid date format should not return a result"
        );
    }
}
