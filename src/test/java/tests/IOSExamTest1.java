package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import util.DriverSetup;
import util.GlobalVariables;

public class IOSExamTest1 extends DriverSetup{

    private final String startDate = "27";
    private final String endDate = "28";
    private final String month = "April";

    @Test(testName = "iOS Exam Test 1")
    public void iOSExamTest1(){

        Assert.assertTrue(splashScreen.splashScreenLoaded(), "Splash Screen not Loaded");

        splashScreen.clickContinueButton();
        Assert.assertTrue(calendarHomeScreen.calendarHomeScreenLoaded(), "Calendar home screen not loaded");

        calendarHomeScreen.clickPlusButton();
        Assert.assertTrue(newEventScreen.newEventScreenIsDisplayed(), "New event screen not displayed");

        GlobalVariables.response = restAssuredUtility.getActivityValue("activity");
        newEventScreen.setTitle(GlobalVariables.response);

        newEventScreen.setStartDateAndTime(startDate,month, "15");
        newEventScreen.setEndDateAndTime(endDate,month, "18");
        newEventScreen.selectTravelTime("30 minutes");
        newEventScreen.toggleAllDay();
        Assert.assertTrue(newEventScreen.timePickerIsNotDisplayed(), "Time picker is displayed");

        newEventScreen.clickAddButton();
        Assert.assertTrue(calendarHomeScreen.calendarHomeScreenLoaded(), "Calendar home screen not loaded");

        calendarHomeScreen.clickBackButton();
        Assert.assertTrue(monthViewScreen.monthViewScreenDisplayed(), "Month view screen not displayed");
        Assert.assertTrue(monthViewScreen.eventExistsOnDates(startDate,endDate,month), "Events do not exist on dates");
    }
}
