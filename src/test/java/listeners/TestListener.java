package listeners;

import base.BaseTest;
import utils.ScreenshotUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        BaseTest test = (BaseTest) result.getInstance();
        System.out.println("Test Failed: " +result.getMethod().getMethodName());
        ScreenshotUtil.captureScreenshot(test.getDriver());
    }
}