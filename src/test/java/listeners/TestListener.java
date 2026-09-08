package listeners;

import base.BaseTest;
import utils.ScreenshotUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Map;

public class TestListener implements ITestListener {


    @Override
    public void onTestFailure(ITestResult result) {

        BaseTest test =
                (BaseTest) result.getInstance();


        String testName =
                result.getMethod().getMethodName();


        Object[] parameters =
                result.getParameters();


        if(parameters.length > 0 &&
                parameters[0] instanceof Map) {


            Map<String,Object> data =
                    (Map<String,Object>) parameters[0];


            testName =
                    data.get("testCase").toString();

        }


        // ذخیره عکس داخل فولدر
        ScreenshotUtil.saveScreenshot(
                test.getDriver(),
                testName
        );


        // Attach به Allure
        ScreenshotUtil.attachScreenshot(
                test.getDriver()
        );
    }
}


    //@Override
    /*
    public void onTestFailure(
            ITestResult result) {


        BaseTest test =
                (BaseTest) result.getInstance();



        // ذخیره PNG
        ScreenshotUtil.saveScreenshot(
                test.getDriver(),
                result.getMethod()
                        .getMethodName()
        );



        // Attach به Allure
        ScreenshotUtil.attachScreenshot(
                test.getDriver()
        );

    }*/

