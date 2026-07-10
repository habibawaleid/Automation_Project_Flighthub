package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ElementAction;
import java.time.Duration;



public class HomePage {

    //======================================== Variables ==============================//

    //======================================== Driver =================================//

    private final WebDriver driver;
    private final WebDriverWait wait;
//h

    //======================================== Constructor ===========================//

    public HomePage(WebDriver driver, WebDriverWait webDriverWait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //========================================== Locators ============================//


    // =================================================================
    private final By signupBottomBtn = By.xpath("//div[@class='sunshine-header-nav fh']//div[contains(text(),'Sign in')]");
    private final By emailChosenOption = By.xpath("//span[normalize-space()='Email']");
    private final By emailTextBox = By.id("login-modal-account-login-email");
    private final By continueAfterEmail = By.xpath("//li[@class='item']//button[@class='login-modal-submit-btn is-main-button login-modal-button'][normalize-space()='Continue']");
    private final By firstNameTextBox = By.id("login-modal-first-name");
    private final By lastNameTextBox = By.id("login-modal-last-name");
    private final By passwordTextBox = By.xpath("//input[@id='login-modal-account-register-password']");
    private final By confirmPasswordTextBox = By.id("login-modal-account-register-confirm-password");
    private final By signupBtn = By.xpath("//button[normalize-space()='Sign up']");
    private final By passwordAccountTextBox = By.id("login-modal-account-login-password");
    private final By continueBtn = By.xpath("//div[@class='item button']//button[@class='login-modal-submit-btn is-main-button login-modal-button'][normalize-space()='Continue']");
    private final By verificationCodeTextBox = By.id("login-modal-account-login-totp");

    // Locators for validation and error messages
    private final By errorMessageEmail = By.xpath("//div[@id='page-email']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Please provide your email address']");
    private final By errorMessageMissedField = By.xpath("//div[@id='page-register']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Please fill in all the fields']");
    private final By errorMessagePassword = By.xpath("//div[@id='page-register']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Your password must be at least 8 characters long']");
    private final By passwordErrorMatched = By.xpath("//div[@id='page-register']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Your passwords do not match']");
    private final By errorMessage = By.xpath("//div[@class='login-modal-feedback-message feedback-error']");
    private final By verifySignInBtn = By.xpath("//button[@class='login-modal-submit-btn is-main-button login-modal-button'][normalize-space()='Sign in']");
    private final By errorInvalidPASStoEmail = By.xpath("//div[@id='page-auth']//div[@class='login-modal-feedback-message feedback-error'][contains(text(),'Error, unable to reach the server. Please try again.')]");

    // Validation & Error Messages Elements
    /*private final By errorMessageEmail = By.xpath("//div[@id='page-email']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Please provide your email address']");
    private final By errorMessageMissedField  = By.xpath("//div[@id='page-register']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Please fill in all the fields']");
    private final By errorMessagePassword = By.xpath("//div[@id='page-register']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Your password must be at least 8 characters long']");
    private final By passwordErrorMatched = By.xpath("//div[@id='page-register']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Your passwords do not match']");
    private final By errorMessage = By.xpath("//div[@class='login-modal-feedback-message feedback-error']");
    */

    // Language & Currency Locators (in the order provided)
    private final By languageCurrencySelection = By.xpath("//div[@class='sunshine-header-nav fh']//span[contains(text(),'CAD')]");
    private final By languageSelect = By.xpath("//div[@class='ReactModalPortal']//div[3]");
    private final By englishSelectBtn = By.xpath("//button[@class='_option_kuh6b_143 _selected_kuh6b_167']//div[@class='_dropdownOptionContent_kuh6b_170']");
    private final By frenchSelectBtn = By.xpath("//button[@aria-selected='false']");
    private final By currencySelectBtn = By.xpath("//div[@class='_selectorInput_4e6ym_58 _disabled_4e6ym_73']");
    private final By saveBtn = By.xpath("//button[normalize-space()='Save']");

    // ===== Navigation Buttons =====
    private final By myTripsBtn = By.xpath("//div[@class='sunshine-header-nav fh']//div//a[@class='navbar-link'][normalize-space()='My Trips']");
    private final By supportBtn = By.xpath("//div[@class='sunshine-header-nav fh']//div//a[@class='navbar-link'][normalize-space()='Support']");

    // ===== Social Media Icons =====
    private final By facebookIcon = By.xpath("//div[@class='footer-header-wrapper']//li[1]");
    private final By instagramIcon = By.xpath("//a[@href='https://www.instagram.com/flighthub/']");
    private final By youtubeIcon = By.xpath("//a[@href='https://www.youtube.com/channel/UC']");
    private final By linkedinIcon = By.xpath("//a[@href='https://www.linkedin.com/company/flighthub/']//*[name()='svg']");

    // ===== Footer Links - Company =====
    private final By donationsLink = By.xpath("//a[normalize-space()='Donations']");
    private final By newsletterLink = By.xpath("//a[normalize-space()='Newsletter']");

    // ===== Footer Links - Popular Cities & Routes =====
    private final By montrealCityLink = By.xpath("//a[normalize-space()='Montreal']");
    private final By montrealToLasVegasLink = By.xpath("//a[normalize-space()='Montreal to Las Vegas']");

    // ===== Footer Links - Legal & Policies =====
    private final By privacyPolicyLink = By.xpath("//div[@class='footer-footer-links ']//a[normalize-space()='Privacy Policy']");
    private final By cookiePolicyLink = By.xpath("//a[normalize-space()='Cookie Policy']");
    private final By cookiePreferencesLink = By.xpath("//a[normalize-space()='Cookie Preferences']");
    private final By termsAndConditionsLink = By.xpath("//div[contains(@class,'footer-footer-links')]//a[normalize-space()='Terms & Conditions']");

    // ===== Logo =====
    private final By flightHubLogo = By.id("top-logo");

    // ===== Flights and Hotels Search Form - Round Trip =====
    private final By flightsAndHotelsBtn = By.xpath("//div[contains(@class, 'product-type-selector-item__travel-packages')]");
    private final By roundTripBtn = By.xpath("//div[contains(@class, 'travel-packages')]//div[contains(@class, 'trip-type-roundtrip')]");
    private final By roundTripLeavingFromInput = By.xpath("//div[contains(@class, 'travel-packages')]//div[contains(@class, 'departure')]//input");    private final By roundTripGoingToInput = By.xpath("//div[contains(@class, 'travel-packages')]//div[contains(@class, 'destination')]//input");
    private final By roundTripDateInput = By.xpath("//div[@id='seg0_date']");
    private final By roundTripSearchBtn = By.xpath("//div[@class='home-search-form-submit search-form-submit flights fh']");
    private final By firstAutoCompleteOption = By.xpath("(//div[contains(@class, 'airport-autocomplete-list-item')])[1]");
    private final By oneWayBtn = By.xpath("//div[contains(@class, 'travel-packages')]//div[contains(@class, 'trip-type-oneway')]");
    private final By oneWayLeavingFromInput = By.xpath("//div[contains(@class, 'departure')]//input");
    private final By oneWayGoingToInput = By.xpath("//div[@class='home-search-form-input destination ']");
    private final By oneWayDateInput = By.xpath("//div[@id='seg0_date']");
    private final By oneWaySearchBtn = By.xpath("//div[contains(@class,'home-search-form-submit search-form-submit flights fh')]");
    private final By cabinClassDropdown = By.xpath("//div[contains(@class, 'travel-packages')]//div[contains(text(), 'Economy') or contains(text(), 'Business') or contains(text(), 'First')]");    private final By premiumEconomyOption = By.xpath("//div[contains(text(),'Premium Economy')]");
    private final By travellersInput = By.xpath("//div[contains(@class,'passenger-room-input-wrapper')]");
    private final By addAdultBtn = By.xpath("//div[contains(@class, 'passenger-type-adult')]//button[contains(@class, 'plus')]");
    private final By childAgeDropdown = By.xpath("//div[contains(@class, 'guest-age-selector-container')]//div[contains(@class, 'age-selector-content')]");
    private final By childAge3Years = By.xpath("//div[contains(@class, 'guest-age-selector-dropdown-list')]//div[contains(normalize-space(), '3 years old')]");
    private final By businessClassOption = By.xpath("//div[text()='Business Class']");
    private final By firstClassOption = By.xpath("//div[text()='First Class']");
    private final By counterWrappers = By.xpath("//div[contains(@class,'passenger-room-list')]//div[contains(@class,'passenger-room-counter-wrapper')]");
    private final By adultPlusBtn  = By.xpath("(//div[contains(@class,'passenger-room-counter-wrapper')])[1]//div[contains(concat(' ', normalize-space(@class), ' '), ' plus ')]");
    private final By adultMinusBtn = By.xpath("(//div[contains(@class, 'passenger-room-counter')])[1]//div[contains(@class, 'plus-minus')][1]");
    private final By childPlusBtn  = By.xpath("(//div[contains(@class,'passenger-room-counter-wrapper')])[2]//div[contains(concat(' ', normalize-space(@class), ' '), ' plus ')]");
    private final By infantOnSeatPlusBtn = By.xpath("(//div[contains(@class,'passenger-room-counter-wrapper')])[3]//div[contains(concat(' ', normalize-space(@class), ' '), ' plus ')]");
    private final By infantLapPlusBtn = By.xpath("(//div[contains(@class,'passenger-room-counter-wrapper')])[4]//div[contains(concat(' ', normalize-space(@class), ' '), ' plus ')]");
    private final By travellersSummaryText = By.xpath("//div[contains(@class, 'travel-packages')]//div[contains(@class, 'passenger-room-input-wrapper')]//input");
    private final By applyPassengerBtn = By.xpath("//div[contains(@class, 'travel-packages')]//button[contains(@class, 'apply-button')]");
    private final By startDate = By.xpath("(//div[contains(@class, 'date-picker')]//button[contains(@class, 'rdrDay') and not(contains(@class, 'rdrDayDisabled')) and not(contains(@class, 'rdrDayPassive'))])[1]");
    private final By endDate = By.xpath("(//div[contains(@class, 'date-picker')]//button[contains(@class, 'rdrDay') and not(contains(@class, 'rdrDayDisabled')) and not(contains(@class, 'rdrDayPassive'))])[14]");
    private final By clearDatesBtn = By.xpath("//div[contains(@class, 'date-picker')]//div[contains(@class, 'clear-dates-btn')]");
    private final By setDatesBtn = By.xpath("//div[contains(@class, 'date-picker')]//button[contains(@class, 'set-dates-btn')]");
    private final By passengerErrorMsg = By.xpath("//div[contains(@class, 'passenger-room-selector-error')]");
    private final By segmentWrapper = By.xpath("//div[contains(@class,'segment-wrapper')]");

    // ===== Cars Search Form =====
    private final By carsSelector = By.xpath("//div[contains(@class,'product-type-selector-item active')]");
    private final By carPickupDropOffInput = By.xpath("//div[contains(@class,'home-search-form-left')]//div[contains(@class,'home-search-form-input')]");
    private final By carPickupDateInput = By.xpath("//body[1]/div[2]/div[2]/div[1]/section[1]/div[1]/div[2]/form[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]");
    private final By carPickupTimeInput = By.xpath("//body[1]/div[2]/div[2]/div[1]/section[1]/div[1]/div[2]/form[1]/div[2]/div[2]/div[2]/div[1]/div[2]");
    private final By carDropOffDateInput = By.xpath("//body[1]/div[2]/div[2]/div[1]/section[1]/div[1]/div[2]/form[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]");
    private final By carDropOffTimeInput = By.xpath("//body[1]/div[2]/div[2]/div[1]/section[1]/div[1]/div[2]/form[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]");
    private final By carSearchBtn = By.xpath("//div[contains(@class,'home-search-form-submit')]");


    //========================================= Actions ===============================//
//========================================== Action Methods ============================//

    // 1. فتح نافذة التسجيل/الدخول المنبثقة
    public void clickSignInSignUp() {
        ElementAction.click(driver, signupBottomBtn);
    }

    // 2. اختيار الإيميل كطريقة تسجيل/دخول
    public void selectEmailOption() {
        ElementAction.click(driver, emailChosenOption);
    }

    // 3. فتح شاشة تسجيل الدخول المباشرة (دمج الخطوتين السابقتين لسهولة التيست)
    public void openLoginPage() {
        clickSignInSignUp();
        selectEmailOption();
    }

    // 4. كتابة الإيميل فقط
    public void enterEmail(String email) {
        ElementAction.fill(driver, emailTextBox, email, true);
    }

    // 5. الضغط على زر Continue بعد كتابة الإيميل
    public void clickContinueAfterEmail() {
        ElementAction.click(driver, continueAfterEmail);
    }

    // 6. إدخال الإيميل والضغط على متابعة (خطوة مركبة)
    public void enterEmailAndContinue(String email) {
        enterEmail(email);
        clickContinueAfterEmail();
    }

    // 7. ملء نموذج التسجيل والضغط على Sign up
    public void fillRegistrationForm(String firstName, String lastName, String password, String confirmPassword) {
        ElementAction.fill(driver, firstNameTextBox, firstName);
        ElementAction.fill(driver, lastNameTextBox, lastName);
        ElementAction.fill(driver, passwordTextBox, password);
        ElementAction.fill(driver, confirmPasswordTextBox, confirmPassword);
        ElementAction.click(driver, signupBtn);
    }

    // 8. إدخال كلمة المرور المخصصة للـ Login
    public void enterLoginPassword(String password) {
        ElementAction.fill(driver, passwordAccountTextBox, password);
    }

    // 9. الضغط على زر Continue بعد كتابة الباسورد
    public void clickContinueAfterPassword() {
        ElementAction.click(driver, continueBtn);
    }

    // 10. دالة مجمعة لإجراء الـ Login الكامل
    public void login(String email, String password) {
        enterEmailAndContinue(email);
        enterLoginPassword(password);
        clickContinueAfterPassword();
    }

    // 11. إدخال كود التحقق OTP
    public void enterVerificationCode(String code) {
        ElementAction.fill(driver, verificationCodeTextBox, code, true);
    }

    // 12. الضغط على زر تأكيد تسجيل الدخول بالـ OTP
    public void clickVerifySignIn() {
        ElementAction.click(driver, verifySignInBtn);
    }

    //================================== Verification & Getters ==========================//

    public boolean isFirstNameFieldDisplayed() {
        try {
            return !driver.findElements(firstNameTextBox).isEmpty() && driver.findElement(firstNameTextBox).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPasswordFieldDisplayed() {
        try {
            return !driver.findElements(passwordAccountTextBox).isEmpty() && driver.findElement(passwordAccountTextBox).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailValidationMessage() {
        try {
            String validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(emailTextBox)).getAttribute("validationMessage");
            if (validationMessage != null && !validationMessage.isEmpty()) {
                return validationMessage;
            }
        } catch (Exception e) {
            // Fallback to DOM error message
        }
        return getGeneralErrorMessage();
    }

    public String getEmailErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageEmail)).getText();
    }

    public String getMissingFieldError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageMissedField)).getText();
    }

    public String getPasswordError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessagePassword)).getText();
    }

    public String getPasswordMismatchError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorMatched)).getText();
    }

    public String getGeneralErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVerificationCodeDisplayed() {
        try {
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            return longWait.until(ExpectedConditions.visibilityOfElementLocated(verificationCodeTextBox)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVerificationCodeEnabled() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(verificationCodeTextBox)).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRegistrationFormDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(firstNameTextBox)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPasswordFieldType() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextBox)).getAttribute("type");
    }

    public String getConfirmPasswordFieldType() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordTextBox)).getAttribute("type");
    }


    public boolean isErrorPassLogDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorInvalidPASStoEmail)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
