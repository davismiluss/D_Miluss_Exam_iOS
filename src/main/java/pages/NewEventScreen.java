package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class NewEventScreen {
    protected IOSDriver driver;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"New\"`]")
    private RemoteWebElement newEventScreenContainer;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Title\"")
    private RemoteWebElement titleField;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Starts']/preceding-sibling::XCUIElementTypeButton/XCUIElementTypeButton)[1]")
    private RemoteWebElement startDateButton;

    @iOSXCUITFindBy(iOSNsPredicate = "type == \"XCUIElementTypeDatePicker\"")
    private RemoteWebElement calendarContainer;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Date and Time Picker\"]/*[2]")
    private RemoteWebElement timePickerButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Date and Time Picker\"]/*[1]")
    private RemoteWebElement endDateButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[1]")
    private RemoteWebElement hourPicker;

    @iOSXCUITFindBy(iOSNsPredicate = "value == \"Travel Time\"")
    private RemoteWebElement travelTimeButton;

    @iOSXCUITFindBy(iOSNsPredicate = "type == \"XCUIElementTypeCollectionView\"")
    private RemoteWebElement travelTimeContainer;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[`name == \"All-day\"`]")
    private RemoteWebElement allDayToggle;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"add-button\"")
    private  RemoteWebElement addButton;

    public NewEventScreen(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("New event screen loaded")
    public boolean newEventScreenIsDisplayed(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(newEventScreenContainer)).isDisplayed();
    }

    @Step("New event title set")
    public void setTitle(String title){
        titleField.clear();
        titleField.sendKeys(title);
    }

    @Step("Start day of {day}, month of {month} and hour of {hour} is set")
    public void setStartDateAndTime(String day,String month, String hour){
        startDateButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(calendarContainer)).isDisplayed();
        WebElement element = driver.findElement(AppiumBy.iOSNsPredicateString(String.format("name ENDSWITH \"%s %s\"", day, month)));
        element.click();
        timePickerButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(hourPicker)).sendKeys(hour);
    }

    @Step("End day of {day}, month of {month} and hour of {hour} is set")
    public void setEndDateAndTime(String day,String month, String hour){
        endDateButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(calendarContainer)).isDisplayed();
        WebElement element = driver.findElement(AppiumBy.iOSNsPredicateString(String.format("name ENDSWITH \"%s %s\"", day, month)));
        element.click();
        timePickerButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(hourPicker)).sendKeys(hour);
    }
    @Step("{travelTime} travel time is set")
    public void selectTravelTime(String travelTime){
        travelTimeButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(travelTimeContainer)).isDisplayed();
        WebElement element = driver.findElement(AppiumBy.iOSNsPredicateString(String.format("name == \"%s\"", travelTime)));
        element.click();
    }
    @Step("All day button is toggled")
    public void toggleAllDay(){
        allDayToggle.click();
    }
    @Step("No time picker is displayed")
    public boolean timePickerIsNotDisplayed(){
        try {
            return !timePickerButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }
    @Step("Ad new event")
    public void clickAddButton(){
        addButton.click();
    }
}
