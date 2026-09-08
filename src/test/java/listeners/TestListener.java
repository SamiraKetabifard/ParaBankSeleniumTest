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
                result.getMethod()
                        .getMethodName();


        Object[] parameters =
                result.getParameters();


        // اگر DataProvider داشت
        if(parameters.length > 0 &&
                parameters[0] instanceof Map) {


            Map<String,Object> data =
                    (Map<String,Object>) parameters[0];


            if(data.containsKey("testCase")) {

                testName =
                        data.get("testCase")
                                .toString();

            }
        }


        System.out.println("Test Failed: " + testName);


        // فقط Attach به Allure
        ScreenshotUtil.attachScreenshot(
                test.getDriver()
        );

    }
}