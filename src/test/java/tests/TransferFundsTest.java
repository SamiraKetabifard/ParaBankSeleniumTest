package tests;

import base.BaseTest;
import com.example.pages.AccountsOverviewPage;
import com.example.pages.LoginPage;
import com.example.pages.TransferFundsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransferFundsTest extends BaseTest {

    @Test
    public void transferValidTest() {

        // Login
        LoginPage loginPage =
                new LoginPage(driver);

        AccountsOverviewPage accountsPage =
                loginPage.loginHappyPath();

        // Go to Accounts Overview
        accountsPage.goToAccountsOverview();

        System.out.println(
                "URL = " + driver.getCurrentUrl()
        );

        System.out.println(
                "Title = " + driver.getTitle()
        );

        System.out.println(
                "Account count = "
                        + accountsPage.getAccountCount()
        );

        // Make sure at least two accounts exist
        Assert.assertTrue(
                accountsPage.getAccountCount() >= 2,
                "At least two accounts are required"
        );

        // Get balance before transfer
        double balanceBefore =
                accountsPage.getBalanceByRowIndex(0);

        System.out.println(
                "Balance before = " + balanceBefore
        );

        double transferAmount = 100.00;

        // Make sure balance is enough
        Assert.assertTrue(
                balanceBefore >= transferAmount,
                "Insufficient balance for this test. Balance: "
                        + balanceBefore
        );

        // Go to Transfer Funds
        TransferFundsPage transferPage =
                new TransferFundsPage(driver);

        transferPage.goToTransferFunds();

        // Transfer
        transferPage.transferFunds(
                String.valueOf(transferAmount),
                0,
                1
        );

        // Check Transfer Complete
        Assert.assertTrue(
                transferPage.isTransferSuccessful(),
                "Transfer was not successful"
        );

        // Go back to Accounts Overview
        accountsPage.goToAccountsOverview();

        System.out.println(
                "URL after transfer = "
                        + driver.getCurrentUrl()
        );

        System.out.println(
                "Title after transfer = "
                        + driver.getTitle()
        );

        System.out.println(
                "Account count after transfer = "
                        + accountsPage.getAccountCount()
        );

        // Get balance after transfer
        double balanceAfter =
                accountsPage.getBalanceByRowIndex(0);

        System.out.println(
                "Balance after = " + balanceAfter
        );

        // Verify balance
        Assert.assertEquals(
                balanceAfter,
                balanceBefore - transferAmount,
                0.01,
                "Balance was not deducted correctly"
        );
    }
}