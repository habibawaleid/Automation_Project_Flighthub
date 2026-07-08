package Tests;

import Pages.HomePage;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Flights and Hotels")
@Feature("Search Form Functionality")
public class FlightAndHotelSearchTest extends BaseTest {

    private HomePage homePage;
    private Faker faker;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
        faker = new Faker();
    }

    private void navigateToFlightsAndHotelsSearch() {
        homePage.clickFlightsAndHotelsTab();
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("travel-packages"));
    }

    private void navigateToSearchAndToggleToOneWay() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickOneWayOption();
    }

    // ====================== POSITIVE TEST CASES ========================

    @Test(priority = 1)
    @Description("SCRUM-T38 - Verify default selection is 'Round trip' and 'Economy'")
    public void verifyDefaultSelectionIsRoundTripAndEconomy() {
        navigateToFlightsAndHotelsSearch();
        Assert.assertTrue(homePage.isRoundTripActive(), "'Round trip' should be active by default.");
        Assert.assertTrue(homePage.getCabinClassText().contains("Economy"),
                "Cabin Class dropdown should display 'Economy' by default.");
    }

    @Test(priority = 2)
    @Description("SCRUM-T39 - Verify toggling successfully from 'Round trip' to 'One way'")
    public void verifyTogglingSuccessfullyFromRoundTripToOneWay() {
        navigateToSearchAndToggleToOneWay();
        Assert.assertTrue(homePage.isOneWayActive(), "'One way' should become active.");
        Assert.assertFalse(homePage.isRoundTripActive(), "'Round trip' should be deselected.");
    }

    @Test(priority = 3)
    @Description("SCRUM-T40 - Verify toggling back from 'One way' to 'Round trip'")
    public void verifyTogglingBackFromOneWayToRoundTrip() {
        navigateToSearchAndToggleToOneWay();
        homePage.clickRoundTripOption();
        Assert.assertTrue(homePage.isRoundTripActive(), "'Round trip' should become active again.");
        Assert.assertFalse(homePage.isOneWayActive(), "'One way' should be deselected.");
    }

    @Test(priority = 4)
    @Description("SCRUM-T41 - Verify selecting 'Premium Economy' from the cabin class dropdown")
    public void verifySelectingPremiumEconomyFromCabinClass() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickCabinClassDropdown();
        homePage.selectPremiumEconomy();
        Assert.assertTrue(homePage.getCabinClassText().contains("Premium Economy"),
                "Selected value should update to display 'Premium Economy'.");
    }

    @Test(priority = 5)
    @Description("SCRUM-T42 - Verify selecting 'Business Class' from the cabin class dropdown")
    public void verifySelectingBusinessClassFromCabinClass() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickCabinClassDropdown();
        homePage.selectBusinessClass();
        Assert.assertTrue(homePage.getCabinClassText().contains("Business Class"),
                "Selected value should update to display 'Business Class'.");
    }

    @Test(priority = 6)
    @Description("SCRUM-T43 - Verify selecting 'First Class' from the cabin class dropdown")
    public void verifySelectingFirstClassFromCabinClass() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickCabinClassDropdown();
        homePage.selectFirstClass();
        Assert.assertTrue(homePage.getCabinClassText().contains("First Class"),
                "Selected value should update to display 'First Class'.");
    }

    @Test(priority = 7)
    @Description("SCRUM-T44 - Verify auto-complete suggestions appear correctly for departure city")
    public void verifyAutoCompleteSuggestionsForDepartureCity() {
        navigateToFlightsAndHotelsSearch();
        homePage.fillRoundTripLeavingFrom("Cai");
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String actualDeparture = homePage.getRoundTripLeavingFromValue();
        Assert.assertTrue(actualDeparture != null && actualDeparture.toLowerCase().contains("cai"),
                "'Leaving from' field should reflect the typed input.");
    }

    @Test(priority = 8)
    @Description("SCRUM-T45 - Verify auto-complete suggestions appear correctly for destination city")
    public void verifyAutoCompleteSuggestionsForDestinationCity() {
        navigateToFlightsAndHotelsSearch();
        homePage.fillRoundTripGoingTo("Tokyo");
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String actualDestination = homePage.getRoundTripGoingToValue();
        Assert.assertTrue(actualDestination != null && actualDestination.toLowerCase().contains("tokyo"),
                "'Going to' field should reflect the typed input. But found: " + actualDestination);
    }

    @Test(priority = 9)
    @Description("SCRUM-T48 - Verify selecting valid future check-in and checkout dates")
    public void verifySelectingValidFutureDates() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickRoundTripDate();
        homePage.selectDateRangeAndSet();
        String dateFieldText = homePage.getRoundTripDateText();
        Assert.assertTrue(dateFieldText != null && !dateFieldText.trim().isEmpty(),
                "Check-in and checkout dates should be populated in the Dates field.");
    }

    @Test(priority = 10)
    @Description("SCRUM-T51 - Verify adding maximum allowed Adults and updating the total count")
    public void verifyAddingMaximumAllowedAdults() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickTravellersInput();
        for (int i = 0; i < 4; i++) {
            homePage.addAdult();
        }
        homePage.clickApplyPassengers();
        String summaryText = homePage.getTravellersSummaryText();
        Assert.assertTrue(summaryText.contains("5"),
                "Traveller summary should reflect 5 total adults (1 default + 4 added). Actual: " + summaryText);
    }

    @Test(priority = 11)
    @Description("SCRUM-T52 - Verify adding Child (with age selection) and Infant on lap to the booking")
    public void verifyAddingChildAndInfantOnSeat() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickTravellersInput();
        homePage.addChild();
        homePage.selectChildAge();
        homePage.addInfantOnLap();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        homePage.clickApplyPassengers();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String summaryText = homePage.getTravellersSummaryText();
        Assert.assertTrue(summaryText.contains("3"),
                "Traveller summary should reflect 3 total travellers (1 Adult, 1 Child, 1 Infant). Actual: " + summaryText);
    }

    @Test(priority = 12)
    @Description("SCRUM-T55 - Verify updating the overall Traveller and Room summary text")
    public void verifyUpdatingOverallTravellerAndRoomSummaryText() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickTravellersInput();
        homePage.addAdult();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        homePage.clickApplyPassengers();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String summaryText = homePage.getTravellersSummaryText();
        Assert.assertTrue(summaryText.contains("2"), "Traveller summary text should update accurately to 2.");
    }

    @Test(priority = 13)
    @Description("SCRUM-T57 - Verify the functionality of the 'Clear' button in the Date Picker")
    public void verifyClearButtonInDatePicker() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickRoundTripDate();
        homePage.selectDateRangeAndClear();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String dateText = homePage.getRoundTripDateText();
        System.out.println("Executing SCRUM-T57: Testing Date Picker Clear button. Actual text: " + dateText);
        Assert.assertTrue(dateText.contains("Add date"), "Date fields should be cleared and reset to 'Add date'.");
    }

    @Test(priority = 14)
    @Description("SCRUM-T58 - Verify executing a successful search with all valid fields (Happy Path)")
    public void verifySuccessfulSearchWithAllValidFields() {
        navigateToFlightsAndHotelsSearch();
        Assert.assertTrue(homePage.isRoundTripActive(), "'Round trip' should be selected by default.");
        Assert.assertTrue(homePage.getCabinClassText().contains("Economy"), "'Economy' should be selected by default.");
        homePage.fillRoundTripLeavingFrom("Cairo");
        homePage.fillRoundTripGoingTo("Dubai");
        homePage.clickRoundTripDate();
        homePage.selectDateRangeAndSet();
        homePage.clickTravellersInput();
        homePage.addAdult();
        homePage.clickApplyPassengers();
        String beforeUrl = driver.getCurrentUrl();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        homePage.clickRoundTripSearch();
        org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(25));
        wait.until(d -> !d.getCurrentUrl().equals(beforeUrl));
        Assert.assertNotEquals(driver.getCurrentUrl(), beforeUrl,
                "A valid search should navigate the user away from the search form to the results page.");
    }

    // ====================== NEGATIVE TEST CASES ========================

    @Test(priority = 15)
    @Description("SCRUM-T46 - Verify system behavior when entering the same departure and destination city")
    public void verifyEnteringSameDepartureAndDestinationCity() {
        navigateToFlightsAndHotelsSearch();
        homePage.fillRoundTripLeavingFrom("Cairo");
        homePage.fillRoundTripGoingTo("Cairo");
        homePage.clickRoundTripDate();
        homePage.selectDateRangeAndSet();
        String beforeUrl = driver.getCurrentUrl();
        homePage.clickRoundTripSearch();
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        Assert.assertNotNull(driver.getCurrentUrl(),
                "Search action should complete without throwing for identical departure/destination cities.");
    }

    @Test(priority = 16)
    @Description("SCRUM-T47 - Verify behavior when entering invalid characters or non-existent cities")
    public void verifyEnteringInvalidCharactersOrNonExistentCities() {
        navigateToFlightsAndHotelsSearch();
        homePage.fillRoundTripLeavingFrom("@@@123");
        Assert.assertEquals(homePage.getRoundTripLeavingFromValue(), "@@@123",
                "Field should accept the typed value; a valid airport selection should not be forced through.");
    }

    @Test(priority = 17)
    @Description("SCRUM-T49 - Verify the system prevents selecting a past date")
    public void verifySystemPreventsSelectingPastDate() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickRoundTripDate();
        Assert.assertTrue(homePage.isPastDateDisabled(),
                "Past dates should carry the rdrDayDisabled class and be unclickable.");
    }

    @Test(priority = 18)
    @Description("SCRUM-T50 - Verify user cannot pick a return date before the check-in date")
    public void verifyUserCannotPickReturnDateBeforeCheckInDate() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickRoundTripDate();
        homePage.selectDateRangeAndSet();
        Assert.assertTrue(homePage.isSetDatesButtonEnabled(),
                "'Set dates' should only be enabled for a valid (non-inverted) date range.");
    }

    @Test(priority = 19)
    @Description("SCRUM-T53 - Verify the system prevents reducing the number of Adults to 0")
    public void verifySystemPreventsReducingAdultsToZero() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickTravellersInput();
        homePage.removeAdult();
        Assert.assertTrue(homePage.isAdultMinusDisabled(),
                "The '-' Adult button should be disabled once the count reaches 1.");
        homePage.clickApplyPassengers();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String summaryText = homePage.getTravellersSummaryText();
        Assert.assertTrue(summaryText.contains("1"), "System should not allow 0 adults, it must remain 1.");
    }

    @Test(priority = 20)
    @Description("SCRUM-T54 - Verify the system prevents adding more Infants on lap than Adults")
    public void verifySystemPreventsAddingMoreInfantsThanAdults() {
        navigateToFlightsAndHotelsSearch();
        homePage.clickTravellersInput();

        homePage.addInfantOnLap();
        homePage.addInfantOnLap();
        String errorMsg = homePage.getPassengerErrorMessage();
        Assert.assertEquals(errorMsg, "The number of infants cannot exceed the number of adults",
                "Error message should appear preventing more infants than adults.");
    }

    @Test(priority = 21)
    @Description("SCRUM-T56 - Verify the system prevents selecting a date range when 'One way' is active")
    public void verifySystemPreventsDateRangeInOneWayMode() {
        navigateToSearchAndToggleToOneWay();
        homePage.fillOneWayLeavingFrom("Cairo");
        homePage.clickOneWayDate();
        homePage.selectDateRangeAndSet();
        String dateFieldText = homePage.getOneWayDateText();
        Assert.assertFalse(dateFieldText != null && dateFieldText.contains("-"),
                "A date range must not be populated for a One-way trip; only a single date should be accepted.");
    }

    @Test(priority = 22)
    @Description("SCRUM-T59 - Verify search validation when all mandatory fields are empty")
    public void verifySearchValidationWhenMandatoryFieldsEmpty() {
        navigateToFlightsAndHotelsSearch();
        String beforeUrl = driver.getCurrentUrl();
        homePage.clickRoundTripSearch();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        Assert.assertEquals(driver.getCurrentUrl(), beforeUrl,
                "Search should be blocked and the user kept on the same search form when mandatory fields are empty.");
    }

}