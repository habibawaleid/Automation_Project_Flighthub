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
            @Optional("firefox") String browserName,
            @Optional("true") String headless) {

        driver = DriverFactory.setDriver(
                browserName,
                Boolean.parseBoolean(headless)
        );
        driver.get("https://www.flighthub.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            ScreenShotHandler.takeScreenshot(driver, result.getName());
            ScreenShotHandler.attachScreenshot(driver, result.getName());
            System.out.println("Screenshot captured for failed test");
        }

        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully");
        }
    }
}