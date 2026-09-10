package tests;

import base.BaseTest;
import com.example.pages.FindTransactionsPage;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindTransactionsTest extends BaseTest {

    private void openFindTransactions() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginHappyPath();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.open();
    }
    @Test
    public void findTransactionByAmount() {

        openFindTransactions();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.selectAccountByIndex(0);
        findPage.enterAmount("100");
        findPage.clickFindByAmount();

        Assert.assertTrue(
                findPage.isResultDisplayed(),
                "Transaction result should be displayed"
        );
    }
    @Test
    public void findTransactionByDate() {

        openFindTransactions();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.selectAccountByIndex(0);
        findPage.enterDate("08-05-2025");
        findPage.clickFindByDate();

        Assert.assertTrue(
                findPage.isResultDisplayed(),
                "Transaction result should be displayed"
        );
    }


    @Test
    public void findTransactionByDateRange() {

        openFindTransactions();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.selectAccountByIndex(0);
        findPage.enterFromDate("07-01-2025");
        findPage.enterToDate("08-01-2025");
        findPage.clickFindByDateRange();

        Assert.assertTrue(
                findPage.isResultDisplayed(),
                "Transaction result should be displayed"
        );
    }


    @Test
    public void findTransactionById() {

        openFindTransactions();

        FindTransactionsPage findPage =
                new FindTransactionsPage(driver);

        findPage.selectAccountByIndex(0);
        findPage.enterTransactionId("60985");
        findPage.clickFindByTransactionId();

        Assert.assertTrue(
                findPage.isResultDisplayed(),
                "Transaction result should be displayed"
        );
    }
}