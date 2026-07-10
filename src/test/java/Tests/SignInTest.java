package Tests;

import Pages.HomePage;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("User Authentication")
@Feature("Account SignIn")
public class SignInTest extends BaseTest {

    private HomePage homePage;
    private Faker faker;

    private final String VALID_EMAIL = "bebohabiba993@gmail.com";
    private final String VALID_PASSWORD = "12345678";

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
        faker = new Faker();
    }

    @Test(priority = 1, description = "LOGIN_001: Successful login with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Verification that valid credentials redirect successfully to the OTP verification screen")
    public void testSuccessfulLogin_LOGIN_001() {
        homePage.openLoginPage();
        homePage.login(VALID_EMAIL, VALID_PASSWORD);

        Assert.assertTrue(homePage.isVerificationCodeDisplayed(), "Verification code input screen was not displayed!");
    }

    @Test(priority = 2, description = "LOGIN_002: Login with empty email")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithEmptyEmail_LOGIN_002() {
        homePage.openLoginPage();
        homePage.clickContinueAfterEmail();

        Assert.assertEquals(homePage.getEmailErrorMessage(), "Please provide your email address");
    }

    @Test(priority = 3, description = "LOGIN_003: Login with invalid email format")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithInvalidEmail_LOGIN_003() {
        homePage.openLoginPage();
        homePage.enterEmail("habiba.com");
        homePage.clickContinueAfterEmail();

        Assert.assertFalse(homePage.isLoginPasswordFieldDisplayed(), "System proceeded with an invalid email format!");
    }

    @Test(priority = 4, description = "LOGIN_004: Login with unregistered email")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithUnregisteredEmail_LOGIN_004() {
        String randomEmail = faker.internet().safeEmailAddress();
        homePage.openLoginPage();
        homePage.enterEmailAndContinue(randomEmail);

        Assert.assertTrue(homePage.isRegistrationFormDisplayed(), "System didn't show signup fields for an unregistered email!");
    }

    @Test(priority = 5, description = "LOGIN_005: Login with empty password")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithEmptyPassword_LOGIN_005() {
        homePage.openLoginPage();
        homePage.enterEmailAndContinue(VALID_EMAIL);
        homePage.clickContinueAfterPassword();

        Assert.assertTrue(homePage.isErrorPassLogDisplayed(), "Error message was not displayed for an empty password field!");
    }

    @Test(priority = 6, description = "LOGIN_006: Login with incorrect password")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithIncorrectPassword_LOGIN_006() {
        homePage.openLoginPage();
        homePage.login(VALID_EMAIL, "Wrong@123");

        Assert.assertTrue(homePage.isErrorPassLogDisplayed(), "Error message was not displayed for an incorrect password!");
    }

    @Test(priority = 7, description = "LOGIN_007: Verification code textbox is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testVerificationCodeTextboxIsDisplayed_LOGIN_007() {
        homePage.openLoginPage();
        homePage.login(VALID_EMAIL, VALID_PASSWORD);

        Assert.assertTrue(homePage.isVerificationCodeDisplayed(), "Verification code text box was not displayed!");
    }

    @Test(priority = 8, description = "LOGIN_008: Verification code textbox is enabled")
    @Severity(SeverityLevel.NORMAL)
    public void testVerificationCodeTextboxIsEnabled_LOGIN_008() {
        homePage.openLoginPage();
        homePage.login(VALID_EMAIL, VALID_PASSWORD);

        Assert.assertTrue(homePage.isVerificationCodeEnabled(), "Verification code text box was disabled!");
    }

    @Test(priority = 9, description = "LOGIN_009: User cannot reach verification screen with wrong credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testCannotReachVerificationWithWrongCredentials_LOGIN_009() {
        homePage.openLoginPage();
        homePage.login(VALID_EMAIL, "wrongPassword123!");

        Assert.assertFalse(homePage.isVerificationCodeDisplayed(), "User was wrongly redirected to the verification code page!");
    }

    @Test(priority = 10, description = "LOGIN_010: Enter invalid verification code and click Sign In")
    @Severity(SeverityLevel.NORMAL)
    public void testInvalidVerificationCode_LOGIN_010() {
        homePage.openLoginPage();
        homePage.login(VALID_EMAIL, VALID_PASSWORD);
        homePage.enterVerificationCode("123456");
        homePage.clickVerifySignIn();

        Assert.assertTrue(homePage.isErrorMessageDisplayed(), "No error message displayed for an invalid OTP code!");
    }

    @Test(priority = 11, description = "LOGIN_011: Verify user remains on verification page after invalid OTP")
    @Severity(SeverityLevel.NORMAL)
    public void testRemainOnVerificationPageAfterInvalidOTP_LOGIN_011() {
        homePage.openLoginPage();
        homePage.login(VALID_EMAIL, VALID_PASSWORD);
        homePage.enterVerificationCode("123456");
        homePage.clickVerifySignIn();

        Assert.assertTrue(homePage.isVerificationCodeDisplayed(), "User was navigated away from the OTP verification screen after entering an invalid OTP!");
    }

    @Test(priority = 12, description = "LOGIN_012: Verify generic error message appears when login fails")
    @Severity(SeverityLevel.NORMAL)
    public void testGenericErrorMessageOnFailure_LOGIN_012() {
        homePage.openLoginPage();
        homePage.login(VALID_EMAIL, "invalidPassWord");

        Assert.assertTrue(homePage.isErrorMessageDisplayed(), "Generic error message did not appear!");
    }
}