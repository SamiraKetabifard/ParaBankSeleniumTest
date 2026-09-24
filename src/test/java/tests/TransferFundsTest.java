package tests;

import base.BaseTest;
import com.example.pages.AccountsOverviewPage;
import com.example.pages.LoginPage;
import com.example.pages.TransferFundsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransferFundsTest extends BaseTest{

    @Test
    public void transferValidTest() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        AccountsOverviewPage accountsPage = loginPage.loginHappyPath();
        // Go to Accounts Overview
        accountsPage.goToAccountsOverview();
        // Make sure at least two accounts exist
        Assert.assertTrue(accountsPage.getAccountCount() >= 2, "At least two accounts are required");
        // Get balance before transfer
        double balanceBefore = accountsPage.getBalanceByRowIndex(0);
        double transferAmount = 100.00;
        // Make sure balance is enough
        Assert.assertTrue(balanceBefore >= transferAmount,
                "Insufficient balance for this test. Balance: " + balanceBefore);
        // Go to Transfer Funds
        TransferFundsPage transferPage = new TransferFundsPage(driver);
        transferPage.goToTransferFunds();
        // Transfer
        transferPage.transferFunds(String.valueOf(transferAmount), 0, 1);
        // Check Transfer Complete
        Assert.assertTrue(transferPage.isTransferSuccessful(), "Transfer was not successful");
        // Go back to Accounts Overview
        accountsPage.goToAccountsOverview();
        // Get balance after transfer
        double balanceAfter = accountsPage.getBalanceByRowIndex(0);
        // Verify balance
        Assert.assertEquals(balanceAfter, balanceBefore - transferAmount,
                "Balance was not deducted correctly");
    }
}