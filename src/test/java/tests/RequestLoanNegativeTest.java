package tests;

import base.BaseTest;
import com.example.pages.LoginPage;
import com.example.pages.AccountsOverviewPage;
import com.example.pages.RequestLoanPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RequestLoanNegativeTest extends BaseTest {

    @Test
    public void validLoanTest() {

        LoginPage loginPage = new LoginPage(driver);
        AccountsOverviewPage accountsPage =
                loginPage.loginHappyPath();

        RequestLoanPage loanPage =
                new RequestLoanPage(driver);

        loanPage.goToRequestLoan();

        loanPage.requestLoan(
                "1000",
                "100",
                0
        );

        Assert.assertTrue(
                loanPage.isLoanApproved(),
                "Loan was not approved"
        );
    }

    @Test
    public void emptyAmountTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginHappyPath();

        RequestLoanPage loanPage =
                new RequestLoanPage(driver);

        loanPage.goToRequestLoan();

        loanPage.requestLoan(
                "",
                "100",
                0
        );

        Assert.assertTrue(
                loanPage.hasInternalError(),
                "Expected internal error for empty amount"
        );
    }

    @Test
    public void textAmountTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginHappyPath();

        RequestLoanPage loanPage =
                new RequestLoanPage(driver);

        loanPage.goToRequestLoan();

        loanPage.requestLoan(
                "abc",
                "100",
                0
        );

        Assert.assertTrue(
                loanPage.hasInternalError(),
                "Expected internal error for text amount"
        );
    }

    @Test
    public void zeroAmountTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginHappyPath();

        RequestLoanPage loanPage =
                new RequestLoanPage(driver);

        loanPage.goToRequestLoan();

        loanPage.requestLoan(
                "0",
                "0",
                0
        );

        Assert.assertTrue(
                loanPage.hasInternalError(),
                "Expected internal error for zero amount"
        );
    }

    @Test
    public void veryLargeAmountTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginHappyPath();

        RequestLoanPage loanPage =
                new RequestLoanPage(driver);

        loanPage.goToRequestLoan();

        loanPage.requestLoan(
                "999999999",
                "100",
                0
        );

        Assert.assertTrue(
                loanPage.hasLargeAmountError(),
                "Expected large amount error"
        );
    }

    @Test
    public void insufficientDownPaymentTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginHappyPath();

        RequestLoanPage loanPage =
                new RequestLoanPage(driver);

        loanPage.goToRequestLoan();

        loanPage.requestLoan(
                "0",
                "1000",
                0
        );

        Assert.assertTrue(
                loanPage.hasInsufficientFundsError(),
                "Expected insufficient funds error"
        );
    }
    @Test
    public void negativeAmountTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginHappyPath();

        RequestLoanPage loanPage =
                new RequestLoanPage(driver);

        loanPage.goToRequestLoan();

        loanPage.requestLoan(
                "-100",
                "-100",
                0
        );

        Assert.assertTrue(
                loanPage.hasLargeAmountError(),
                "Expected loan amount error for negative values"
        );
    }
}