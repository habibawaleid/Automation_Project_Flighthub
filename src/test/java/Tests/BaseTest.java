package Tests;

import Driver.DriverFactory;
import Utils.ScreenShotHandler;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;

    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void precondition(
            @Optional("edge") String browserName,  // Changed from "firefox" to "edge"
            @Optional("false") String headless) {  // Changed from "true" to "false" to see the browser

        driver = DriverFactory.setDriver(
                browserName,
                Boolean.parseBoolean(headless)
        );
        driver.get("https://www.flighthub.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenShotHandler.takeScreenshot(driver, result.getName());
            ScreenShotHandler.attachScreenshot(driver, result.getName());
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully");
        }
    }
}