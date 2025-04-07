package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class CalendarsScreen {
    protected IOSDriver driver;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Calendars\" AND type == \"XCUIElementTypeNavigationBar\"")
    private RemoteWebElement calendarsScreenHeader;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"add-calendar-button\"")
    private RemoteWebElement addCalendarButton;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"add-calendar-menubutton\"")
    private RemoteWebElement addCalendarMenuButton;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"calendarlist-cell:Default:Calendar\" AND label == \"Calendar\" AND type == \"XCUIElementTypeCell\"")
    private RemoteWebElement defaultCalendar;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Calendar\"]/preceding-sibling::*[1]")
    private RemoteWebElement defaultCalendarSelectionStatus;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"done-button\"")
    private RemoteWebElement doneButton;

    public CalendarsScreen(IOSDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @Step("Calendar screen loaded")
    public boolean calendarsScreenLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(calendarsScreenHeader)).isDisplayed();
    }
    @Step("Navigating to Add Calendar Screen")
    public void navigateToAddCalendarScreen(){
        addCalendarButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(addCalendarMenuButton)).isDisplayed();
        addCalendarMenuButton.click();
    }
    @Step("Created calendar with the name {calendarName} is displayed")
    public boolean createdCalendarDisplayed(String calendarName){
        WebElement element = driver.findElement(AppiumBy.iOSNsPredicateString(String.format("label == \"%s\" AND type == \"XCUIElementTypeCell\"", calendarName)));
        return element.isDisplayed();
    }
    @Step("Calendars with the names of Calendar and {calendarName} are selected")
    public boolean calendarsAreSelected(String calendarName){
       boolean defaultCalendarSelected = defaultCalendarSelectionStatus.getAttribute("label").equals("selected");
       WebElement element = driver.findElement(AppiumBy.xpath(String.format("//XCUIElementTypeStaticText[@name=\"%s\"]/preceding-sibling::*[1]", calendarName)));
       boolean createCalendarSelect = element.getAttribute("label").equals("selected");

       return defaultCalendarSelected && createCalendarSelect;
    }

    @Step("Edit calendar for {calendarName} is selected")
    public void clickIButton(String calendarName){
        WebElement element = driver.findElement(AppiumBy.iOSNsPredicateString(String.format("label == \"%s\" AND type == \"XCUIElementTypeOther\"", calendarName)));
        element.click();
    }

    @Step("Calendar with name {calendarName} is no longer displayed")
    public boolean correctCalendarDisplayed(String calendarName){
        boolean defaultCalendarDisplay = defaultCalendar.isDisplayed();
        boolean newCalendarDisplay;

        try {
            WebElement element = driver.findElement(AppiumBy.iOSNsPredicateString(String.format("label == \"%s\" AND type == \"XCUIElementTypeCell\"", calendarName)));
            newCalendarDisplay = !element.isDisplayed();
        }catch (NoSuchElementException | StaleElementReferenceException e){
            newCalendarDisplay = true;
        }

        return defaultCalendarDisplay && newCalendarDisplay;

    }
    @Step("Done button is clicked")
    public void clickDoneButton(){
        doneButton.click();
    }

}
