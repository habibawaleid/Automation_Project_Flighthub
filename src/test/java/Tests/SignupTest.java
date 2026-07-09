""package Tests;

import Pages.HomePage;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

@Epic("User Registration")
@Feature("Account Signup")
public class SignupTest extends BaseTest {

    private HomePage homePage;
    private Faker faker;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver, new WebDriverWait(driver, Duration.ofSeconds(10)));
        faker = new Faker();
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test(priority = 1, description = "REG_AUTO_001: Successful Registration")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Happy Path: User registers successfully with dynamic data and reaches OTP screen")
    public void testSuccessfulRegistration_REG_AUTO_001() {
        String randomEmail = faker.internet().safeEmailAddress();
        String password = "Pass" + faker.internet().password(6, 10, true, true);

        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(randomEmail);
        homePage.fillRegistrationForm(faker.name().firstName(), faker.name().lastName(), password, password);
        homePage.reEnterPasswordAndSubmit(password);

        Assert.assertTrue(homePage.isVerificationCodeDisplayed(), "Verification code input screen was not displayed!");
    }

    @Test(priority = 2, description = "REG_AUTO_002: Empty Email Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Leaving the email field empty triggers validation message")
    public void testEmptyEmail_REG_AUTO_002() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue("");

        Assert.assertEquals(homePage.getEmailErrorMessage(), "Please provide your email address");
    }

    @Test(priority = 3, description = "REG_AUTO_003: Invalid Email format Validation")
    @Severity(SeverityLevel.NORMAL)
    @Story("Entering an invalid formatted email displays error message")
    public void testInvalidEmail_REG_AUTO_003() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue("invalidEmailFormat"); // إيميل غير صالح

        Assert.assertFalse(homePage.isFirstNameFieldDisplayed(),
                "System allowed the registration form to display (First Name field appeared) even with an invalid email format!");
    }

    @Test(priority = 4, description = "REG_AUTO_004: All Fields Empty Validation")
    @Severity(SeverityLevel.NORMAL)
    @Story("Submitting an empty registration form displays missing fields error")
    public void testAllFieldsEmpty_REG_AUTO_004() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        homePage.fillRegistrationForm("", "", "", "");

        Assert.assertEquals(homePage.getMissingFieldError(), "Please fill in all the fields");
    }


    @Test(priority = 5, description = "REG_AUTO_005: Empty First Name Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Story("Leaving First Name blank while others are filled triggers fields validation error")
    public void testEmptyFirstName_REG_AUTO_005() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        String validPass = "ValidPass123!";
        homePage.fillRegistrationForm("", faker.name().lastName(), validPass, validPass);
        Assert.assertEquals(homePage.getMissingFieldError(), "Please fill in all the fields");
    }

    @Test(priority = 6, description = "REG_AUTO_006: Empty Last Name Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Story("Leaving Last Name blank triggers fields validation error")
    public void testEmptyLastName_REG_AUTO_006() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        String validPass = "ValidPass123!";
        homePage.fillRegistrationForm(faker.name().firstName(), "", validPass, validPass);

        Assert.assertEquals(homePage.getMissingFieldError(), "Please fill in all the fields");
    }

    @Test(priority = 7, description = "REG_AUTO_007: Empty Password Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Story("Leaving password blank triggers validation error")
    public void testEmptyPassword_REG_AUTO_007() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        homePage.fillRegistrationForm(faker.name().firstName(), faker.name().lastName(), "", "SomePass123!");
        Assert.assertEquals(homePage.getMissingFieldError(), "Please fill in all the fields");
    }

    @Test(priority = 8, description = "REG_AUTO_008: Empty Confirm Password Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Story("Leaving confirmation password blank triggers validation error")
    public void testEmptyConfirmPassword_REG_AUTO_008() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        homePage.fillRegistrationForm(faker.name().firstName(), faker.name().lastName(), "SomePass123!", "");
        Assert.assertEquals(homePage.getMissingFieldError(), "Please fill in all the fields");
    }


    @Test(priority = 9, description = "REG_AUTO_009: Password Under 8 Characters")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Entering a password less than 8 characters results in length error message")
    public void testPasswordTooShort_REG_AUTO_009() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        homePage.fillRegistrationForm(faker.name().firstName(), faker.name().lastName(), "Short1!", "Short1!");
        Assert.assertEquals(homePage.getPasswordError(), "Your password must be at least 8 characters long");
    }

    @Test(priority = 10, description = "REG_AUTO_010: Password Mismatch Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Providing different values in Password and Confirm Password displays mismatch error")
    public void testPasswordMismatch_REG_AUTO_010() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        homePage.fillRegistrationForm(faker.name().firstName(), faker.name().lastName(), "StrongPass1!", "DifferentPass2!");
        Assert.assertEquals(homePage.getPasswordMismatchError(), "Your passwords do not match");
    }

    @Test(priority = 11, description = "REG_AUTO_011: Attempt Registration with an Existing Registered Email")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Using an already registered email should prevent signup progress and show error or redirect")
    public void testExistingEmailRegistration_REG_AUTO_011() {
        String existingEmail = "existing_tester_123@gmail.com";
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(existingEmail);}

    @Test(priority = 12, description = "REG_AUTO_012: Password with Exactly 8 Characters")
    @Severity(SeverityLevel.NORMAL)
    @Story("Boundary Analysis: Verification of password length exactly equal to 8 characters")
    public void testPasswordExactlyEightChars_REG_AUTO_012() {
        String exactEightPassword = "Exact8p!"; // 8 chars
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        homePage.fillRegistrationForm(faker.name().firstName(), faker.name().lastName(), exactEightPassword, exactEightPassword);
        homePage.reEnterPasswordAndSubmit(exactEightPassword);
        Assert.assertTrue(homePage.isVerificationCodeDisplayed(), "Failed to proceed with exactly 8 character password!");
    }


    @Test(priority = 13, description = "REG_AUTO_013: Password with More Than 8 Characters")
    @Severity(SeverityLevel.NORMAL)
    @Story("Boundary Analysis: Verification of password length greater than 8 characters")
    public void testPasswordMoreThanEightChars_REG_AUTO_013() {
        String longPassword = "SuperStrongPassword123#"; // >8 chars
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        homePage.fillRegistrationForm(faker.name().firstName(), faker.name().lastName(), longPassword, longPassword);
        homePage.reEnterPasswordAndSubmit(longPassword);
        Assert.assertTrue(homePage.isVerificationCodeDisplayed(), "Failed to proceed with more than 8 character password!");
    }

    @Test(priority = 14, description = "REG_AUTO_014: Continue After Valid Email Displays Registration Form")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Entering a valid email transitions the user to the basic registration details form")
    public void testContinueAfterValidEmail_REG_AUTO_014() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        Assert.assertTrue(homePage.isRegistrationFormDisplayed(), "Registration form was not displayed after submitting email!");
    }

    @Test(priority = 15, description = "REG_AUTO_015: Verification Code Displayed")
    @Severity(SeverityLevel.NORMAL)
    @Story("Ensure the verification code field is visible upon successful detail entry")
    public void testVerificationCodeDisplayed_REG_AUTO_015() {
        String randomEmail = faker.internet().safeEmailAddress();
        String password = "Pass" + faker.internet().password(6, 10, true, true);
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(randomEmail);
        homePage.fillRegistrationForm(faker.name().firstName(), faker.name().lastName(), password, password);
        homePage.reEnterPasswordAndSubmit(password);
        Assert.assertTrue(homePage.isVerificationCodeDisplayed(), "Verification code input field is not displayed!");
    }

    @Test(priority = 16, description = "REG_AUTO_016: Verification Code Enabled")
    @Severity(SeverityLevel.NORMAL)
    @Story("Ensure the verification code field is enabled for user input")
    public void testVerificationCodeEnabled_REG_AUTO_016() {
        String randomEmail = faker.internet().safeEmailAddress();
        String password = "Pass" + faker.internet().password(6, 10, true, true);
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(randomEmail);
        homePage.fillRegistrationForm(faker.name().firstName(), faker.name().lastName(), password, password);
        homePage.reEnterPasswordAndSubmit(password);

        Assert.assertTrue(homePage.isVerificationCodeEnabled(), "Verification code input field is disabled!");
    }
//h

    @Test(priority = 17, description = "REG_AUTO_017: Invalid Data Cannot Reach Verification Screen")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Negative security check: User cannot bypass errors to reach the OTP verification step")
    public void testInvalidDataCannotReachVerification_REG_AUTO_017() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        homePage.fillRegistrationForm("", "", "123", "321");
        Assert.assertFalse(homePage.isVerificationCodeDisplayed(), "System bypassed validation and displayed OTP screen!");
    }

    @Test(priority = 18, description = "REG_AUTO_018: Password Field Character Masking")
    @Severity(SeverityLevel.NORMAL)
    @Story("Ensuring the password input field masks input characters as 'password' type")
    public void testPasswordMasking_REG_AUTO_018() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        Assert.assertEquals(homePage.getPasswordFieldType(), "password", "Password input characters are visible on screen!");
    }

    @Test(priority = 19, description = "REG_AUTO_019: Confirm Password Field Character Masking")
    @Severity(SeverityLevel.NORMAL)
    @Story("Ensuring the confirm password input field masks input characters as 'password' type")
    public void testConfirmPasswordMasking_REG_AUTO_019() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue(faker.internet().safeEmailAddress());
        Assert.assertEquals(homePage.getConfirmPasswordFieldType(), "password", "Confirm Password input characters are visible on screen!");
    }

    @Test(priority = 20, description = "REG_AUTO_020: Generic Error Message Validation")
    @Severity(SeverityLevel.NORMAL)
    @Story("Verify that general error elements are displayed when general system or input errors occur")
    public void testGenericErrorMessage_REG_AUTO_020() {
        homePage.clickSignInSignUp();
        homePage.selectEmailOption();
        homePage.enterEmailAndContinue("");
        Assert.assertEquals(homePage.getEmailErrorMessage(), "Please provide your email address", "Generic error message element was not displayed!");
    }

    }""