package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class CalendarColourScreen {
    IOSDriver driver;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Calendar Colour\" AND type == \"XCUIElementTypeNavigationBar\"")
    private RemoteWebElement calendarColourScreenHeader;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Add Calendar\"")
    private RemoteWebElement backToAddCalendarScreenButton;

    public CalendarColourScreen(IOSDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Calendar colour screen is loaded")
    public boolean calendarColourScreenIsDisplayed(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(calendarColourScreenHeader)).isDisplayed();
    }

    @Step("{colour} is selected as calendar colour")
    public void setCalendarColour(String colour){
        WebElement element = driver.findElement(AppiumBy.iOSNsPredicateString(String.format("name == \"calendar-current-selected-color\" AND label == \"%s\"", colour)));
        element.click();
        backToAddCalendarScreenButton.click();
    }
}
