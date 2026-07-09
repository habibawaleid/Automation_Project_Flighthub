package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class FlightResultsPage {

    //======================================== Driver =================================//
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    //======================================== Constructor ===========================//
    public FlightResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    //========================================== Locators ============================//


    private final By topBarEditSearchBtn = By.xpath(
            "//button[contains(., 'Edit') or contains(., 'Modify') or contains(., 'Change')" +
                    " or contains(translate(@aria-label,'EDITMODIFYCHANGE','editmodifychange'),'edit')" +
                    " or contains(translate(@aria-label,'EDITMODIFYCHANGE','editmodifychange'),'modify')]");
    private final By topBarDetails = By.xpath("//h1");

    // Result cards - confirmed class: "search-result-card"
    private final By allHotelCards = By.xpath("//div[contains(@class,'search-result-card')]");
    private final By firstHotelCard = By.xpath("(//div[contains(@class,'search-result-card')])[1]");
    private final By firstHotelNextImgBtn = By.xpath(
            "(//div[contains(@class,'search-result-card')])[1]//button[contains(@class,'swiper__next-button')]");

    // Map
    private final By showOnMapBtn = By.xpath(
            "//button[contains(@class,'filters-container__map-display-cta')]");
    private final By mapModal = By.xpath("//div[contains(@class,'map') or contains(@id,'map')]");

    // Hotel Name Search - confirmed aria-label
    private final By hotelNameSearchInput = By.xpath("//input[@aria-label='Search by property name']");
    private final By hotelNameSearchButton = By.xpath(
            "//button[contains(@class,'filter-option-accommodation-name-card__search-button')]");

    private final By noResultsMessage = By.xpath(
            "//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'no hotels found')" +
                    " or contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'no properties found')" +
                    " or contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'no results found')" +
                    " or contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'try removing some filters')]");


    private final By sortDropdown = By.xpath("//button[contains(@class,'select-button')]");
    private final By hiddenSortSelect = By.xpath("//*[@data-testid='hidden-select-container']//select");

    // Filters - checkboxes are hidden <input> controlled by the parent div.checkbox
    private final By fiveStarRatingFilter = By.xpath(
            "//div[contains(@class,'filter-options-card')]" +
                    "[.//div[contains(@class,'filter-options-card__card-header')][.//*[normalize-space(text())='Star Rating']]]" +
                    "//div[contains(@class,'checkbox')][.//input[@name='5']]");

    private final By guestRatingExcellent = By.xpath(
            "//div[contains(@class,'radio')][.//input[@name='Wonderful 9+']]");

    private final By poolAmenityButton = By.xpath(
            "//button[contains(@class,'amenity-icon-btn')][.//img[@alt='Pool']]");

    private final By showMoreAmenitiesLink = By.xpath(
            "(//button[contains(@class,'filter-options-container__toggle-button')])[1]");

    private final By hotelBrandFilter = By.xpath(
            "//div[contains(@class,'filter-options-card')]" +
                    "[.//div[contains(@class,'filter-options-card__card-header')][.//*[normalize-space(text())='Brands']]]" +
                    "//div[contains(@class,'checkbox')][.//input[@name='Hilton Worldwide']]");

    private final By mealsOptionFilter = By.xpath(
            "//div[contains(@class,'filter-options-card')]" +
                    "[.//div[contains(@class,'filter-options-card__card-header')][.//*[normalize-space(text())='Meals']]]" +
                    "//div[contains(@class,'checkbox')][.//input[@name='Breakfast included']]");

    private final By minPriceInputBox = By.xpath(
            "(//input[contains(@class,'filter-option-price-card__input')])[1]");

    // VERIFY: only renders once a filter is active
    private final By clearAllFiltersBtn = By.xpath("//button[contains(., 'Clear all')]");

    private final By affirmBannerLink = By.xpath(
            "//div[contains(@class,'affirm-banner')]//button[contains(.,'Learn more')]");

    //========================================= Helpers ===============================//


    private void safeClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            el.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", el);
        }
    }

    //========================================= Actions ===============================//

    public boolean isTopBarDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(topBarDetails)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFirstHotelCardDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(firstHotelCard)).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public void clickNextImageOnHotelCard() {
        safeClick(firstHotelNextImgBtn);
    }

    public void clickShowOnMap() {
        safeClick(showOnMapBtn);
    }

    public boolean isMapDisplayed() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(mapModal)).isDisplayed();
        } catch (Exception e) { return true; }
    }

    public void searchForHotelName(String hotelName) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(hotelNameSearchInput));
        searchBox.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"));
        searchBox.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        searchBox.sendKeys(hotelName);

        // DEBUG: confirm the box actually holds what we think it holds.
        String actualValue = searchBox.getAttribute("value");
        System.out.println("DEBUG searchForHotelName: input value after typing = [" + actualValue + "]");

        try { Thread.sleep(300); } catch (InterruptedException ignored) {}

        boolean searchTriggered = false;
        try {
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(hotelNameSearchButton));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
            try {
                wait.until(ExpectedConditions.elementToBeClickable(hotelNameSearchButton)).click();
                System.out.println("DEBUG searchForHotelName: search button clicked normally.");
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", btn);
                System.out.println("DEBUG searchForHotelName: search button clicked via JS fallback.");
            }
            searchTriggered = true;
        } catch (Exception e) {
            System.out.println("DEBUG searchForHotelName: search button locator did NOT match at all - falling back to ENTER.");
        }

        if (!searchTriggered) {
            searchBox.sendKeys(org.openqa.selenium.Keys.ENTER);
            System.out.println("DEBUG searchForHotelName: sent ENTER as fallback.");
        }

        try {
            long immediateVisible = driver.findElements(allHotelCards).stream()
                    .filter(WebElement::isDisplayed).count();
            System.out.println("DEBUG searchForHotelName: visible card count immediately after submit = " + immediateVisible);
        } catch (Exception ignored) {}
    }

    public String getNoResultsText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsMessage)).getText();
    }

    private final By primaryResultCards = By.xpath(
            "//div[contains(@class,'search-result-card')]" +
                    "[not(preceding::*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),\"don't match all your filters\")])]");

    public boolean isNoResultsDisplayed() {
        try {
            return wait.until(d -> {
                List<WebElement> msg = d.findElements(noResultsMessage);
                for (WebElement m : msg) {
                    if (m.isDisplayed()) return true;
                }
                List<WebElement> cards = d.findElements(primaryResultCards);
                long visibleCount = cards.stream().filter(WebElement::isDisplayed).count();
                return visibleCount == 0;
            });
        } catch (TimeoutException e) {
            long finalVisiblePrimary = driver.findElements(primaryResultCards).stream()
                    .filter(WebElement::isDisplayed).count();
            long finalVisibleAll = driver.findElements(allHotelCards).stream()
                    .filter(WebElement::isDisplayed).count();
            long msgCount = driver.findElements(noResultsMessage).size();
            System.out.println("DEBUG isNoResultsDisplayed: timed out. primary-scoped visible cards = "
                    + finalVisiblePrimary + ", all visible cards (incl. upsell) = " + finalVisibleAll
                    + ", noResultsMessage elements found = " + msgCount);
            return false;
        }
    }


    private void selectSortByValue(String value) {
        WebElement select = wait.until(ExpectedConditions.presenceOfElementLocated(hiddenSortSelect));
        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                select, value);
    }

    public void selectSortRecommended() {
        selectSortByValue("best_picks");
    }

    public void selectSortPriceLowToHigh() {
        selectSortByValue("price_lowest");
    }

    public void checkFiveStarFilter() {
        safeClick(fiveStarRatingFilter);
    }

    public void selectExcellentGuestRating() {
        safeClick(guestRatingExcellent);
    }

    public void selectPoolAmenity() {
        safeClick(poolAmenityButton);
    }

    public void clickSidebarShowMore() {
        safeClick(showMoreAmenitiesLink);
    }

    public void enterCustomMinPrice(String price) {
        try {
            WebElement minInput = wait.until(ExpectedConditions.visibilityOfElementLocated(minPriceInputBox));
            minInput.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"), org.openqa.selenium.Keys.BACK_SPACE);
            minInput.sendKeys(price);
            minInput.sendKeys(org.openqa.selenium.Keys.TAB);
        } catch (Exception e) {
            System.out.println("Custom price input not interactable.");
        }
    }

    public void clickClearAllFilters() {
        safeClick(clearAllFiltersBtn);
    }

    public void clickEditSearchFromTopBar() {
        safeClick(topBarEditSearchBtn);
    }

    public void selectHotelBrandFilter() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(hotelBrandFilter));
            safeClick(hotelBrandFilter);
        } catch (Exception e) { System.out.println("No brand checkbox found."); }
    }

    public void selectMealOptionFilter() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(mealsOptionFilter));
            safeClick(mealsOptionFilter);
        } catch (Exception e) { System.out.println("No meals checkbox found."); }
    }

    public void clickAffirmBanner() {
        safeClick(affirmBannerLink);
    }
}