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


    //======================================== Constructor ===========================//

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    //========================================== Locators ============================//


    // Locators for signup and login
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
    private final By signInBtn = By.xpath("//button[@class='login-modal-submit-btn is-main-button login-modal-button'][normalize-space()='Sign in']");

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

    //========================================= Actions ===============================//

    // Sign-up flow actions
    public void clickSignInButton() {
        ElementAction.click(driver, signupBottomBtn);
    }

    public void clickChooseEmail() {
        ElementAction.click(driver, emailChosenOption);
    }

    public void fillEmail(String email) {
        ElementAction.fill(driver, emailTextBox, email);
    }

    public void clickContinueAfterEmail() {
        ElementAction.click(driver, continueAfterEmail);
    }

    public void fillFirstName(String firstName) {
        ElementAction.fill(driver, firstNameTextBox, firstName);
    }

    public void fillLastName(String lastName) {
        ElementAction.fill(driver, lastNameTextBox, lastName);
    }

    public void fillPassword(String password) {
        ElementAction.fill(driver, passwordTextBox, password);
    }

    public void fillConfirmPassword(String confirmPassword) {
        ElementAction.fill(driver, confirmPasswordTextBox, confirmPassword);
    }

    public void clickSignUpButton() {
        ElementAction.click(driver, signupBtn);
    }

    public void fillAccountPassword(String password) {
        ElementAction.fill(driver, passwordAccountTextBox, password);
    }

    public void clickContinueButton() {
        ElementAction.click(driver, continueBtn);
    }

    // ====================== HELPER METHODS FOR SIGNUP ======================

    public void openSignupFlow() {
        clickSignInButton();
        clickChooseEmail();
    }
    public void completeSignupFlow(String email, String firstName,
                                   String lastName, String password,
                                   String confirmPassword) {

        fillEmail(email);
        clickContinueAfterEmail();
        fillFirstName(firstName);
        fillLastName(lastName);
        fillPassword(password);
        fillConfirmPassword(confirmPassword);
        clickSignUpButton();
        fillAccountPassword(password);
        clickContinueButton();
    }
    //======================================== Assertions ===============================//

    public void assertVerificationCodeBoxVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(verificationCodeTextBox));
        Assert.assertTrue(driver.findElement(verificationCodeTextBox).isDisplayed());
    }

}
