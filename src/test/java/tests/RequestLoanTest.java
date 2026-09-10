package tests;

import base.BaseTest;
import com.example.pages.LoginPage;
import com.example.pages.RequestLoanPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RequestLoanTest extends BaseTest {

    @Test
    public void validLoanTest() {

        LoginPage loginPage =
                new LoginPage(driver);

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
                "Loan should be approved"
        );
    }
}