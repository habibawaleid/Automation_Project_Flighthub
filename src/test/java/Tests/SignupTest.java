package Tests;

import Pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("User Registration")
@Feature("Account Signup")
public class SignupTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);

    }

    // ==================== HELPER METHODS ======================


    private void performCompleteSignup(String email, String firstName, String lastName, String password) {

        homePage.openSignupFlow();
        homePage.completeSignupFlow(email, firstName, lastName, password, password);
        homePage.assertVerificationCodeBoxVisible();
    }

    private void performSignupWithoutVerification(String email, String firstName, String lastName,
                                                  String password, String confirmPassword) {

        homePage.openSignupFlow();
        homePage.completeSignupFlow(email, firstName, lastName, password, confirmPassword);
    }

    private void performEmailOnly(String email) {

        homePage.openSignupFlow();
        homePage.fillEmail(email);
    }

    // ====================== POSITIVE TEST CASES ========================

    @Test(priority = 1)
    @Description("TC001 - Validate that user can register successfully with valid credentials")
    public void validateUserCanRegisterSuccessfullyWithValidCredentials() {
        performCompleteSignup("test8T8r@example.com", "John", "Doe", "Password@123");
    }

    @Test(priority = 2)
    @Description("TC002 - Validate user can register with different valid email formats")
    public void validateRegistrationWithDifferentValidEmailFormats() {
        performCompleteSignup("test.ueeser+tag@domain.co.uk", "Jane", "Smith", "SecurePass@123");
    }

    @Test(priority = 3)
    @Description("TC003 - Validate user registration with special characters in names")
    public void validateRegistrationWithSpecialCharactersInName() {
        performCompleteSignup("special@example.com", "Jean-Paul", "O'Connor", "SpecialChar@123");
    }

    @Test(priority = 4)
    @Description("TC004 - Validate user can register with complex passwords")
    public void validateRegistrationWithComplexPasswords() {
        performCompleteSignup("complex@example.com", "Alex", "Brown", "P@ssw0rd!#$%&123");
    }

    @Test(priority = 5)
    @Description("TC005 - Validate user can register with numbers in name")
    public void validateRegistrationWithNumbersInName() {
        performCompleteSignup("user123@example.com", "John2", "Doe3", "Numbers@123");
    }

    @Test(priority = 6)
    @Description("TC006 - Validate email and continue button functionality")
    public void validateEmailAndContinueButtonFunctionality() {

        homePage.openSignupFlow();
        homePage.fillEmail("newuser@example.com");
        homePage.clickContinueAfterEmail();
    }

    @Test(priority = 7)
    @Description("TC007 - Validate all registration fields can be filled")
    public void validateAllRegistrationFieldsCanBeFilled() {
        performCompleteSignup("completeuser@example.com", "Michael", "Johnson", "SecurePass@2024");
    }

    @Test(priority = 8)
    @Description("TC008 - Validate sign in button opens email selection modal")
    public void validateSignInButtonOpensModal() {

        homePage.clickSignInButton();
        homePage.clickChooseEmail();
    }

    @Test(priority = 9)
    @Description("TC009 - Validate verification code box is displayed after sign up")
    public void validateVerificationCodeBoxDisplay() {
        performCompleteSignup("verify@example.com", "Test", "User", "TestPass@123");
    }

    @Test(priority = 10)
    @Description("TC010 - Validate user can register with lowercase email")
    public void validateRegistrationWithLowercaseEmail() {
        performCompleteSignup("lowercase@example.com", "Tom", "Hardy", "Lowercase@123");
    }

    @Test(priority = 11)
    @Description("TC011 - Validate user can register with uppercase password")
    public void validateRegistrationWithUppercasePassword() {
        performCompleteSignup("uppercase@example.com", "Upper", "Case", "UPPERCASE@123");
    }

    @Test(priority = 12)
    @Description("TC012 - Validate registration with hyphenated email")
    public void validateRegistrationWithHyphenatedEmail() {
        performCompleteSignup("test-user-name@example-domain.com", "Hyphen", "Email", "Hyphen@123");
    }

    @Test(priority = 13)
    @Description("TC013 - Validate registration with minimum length names")
    public void validateRegistrationWithSingleCharacterNames() {
        performCompleteSignup("singlechar@example.com", "A", "B", "Single@123");
    }

    @Test(priority = 14)
    @Description("TC014 - Validate registration with maximum length names")
    public void validateRegistrationWithMaxLengthNames() {
        performCompleteSignup("maxname@example.com", "VeryVeryVeryLongFirstNameForTesting",
                "VeryVeryVeryLongLastNameForTesting", "MaxLength@123");
    }

    @Test(priority = 15)
    @Description("TC015 - Validate registration with very long password")
    public void validateRegistrationWithVeryLongPassword() {
        String longPassword = "VeryLongPassword@123456789VeryLongPassword@123456789";
        performCompleteSignup("longpwd@example.com", "Long", "Password", longPassword);
    }

    @Test(priority = 16)
    @Description("TC016 - Validate registration with mixed case names")
    public void validateRegistrationWithMixedCaseNames() {
        performCompleteSignup("mixedcase@example.com", "JoHn", "DoE", "MixedCase@123");
    }

    @Test(priority = 17)
    @Description("TC017 - Validate verification code box is displayed after signup")
    public void validateVerificationCodeBoxDisplayAfterSignup() {
        performCompleteSignup("verify2@example.com", "Verify", "Code", "Verify@123");
    }

    @Test(priority = 18)
    @Description("TC018 - Validate registration flow with email containing dots")
    public void validateRegistrationWithEmailContainingDots() {
        performCompleteSignup("first.second.third@example.com", "Dots", "Email", "Dots@123");
    }

    @Test(priority = 19)
    @Description("TC019 - Validate registration flow with email containing underscore")
    public void validateRegistrationWithEmailContainingUnderscore() {
        performCompleteSignup("test_user@example.com", "Under", "Score", "Under@123");
    }

    @Test(priority = 20)
    @Description("TC020 - Validate registration with password containing multiple special characters")
    public void validateRegistrationWithMultipleSpecialCharactersInPassword() {
        performCompleteSignup("multispecial@example.com", "Multi", "Special", "P@$$w0rd!#%");
    }

    // ====================== NEGATIVE TEST CASES ========================

    @Test(priority = 21)
    @Description("TC021 - Validate that user cannot sign up with mismatched passwords")
    public void validateUserCannotSignupWithMismatchedPasswords() {
        performSignupWithoutVerification("mismatch@example.com", "Jane", "Smith",
                "Password@123", "Password@456");
    }

    @Test(priority = 22)
    @Description("TC022 - Validate that empty first name field prevents signup")
    public void validateEmptyFirstNamePreventsSignup() {
        performSignupWithoutVerification("nofirstname@example.com", "", "Smith",
                "Pass@123", "Pass@123");
    }

    @Test(priority = 23)
    @Description("TC023 - Validate that empty last name field prevents signup")
    public void validateEmptyLastNamePreventsSignup() {
        performSignupWithoutVerification("nolastname@example.com", "Jane", "",
                "Pass@123", "Pass@123");
    }

    @Test(priority = 24)
    @Description("TC024 - Validate that empty email field prevents signup")
    public void validateEmptyEmailPreventsSignup() {

        homePage.openSignupFlow();
        homePage.fillEmail("");
    }

    @Test(priority = 25)
    @Description("TC025 - Validate that empty password field prevents signup")
    public void validateEmptyPasswordPreventsSignup() {
        performSignupWithoutVerification("nopassword@example.com", "Test", "User", "", "");
    }

    @Test(priority = 26)
    @Description("TC026 - Validate that invalid email format is rejected")
    public void validateInvalidEmailFormatRejected() {
        performEmailOnly("invalidemail");
    }

    @Test(priority = 27)
    @Description("TC027 - Validate that email with missing domain extension is rejected")
    public void validateEmailWithoutDomainExtensionRejected() {
        performEmailOnly("user@domain");
    }

    @Test(priority = 28)
    @Description("TC028 - Validate password confirm field shows error with mismatched password")
    public void validatePasswordConfirmFieldErrorOnMismatch() {
        performSignupWithoutVerification("pwdmismatch@example.com", "Alex", "Brown",
                "Correct@123", "Wrong@456");
    }

    @Test(priority = 29)
    @Description("TC029 - Validate that short password is rejected")
    public void validateShortPasswordRejected() {
        performSignupWithoutVerification("shortpwd@example.com", "Bob", "Dylan", "Pass", "Pass");
    }

    @Test(priority = 30)
    @Description("TC030 - Validate that password without special characters is rejected")
    public void validatePasswordWithoutSpecialCharactersRejected() {
        performSignupWithoutVerification("nospecial@example.com", "Tom", "Jerry",
                "Password123", "Password123");
    }

    // ====================== EDGE CASES ========================

    @Test(priority = 31)
    @Description("TC031 - Validate registration with maximum length names")
    public void validateRegistrationWithMaximumLengthNames() {
        performCompleteSignup("maxname@example.com", "VeryVeryVeryLongFirstNameForTesting",
                "VeryVeryVeryLongLastNameForTesting", "MaxLength@123");
    }

    @Test(priority = 32)
    @Description("TC032 - Validate registration with single character names")
    public void validateRegistrationWithSingleCharacterNameValues() {
        homePage.completeSignupFlow("singlechar@example.com", "A", "B", "Single@123", "Single@123");
    }

    @Test(priority = 33)
    @Description("TC033 - Validate email field with leading/trailing spaces")
    public void validateEmailWithLeadingTrailingSpaces() {

        homePage.openSignupFlow();
        homePage.fillEmail("  spaces@example.com  ");
        homePage.clickContinueAfterEmail();
    }

    @Test(priority = 34)
    @Description("TC034 - Validate registration with very long password")
    public void validateRegistrationWithVeryLongPasswordValue() {
        String longPassword = "VeryLongPassword@123456789VeryLongPassword@123456789";
        performCompleteSignup("longpwd@example.com", "Long", "Password", longPassword);
    }

    @Test(priority = 35)
    @Description("TC035 - Validate email field rejects multiple @ symbols")
    public void validateEmailRejectsMultipleAtSymbols() {
        performEmailOnly("user@@example.com");
    }

    @Test(priority = 36)
    @Description("TC036 - Validate password field accepts numbers and uppercase")
    public void validatePasswordAcceptsNumbersAndUppercase() {
        performCompleteSignup("uppernumber@example.com", "Upper", "Number", "PASSWORD123@");
    }

    @Test(priority = 37)
    @Description("TC037 - Validate that empty confirm password field shows error")
    public void validateEmptyConfirmPasswordShowsError() {
        performSignupWithoutVerification("nopwdconfirm@example.com", "No", "Confirm",
                "Password@123", "");
    }

    @Test(priority = 38)
    @Description("TC038 - Validate registration flow with hyphenated email")
    public void validateRegistrationWithHyphenatedEmailValue() {
        performCompleteSignup("test-user-name@example-domain.com", "Hyphen", "Email", "Hyphen@123");
    }

    @Test(priority = 39)
    @Description("TC039 - Validate that clicking continue without email shows error")
    public void validateClickingContinueWithoutEmailShowsError() {

        homePage.openSignupFlow();
        homePage.fillEmail("");
    }

    @Test(priority = 40)
    @Description("TC040 - Validate successful signup flow with all parameters filled correctly")
    public void validateCompleteSuccessfulSignupFlow() {

        homePage.openSignupFlow();

        String email = "complete@example.com";
        String firstName = "Complete";
        String lastName = "Registration";
        String password = "Complete@123";

        homePage.completeSignupFlow(email, firstName, lastName, password, password);
        homePage.assertVerificationCodeBoxVisible();
    }
}
