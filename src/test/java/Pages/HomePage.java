package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ElementAction;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {

    //======================================== Variables ==============================//

    //======================================== Driver =================================//

    private final WebDriver driver;

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

    // ====================== Flights & Hotels Actions (Youssef Hebish) ======================

    public void clickFlightsAndHotelsTab() {
        ElementAction.click(driver, flightsAndHotelsBtn);
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        cleanUpDefaultValues();
    }

    public void clickRoundTripOption() {
        ElementAction.click(driver, roundTripBtn);
    }

    public void fillRoundTripLeavingFrom(String city) {
        org.openqa.selenium.WebElement input = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(roundTripLeavingFromInput));
        input.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"));
        input.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        input.sendKeys(city);
        try {
            org.openqa.selenium.WebElement option = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(firstAutoCompleteOption));
            option.click();
        } catch (Exception e) {
            System.out.println("Autocomplete dropdown didn't appear for Leaving From.");
        }
    }
    public void fillRoundTripGoingTo(String destination) {
        org.openqa.selenium.WebElement goingToInput = driver.findElement(roundTripGoingToInput);
        goingToInput.sendKeys(destination);
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        goingToInput.sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    public void clickRoundTripDate() {
        ElementAction.click(driver, roundTripDateInput);
    }

    public void clickRoundTripSearch() {
        ElementAction.click(driver, roundTripSearchBtn);
    }

    public void clickOneWayOption() {
        ElementAction.click(driver, oneWayBtn);
    }

    public void fillOneWayLeavingFrom(String city) {
        org.openqa.selenium.WebElement input = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(oneWayLeavingFromInput));

        input.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"));
        input.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);

        ElementAction.fill(driver, oneWayLeavingFromInput, city);
    }

    public void fillOneWayGoingTo(String city) {
        ElementAction.fill(driver, oneWayGoingToInput, city);
    }

    public void clickOneWayDate() {
        ElementAction.click(driver, oneWayDateInput);
    }

    public void clickOneWaySearch() {
        ElementAction.click(driver, oneWaySearchBtn);
    }
    public void clickCabinClassDropdown() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class)
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(cabinClassDropdown))
                .click();
    }

    public void selectPremiumEconomy() {
        ElementAction.click(driver, premiumEconomyOption);
    }

    public void clickTravellersInput() {
        ElementAction.click(driver, travellersInput);
    }
    public void clickApplyPassengers() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(applyPassengerBtn)).click();
    }
    public void selectBusinessClass() {
        ElementAction.click(driver, businessClassOption);
    }

    public void selectFirstClass() {
        ElementAction.click(driver, firstClassOption);
    }

    public void addAdult() {
        org.openqa.selenium.WebElement btn = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(adultPlusBtn));
        new org.openqa.selenium.interactions.Actions(driver).moveToElement(btn).click().perform();
    }

    public void removeAdult() {
        try {
            org.openqa.selenium.WebElement minusBtn = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(3))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(adultMinusBtn));
            minusBtn.click();
        } catch (Exception e) {
            System.out.println("Adult minus button is disabled and cannot be clicked (Expected).");
        }
    }

    public void addChild() {
        org.openqa.selenium.WebElement btn = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(childPlusBtn));
        new org.openqa.selenium.interactions.Actions(driver).moveToElement(btn).click().perform();
    }

    public void addInfantOnLap() {
        org.openqa.selenium.WebElement btn = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(infantLapPlusBtn));
        new org.openqa.selenium.interactions.Actions(driver).moveToElement(btn).click().perform();
    }

    public String getTravellersSummaryText() {
        return new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(travellersSummaryText))
                .getAttribute("value");
    }

    public void selectDateRangeAndClear() {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(startDate)).click();
            Thread.sleep(500);
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(endDate)).click();
            Thread.sleep(500);
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(clearDatesBtn)).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectDateRangeAndSet() {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(startDate)).click();
            Thread.sleep(500);
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(endDate)).click();
            Thread.sleep(500);
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(setDatesBtn)).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void cleanUpDefaultValues() {
        try {
            org.openqa.selenium.WebElement input = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(3))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(roundTripLeavingFromInput));
            input.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"));
            input.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        } catch (Exception e) {
            System.out.println("Departure input was already empty.");
        }
        try {
            org.openqa.selenium.WebElement checkbox = driver.findElement(By.xpath("//div[contains(@class, 'travel-packages')]//input[@type='checkbox']"));
            if (checkbox.isSelected()) {
                org.openqa.selenium.WebElement checkboxWrapper = checkbox.findElement(By.xpath("./.."));
                new org.openqa.selenium.interactions.Actions(driver).moveToElement(checkboxWrapper).click().perform();
            }
        } catch (Exception e) {
            System.out.println("Checkbox not found or not selected.");
        }
    }
    public String getPassengerErrorMessage() {
        try {
            org.openqa.selenium.WebElement errorElement = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(passengerErrorMsg));
            return errorElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getCabinClassText() {
        return new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(cabinClassDropdown))
                .getText();
    }

    public String getRoundTripLeavingFromValue() {
        return driver.findElement(roundTripLeavingFromInput).getAttribute("value");
    }

    public String getRoundTripGoingToValue() {
        return driver.findElement(roundTripGoingToInput).getAttribute("value");
    }

    public String getRoundTripDateText() {
        return driver.findElement(roundTripDateInput).getText();
    }

    public String getOneWayDateText() {
        return driver.findElement(oneWayDateInput).getText();
    }


    public boolean isRoundTripActive() {
        String cls = driver.findElement(segmentWrapper).getAttribute("class");
        return cls != null && cls.contains("roundtrip");
    }
    public boolean isOneWayActive() {
        String cls = driver.findElement(segmentWrapper).getAttribute("class");
        return cls != null && cls.contains("oneway");
    }

    public boolean isAdultMinusDisabled() {
        try {
            org.openqa.selenium.WebElement minusBtn = driver.findElement(adultMinusBtn);
            String classes = minusBtn.getAttribute("class");
            System.out.println("DEBUG - Classes found on Adult Minus button: " + classes);
            return classes != null && classes.contains("disabled");
        } catch (Exception e) {
            System.out.println("DEBUG - Could not find the minus button: " + e.getMessage());
            return false;
        }
    }

    public boolean isApplyPassengersEnabled() {
        return driver.findElement(applyPassengerBtn).isEnabled();
    }

    public boolean isSetDatesButtonEnabled() {
        return driver.findElement(setDatesBtn).isEnabled();
    }

    // Extends the same rdrDayDisabled convention already used inside startDate/endDate
    private final By anyDisabledDate = By.xpath("//div[contains(@class,'date-picker')]//button[contains(@class,'rdrDayDisabled')]");
    public boolean isPastDateDisabled() {
        return !driver.findElements(anyDisabledDate).isEmpty();
    }

    public void selectChildAge() {
        try {
            org.openqa.selenium.WebElement dropdown = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(childAgeDropdown));
            new org.openqa.selenium.interactions.Actions(driver).moveToElement(dropdown).click().perform();
            Thread.sleep(500);
            org.openqa.selenium.WebElement ageOption = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(childAge3Years));
            new org.openqa.selenium.interactions.Actions(driver).moveToElement(ageOption).click().perform();
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Could not select child age: " + e.getMessage());
        }
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

    // Syncing missing methods
}
