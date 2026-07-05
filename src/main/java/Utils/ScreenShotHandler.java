package Utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShotHandler {

    public static String takeScreenshot(WebDriver driver, String testName) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String screenshotPath = System.getProperty("user.dir")
                + "/screenshots/" + testName + "_" + timestamp + ".png";

        try {

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.createDirectories(Paths.get(System.getProperty("user.dir"), "screenshots"));

            Files.copy(
                    source.toPath(),
                    Paths.get(screenshotPath)
            );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }

    @Attachment(value = "{testName}", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver, String testName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}