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

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    //========================================== Locators ============================//


    //========================================== Locators ============================//
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

    // Locators for Validation and Error Messages
    private final By errorMessageEmail = By.xpath("//div[@id='page-email']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Please provide your email address']");
    private final By errorMessageMissedField  = By.xpath("//div[@id='page-register']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Please fill in all the fields']");
    private final By errorMessagePassword = By.xpath("//div[@id='page-register']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Your password must be at least 8 characters long']");
    private final By passwordErrorMatched = By.xpath("//div[@id='page-register']//div[@class='login-modal-feedback-message feedback-error'][normalize-space()='Your passwords do not match']");
    private final By errorMessage = By.xpath("//div[@class='login-modal-feedback-message feedback-error']");

    // Language & Currency Locators (in the order provided)
    private final By languageCurrencySelection = By.xpath("//div[@class='sunshine-header-nav fh']//span[contains(text(),'CAD')]");
    private final By languageSelect= By.xpath("//div[@class='ReactModalPortal']//div[3]");
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
    private final By flightsAndHotelsBtn = By.xpath("//div[@class='product-type-selector-item product-type-selector-item__travel-packages active']");
    private final By roundTripBtn = By.xpath("//div[@class='trip-type-roundtrip active']");
    private final By roundTripLeavingFromInput = By.xpath("//div[@class='home-search-form-input search-form-input departure ']");
    private final By roundTripGoingToInput = By.xpath("//div[@class='home-search-form-input destination ']");
    private final By roundTripDateInput = By.xpath("//div[@id='seg0_date']");
    private final By roundTripSearchBtn = By.xpath("//div[@class='home-search-form-submit search-form-submit flights fh']");

    // ===== Flights and Hotels Search Form - One Way =====
    private final By oneWayBtn = By.xpath("//div[@class='trip-type-oneway']");
    private final By oneWayLeavingFromInput = By.xpath("//div[@class='home-search-form-input search-form-input departure ']");
    private final By oneWayGoingToInput = By.xpath("//div[@class='home-search-form-input destination ']");
    private final By oneWayDateInput = By.xpath("//div[@id='seg0_date']");
    private final By oneWaySearchBtn = By.xpath("//div[contains(@class,'home-search-form-submit search-form-submit flights fh')]");

    // ===== Cars Search Form =====
    private final By carsSelector = By.xpath("//div[contains(@class,'product-type-selector-item active')]");
    private final By carPickupDropOffInput = By.xpath("//div[contains(@class,'home-search-form-left')]//div[contains(@class,'home-search-form-input')]");
    private final By carPickupDateInput = By.xpath("//body[1]/div[2]/div[2]/div[1]/section[1]/div[1]/div[2]/form[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]");
    private final By carPickupTimeInput = By.xpath("//body[1]/div[2]/div[2]/div[1]/section[1]/div[1]/div[2]/form[1]/div[2]/div[2]/div[2]/div[1]/div[2]");
    private final By carDropOffDateInput = By.xpath("//body[1]/div[2]/div[2]/div[1]/section[1]/div[1]/div[2]/form[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]");
    private final By carDropOffTimeInput = By.xpath("//body[1]/div[2]/div[2]/div[1]/section[1]/div[1]/div[2]/form[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]");
    private final By carSearchBtn = By.xpath("//div[contains(@class,'home-search-form-submit')]");

    // 1. الضغط على Sign in / Sign up
    public void clickSignInSignUp() {
        ElementAction.click(driver, signupBottomBtn);
    }

    // 2. اختيار الإيميل كطريقة تسجيل
    public void selectEmailOption() {
        ElementAction.click(driver, emailChosenOption);
    }

    // 3. إدخال الإيميل والضغط على متابعة
    public void enterEmailAndContinue(String email) {
        ElementAction.fill(driver, emailTextBox, email, true);
        ElementAction.click(driver, continueAfterEmail);
    }

    // 4. ملء نموذج التسجيل والضغط على Sign up
    public void fillRegistrationForm(String firstName, String lastName, String password, String confirmPassword) {
        ElementAction.fill(driver, firstNameTextBox, firstName);
        ElementAction.fill(driver, lastNameTextBox, lastName);
        ElementAction.fill(driver, passwordTextBox, password);
        ElementAction.fill(driver, confirmPasswordTextBox, confirmPassword);
        ElementAction.click(driver, signupBtn);
    }

    // 5. إعادة إدخال الباسورد للتحقق النهائي والضغط على Continue
    public void reEnterPasswordAndSubmit(String password) {
        ElementAction.fill(driver, passwordAccountTextBox, password);
        ElementAction.click(driver, continueBtn);
    }

    //================================== Verification & Getters ==========================//

    // ميثود مباشرة وذكية للتحقق من ظهور حقل الاسم الأول لتجنب أي تعقيدات في الـ DOM
    public boolean isFirstNameFieldDisplayed() {
        try {
            // باستخدام findElements لتفادي تضييع الوقت والـ Exceptions إذا كان الحقل غير موجود بالكامل
            return !driver.findElements(firstNameTextBox).isEmpty() && driver.findElement(firstNameTextBox).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailValidationMessage() {
        try {
            // محاولة جلب رسالة التحقق الخاصة بالمتصفح (HTML5 Validation Tooltip) المعروضة بالصورة image_907547.png
            String validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(emailTextBox)).getAttribute("validationMessage");
            if (validationMessage != null && !validationMessage.isEmpty()) {
                return validationMessage;
            }
        } catch (Exception e) {
            // تجاهل الخطأ والانتقال للـ Fallback التالي
        }

        try {
            // محاولة جلب رسالة الخطأ العادية من الـ DOM كخيار بديل
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        } catch (Exception e) {
            return "";
        }
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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(verificationCodeTextBox)).isEnabled();
    }

    public boolean isRegistrationFormDisplayed() {
        try {
            // استخدام wait قصير (ثانيتين) لكي يفشل التيست بسرعة وبدون تعطيل وقت التشغيل عندما نتوقع عدم ظهور الحقل
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
}





