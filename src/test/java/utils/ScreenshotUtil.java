package utils;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {


    // ذخیره PNG داخل target/screenshots
    public static String saveScreenshot(
            WebDriver driver,
            String testName) {


        TakesScreenshot ts =
                (TakesScreenshot) driver;


        File source =
                ts.getScreenshotAs(OutputType.FILE);



        File folder =
                new File(
                        System.getProperty("user.dir")
                                + "/target/screenshots"
                );


        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName =
                testName + "_" + System.currentTimeMillis() + ".png";


        File destination = new File(
                System.getProperty("user.dir")
                        + "/screenshots/"
                        + fileName);
        try {

            FileUtils.copyFile(
                    source,
                    destination
            );


        } catch (IOException e) {

            e.printStackTrace();

        }


        return destination.getAbsolutePath();
    }
    // Attach کردن به Allure
    @Attachment(
            value = "Failure Screenshot",
            type = "image/png"
    )
    public static byte[] attachScreenshot(
            WebDriver driver) {


        return ((TakesScreenshot) driver)
                .getScreenshotAs(
                        OutputType.BYTES
                );
    }

}