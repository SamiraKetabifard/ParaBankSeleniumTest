package tests;

import base.BaseTest;
import com.example.pages.BillPayPage;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BillPayTest extends BaseTest {

    private void openBillPay() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginHappyPath();

        BillPayPage billPayPage = new BillPayPage(driver);
        billPayPage.goToBillPay();
    }
    @Test
    public void validPaymentTest() {

        openBillPay();

        BillPayPage billPayPage = new BillPayPage(driver);

        billPayPage.payBill(
                "hani",
                "Main Street",
                "Tehran",
                "Tehran",
                "12345",
                "09123456789",
                "123456",
                "123456",
                "100",
                0);

        Assert.assertTrue(
                billPayPage.isPaymentSuccessful(),
                "Bill payment should be successful"
        );
    }


    @Test
    public void emptyAmountTest() {

        openBillPay();

        BillPayPage billPayPage = new BillPayPage(driver);

        billPayPage.payBill(
                "hani",
                "Main Street",
                "Tehran",
                "Tehran",
                "12345",
                "09123456789",
                "123456",
                "123456",
                "",
                0);

        Assert.assertTrue(
                billPayPage.hasAmountEmptyError(),
                "Expected amount empty validation error"
        );
    }
    @Test
    public void textAmountTest() {

        openBillPay();

        BillPayPage billPayPage = new BillPayPage(driver);

        billPayPage.payBill(
                "hani",
                "Main Street",
                "Tehran",
                "Tehran",
                "12345",
                "09123456789",
                "123456",
                "123456",
                "abc",
                0
        );

        Assert.assertFalse(
                billPayPage.isPaymentSuccessful(),
                "Text amount should not be accepted"
        );
    }


    @Test
    public void zeroAmountTest() {

        openBillPay();

        BillPayPage billPayPage = new BillPayPage(driver);

        billPayPage.payBill(
                "hani",
                "Main Street",
                "Tehran",
                "Tehran",
                "12345",
                "09123456789",
                "123456",
                "123456",
                "0",
                0
        );

        Assert.assertFalse(
                billPayPage.isPaymentSuccessful(),
                "Zero amount should be rejected"
        );
    }


    @Test
    public void negativeAmountTest() {

        openBillPay();

        BillPayPage billPayPage = new BillPayPage(driver);

        billPayPage.payBill(
                "hani",
                "Main Street",
                "Tehran",
                "Tehran",
                "12345",
                "09123456789",
                "123456",
                "123456",
                "-100",
                0
        );

        Assert.assertFalse(
                billPayPage.isPaymentSuccessful(),
                "Negative amount should be rejected"
        );
    }


    @Test
    public void veryLargeAmountTest() {

        openBillPay();

        BillPayPage billPayPage = new BillPayPage(driver);

        billPayPage.payBill(
                "hani",
                "Main Street",
                "Tehran",
                "Tehran",
                "12345",
                "09123456789",
                "123456",
                "123456",
                "999999999",
                0
        );

        Assert.assertFalse(
                billPayPage.isPaymentSuccessful(),
                "Very large amount should not be accepted"
        );
    }
}