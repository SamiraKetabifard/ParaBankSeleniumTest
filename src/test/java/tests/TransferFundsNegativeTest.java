package tests;

import base.BaseTest;
import com.example.pages.LoginPage;
import com.example.pages.TransferFundsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransferFundsNegativeTest extends BaseTest {

    @Test
    public void emptyAmountTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.loginHappyPath();

        TransferFundsPage transferPage =
                new TransferFundsPage(driver);

        transferPage.goToTransferFunds();

        transferPage.transferFunds(
                "",
                0,
                1
        );

        // Empty amount should show an error
        Assert.assertTrue(
                transferPage.isErrorDisplayed(),
                "Error should be displayed for empty amount"
        );
    }

    @Test
    public void negativeAmountTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.loginHappyPath();

        TransferFundsPage transferPage =
                new TransferFundsPage(driver);

        transferPage.goToTransferFunds();

        transferPage.transferFunds(
                "-1000",
                0,
                1
        );

        // Negative amount should NOT be accepted
        Assert.assertFalse(
                transferPage.isTransferSuccessful(),
                "BUG: ParaBank accepts negative amount"
        );
    }

    @Test
    public void zeroAmountTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.loginHappyPath();

        TransferFundsPage transferPage =
                new TransferFundsPage(driver);

        transferPage.goToTransferFunds();

        transferPage.transferFunds(
                "0",
                0,
                1
        );

        // Zero amount should NOT be accepted
        Assert.assertFalse(
                transferPage.isTransferSuccessful(),
                "BUG: ParaBank accepts zero amount"
        );
    }

    @Test
    public void veryLargeAmountTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.loginHappyPath();

        TransferFundsPage transferPage =
                new TransferFundsPage(driver);

        transferPage.goToTransferFunds();

        transferPage.transferFunds(
                "999999999",
                0,
                1
        );

        // Very large amount should NOT be accepted
        Assert.assertFalse(
                transferPage.isTransferSuccessful(),
                "BUG: ParaBank accepts an excessively large amount"
        );
    }

    @Test
    public void textAmountTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.loginHappyPath();

        TransferFundsPage transferPage =
                new TransferFundsPage(driver);

        transferPage.goToTransferFunds();

        transferPage.transferFunds(
                "abc",
                0,
                1
        );

        // Text should NOT be accepted
        Assert.assertFalse(
                transferPage.isTransferSuccessful(),
                "BUG: ParaBank accepts text as amount"
        );
    }
}