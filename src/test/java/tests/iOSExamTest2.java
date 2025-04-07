package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;

public class iOSExamTest2 extends DriverSetup {
    private final String calendarName = "Calendar test";

    @Test(testName = "iOS Exam Test 2")
    public void iOSExamTest2(){

        Assert.assertTrue(splashScreen.splashScreenLoaded(), "Splash Screen not Loaded");

        splashScreen.clickContinueButton();
        Assert.assertTrue(calendarHomeScreen.calendarHomeScreenLoaded(), "Calendar home screen not loaded");

        calendarHomeScreen.clickCalendarsButton();
        Assert.assertTrue(calendarsScreen.calendarsScreenLoaded(), "Calendars screen not loaded");

        calendarsScreen.navigateToAddCalendarScreen();
        Assert.assertTrue(addCalendarScreen.addCalendarScreenLoaded(), "Add Calendar Screen not loaded");

        addCalendarScreen.setCalendarTitle(calendarName);
        addCalendarScreen.navigateToCalendarColourScreen();
        Assert.assertTrue(calendarColourScreen.calendarColourScreenIsDisplayed(), "Calendar colour screen not loaded");

        calendarColourScreen.setCalendarColour("Blue");
        addCalendarScreen.clickDoneButton();
        Assert.assertTrue(calendarsScreen.calendarsScreenLoaded(), "Calendars screen not loaded");
        Assert.assertTrue(calendarsScreen.createdCalendarDisplayed(calendarName), "Created Calendar is not displayed");
        Assert.assertTrue(calendarsScreen.calendarsAreSelected(calendarName), "Correct calendars are not selected");

        calendarsScreen.clickIButton(calendarName);
        Assert.assertTrue(editCalendarScreen.editCalendarScreenIsDisplayed(), "Edit Calendar Screen is not displayed");

        editCalendarScreen.deleteCalendar();
        Assert.assertTrue(calendarsScreen.calendarsScreenLoaded(), "Calendars screen not loaded");
        Assert.assertTrue(calendarsScreen.correctCalendarDisplayed(calendarName), "Correct Calendar is not displayed");

        calendarsScreen.clickDoneButton();
        Assert.assertTrue(calendarHomeScreen.calendarHomeScreenLoaded(), "Calendar home screen not loaded");
    }

}
