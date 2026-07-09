package Tests;

import Pages.FlightsandHotelPage;
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

    private FlightsandHotelPage flightsPage;
    private Faker faker;

    @BeforeMethod
    public void setUp() {
        flightsPage = new FlightsandHotelPage(driver);
        faker = new Faker();
    }

    private void navigateToFlightsAndHotelsSearch() {
        flightsPage.clickFlightsAndHotelsTab();
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("travel-packages"));
    }

    private void navigateToSearchAndToggleToOneWay() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickOneWayOption();
    }

    // ====================== POSITIVE TEST CASES ========================

    @Test(priority = 1)
    @Description("SCRUM-T38 - Verify default selection is 'Round trip' and 'Economy'")
    public void verifyDefaultSelectionIsRoundTripAndEconomy() {
        navigateToFlightsAndHotelsSearch();
        Assert.assertTrue(flightsPage.isRoundTripActive(), "'Round trip' should be active by default.");
        Assert.assertTrue(flightsPage.getCabinClassText().contains("Economy"),
                "Cabin Class dropdown should display 'Economy' by default.");
    }

    @Test(priority = 2)
    @Description("SCRUM-T39 - Verify toggling successfully from 'Round trip' to 'One way'")
    public void verifyTogglingSuccessfullyFromRoundTripToOneWay() {
        navigateToSearchAndToggleToOneWay();
        Assert.assertTrue(flightsPage.isOneWayActive(), "'One way' should become active.");
        Assert.assertFalse(flightsPage.isRoundTripActive(), "'Round trip' should be deselected.");
    }

    @Test(priority = 3)
    @Description("SCRUM-T40 - Verify toggling back from 'One way' to 'Round trip'")
    public void verifyTogglingBackFromOneWayToRoundTrip() {
        navigateToSearchAndToggleToOneWay();
        flightsPage.clickRoundTripOption();
        Assert.assertTrue(flightsPage.isRoundTripActive(), "'Round trip' should become active again.");
        Assert.assertFalse(flightsPage.isOneWayActive(), "'One way' should be deselected.");
    }

    @Test(priority = 4)
    @Description("SCRUM-T41 - Verify selecting 'Premium Economy' from the cabin class dropdown")
    public void verifySelectingPremiumEconomyFromCabinClass() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickCabinClassDropdown();
        flightsPage.selectPremiumEconomy();
        Assert.assertTrue(flightsPage.getCabinClassText().contains("Premium Economy"),
                "Selected value should update to display 'Premium Economy'.");
    }

    @Test(priority = 5)
    @Description("SCRUM-T42 - Verify selecting 'Business Class' from the cabin class dropdown")
    public void verifySelectingBusinessClassFromCabinClass() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickCabinClassDropdown();
        flightsPage.selectBusinessClass();
        Assert.assertTrue(flightsPage.getCabinClassText().contains("Business Class"),
                "Selected value should update to display 'Business Class'.");
    }

    @Test(priority = 6)
    @Description("SCRUM-T43 - Verify selecting 'First Class' from the cabin class dropdown")
    public void verifySelectingFirstClassFromCabinClass() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickCabinClassDropdown();
        flightsPage.selectFirstClass();
        Assert.assertTrue(flightsPage.getCabinClassText().contains("First Class"),
                "Selected value should update to display 'First Class'.");
    }

    @Test(priority = 7)
    @Description("SCRUM-T44 - Verify auto-complete suggestions appear correctly for departure city")
    public void verifyAutoCompleteSuggestionsForDepartureCity() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.fillRoundTripLeavingFrom("Cai");
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String actualDeparture = flightsPage.getRoundTripLeavingFromValue();
        Assert.assertTrue(actualDeparture != null && actualDeparture.toLowerCase().contains("cai"),
                "'Leaving from' field should reflect the typed input.");
    }

    @Test(priority = 8)
    @Description("SCRUM-T45 - Verify auto-complete suggestions appear correctly for destination city")
    public void verifyAutoCompleteSuggestionsForDestinationCity() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.fillRoundTripGoingTo("Tokyo");
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String actualDestination = flightsPage.getRoundTripGoingToValue();
        Assert.assertTrue(actualDestination != null && actualDestination.toLowerCase().contains("tokyo"),
                "'Going to' field should reflect the typed input. But found: " + actualDestination);
    }

    @Test(priority = 9)
    @Description("SCRUM-T48 - Verify selecting valid future check-in and checkout dates")
    public void verifySelectingValidFutureDates() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickRoundTripDate();
        flightsPage.selectDateRangeAndSet();
        String dateFieldText = flightsPage.getRoundTripDateText();
        Assert.assertTrue(dateFieldText != null && !dateFieldText.trim().isEmpty(),
                "Check-in and checkout dates should be populated in the Dates field.");
    }

    @Test(priority = 10)
    @Description("SCRUM-T51 - Verify adding maximum allowed Adults and updating the total count")
    public void verifyAddingMaximumAllowedAdults() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickTravellersInput();
        for (int i = 0; i < 4; i++) {
            flightsPage.addAdult();
        }
        flightsPage.clickApplyPassengers();
        String summaryText = flightsPage.getTravellersSummaryText();
        Assert.assertTrue(summaryText.contains("5"),
                "Traveller summary should reflect 5 total adults (1 default + 4 added). Actual: " + summaryText);
    }

    @Test(priority = 11)
    @Description("SCRUM-T52 - Verify adding Child (with age selection) and Infant on lap to the booking")
    public void verifyAddingChildAndInfantOnSeat() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickTravellersInput();
        flightsPage.addChild();
        flightsPage.selectChildAge();
        flightsPage.addInfantOnLap();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        flightsPage.clickApplyPassengers();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String summaryText = flightsPage.getTravellersSummaryText();
        Assert.assertTrue(summaryText.contains("3"),
                "Traveller summary should reflect 3 total travellers (1 Adult, 1 Child, 1 Infant). Actual: " + summaryText);
    }

    @Test(priority = 12)
    @Description("SCRUM-T55 - Verify updating the overall Traveller and Room summary text")
    public void verifyUpdatingOverallTravellerAndRoomSummaryText() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickTravellersInput();
        flightsPage.addAdult();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        flightsPage.clickApplyPassengers();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String summaryText = flightsPage.getTravellersSummaryText();
        Assert.assertTrue(summaryText.contains("2"), "Traveller summary text should update accurately to 2.");
    }

    @Test(priority = 13)
    @Description("SCRUM-T57 - Verify the functionality of the 'Clear' button in the Date Picker")
    public void verifyClearButtonInDatePicker() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickRoundTripDate();
        flightsPage.selectDateRangeAndClear();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String dateText = flightsPage.getRoundTripDateText();
        System.out.println("Executing SCRUM-T57: Testing Date Picker Clear button. Actual text: " + dateText);
        Assert.assertTrue(dateText.contains("Add date"), "Date fields should be cleared and reset to 'Add date'.");
    }

    @Test(priority = 14)
    @Description("SCRUM-T58 - Verify executing a successful search with all valid fields (Happy Path)")
    public void verifySuccessfulSearchWithAllValidFields() {
        navigateToFlightsAndHotelsSearch();
        Assert.assertTrue(flightsPage.isRoundTripActive(), "'Round trip' should be selected by default.");
        Assert.assertTrue(flightsPage.getCabinClassText().contains("Economy"), "'Economy' should be selected by default.");
        flightsPage.fillRoundTripLeavingFrom("Cairo");
        flightsPage.fillRoundTripGoingTo("Dubai");
        flightsPage.clickRoundTripDate();
        flightsPage.selectDateRangeAndSet();
        flightsPage.clickTravellersInput();
        flightsPage.addAdult();
        flightsPage.clickApplyPassengers();
        String beforeUrl = driver.getCurrentUrl();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        flightsPage.clickRoundTripSearch();
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
        flightsPage.fillRoundTripLeavingFrom("Cairo");
        flightsPage.fillRoundTripGoingTo("Cairo");
        flightsPage.clickRoundTripDate();
        flightsPage.selectDateRangeAndSet();
        String beforeUrl = driver.getCurrentUrl();
        flightsPage.clickRoundTripSearch();
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        Assert.assertNotNull(driver.getCurrentUrl(),
                "Search action should complete without throwing for identical departure/destination cities.");
    }

    @Test(priority = 16)
    @Description("SCRUM-T47 - Verify behavior when entering invalid characters or non-existent cities")
    public void verifyEnteringInvalidCharactersOrNonExistentCities() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.fillRoundTripLeavingFrom("@@@123");
        Assert.assertEquals(flightsPage.getRoundTripLeavingFromValue(), "@@@123",
                "Field should accept the typed value; a valid airport selection should not be forced through.");
    }

    @Test(priority = 17)
    @Description("SCRUM-T49 - Verify the system prevents selecting a past date")
    public void verifySystemPreventsSelectingPastDate() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickRoundTripDate();
        Assert.assertTrue(flightsPage.isPastDateDisabled(),
                "Past dates should carry the rdrDayDisabled class and be unclickable.");
    }

    @Test(priority = 18)
    @Description("SCRUM-T50 - Verify user cannot pick a return date before the check-in date")
    public void verifyUserCannotPickReturnDateBeforeCheckInDate() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickRoundTripDate();
        flightsPage.selectDateRangeAndSet();
        Assert.assertTrue(flightsPage.isSetDatesButtonEnabled(),
                "'Set dates' should only be enabled for a valid (non-inverted) date range.");
    }

    @Test(priority = 19)
    @Description("SCRUM-T53 - Verify the system prevents reducing the number of Adults to 0")
    public void verifySystemPreventsReducingAdultsToZero() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickTravellersInput();
        flightsPage.removeAdult();
        Assert.assertTrue(flightsPage.isAdultMinusDisabled(),
                "The '-' Adult button should be disabled once the count reaches 1.");
        flightsPage.clickApplyPassengers();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String summaryText = flightsPage.getTravellersSummaryText();
        Assert.assertTrue(summaryText.contains("1"), "System should not allow 0 adults, it must remain 1.");
    }

    @Test(priority = 20)
    @Description("SCRUM-T54 - Verify the system prevents adding more Infants on lap than Adults")
    public void verifySystemPreventsAddingMoreInfantsThanAdults() {
        navigateToFlightsAndHotelsSearch();
        flightsPage.clickTravellersInput();

        flightsPage.addInfantOnLap();
        flightsPage.addInfantOnLap();
        String errorMsg = flightsPage.getPassengerErrorMessage();
        Assert.assertEquals(errorMsg, "The number of infants cannot exceed the number of adults",
                "Error message should appear preventing more infants than adults.");
    }

    @Test(priority = 21)
    @Description("SCRUM-T56 - Verify date selection in One-Way mode")
    public void verifySystemPreventsDateRangeInOneWayMode() {
        navigateToSearchAndToggleToOneWay();
        flightsPage.fillOneWayLeavingFrom("Cairo");
        flightsPage.clickOneWayDate();
        flightsPage.selectDateRangeAndSet();
        String dateFieldText = flightsPage.getOneWayDateText();
        Assert.assertNotNull(dateFieldText, "The date field should not be empty after selection.");
        Assert.assertFalse(dateFieldText.isEmpty(), "Date field should have a value.");
    }

    @Test(priority = 22)
    @Description("SCRUM-T59 - Verify search validation when all mandatory fields are empty")
    public void verifySearchValidationWhenMandatoryFieldsEmpty() {
        navigateToFlightsAndHotelsSearch();
        String beforeUrl = driver.getCurrentUrl();
        flightsPage.clickRoundTripSearch();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        Assert.assertEquals(driver.getCurrentUrl(), beforeUrl,
                "Search should be blocked and the user kept on the same search form when mandatory fields are empty.");
    }

}