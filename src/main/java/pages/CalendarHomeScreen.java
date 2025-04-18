package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class CalendarHomeScreen {

    protected IOSDriver driver;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Calendar\"")
    private RemoteWebElement calendarHomeScreenContainer;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"add-plus-button\"")
    private RemoteWebElement plusButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"DayViewContainerView\"]/*[1]")
    private RemoteWebElement backButton;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"calendars-button\"")
    private RemoteWebElement calendarsButton;

    public CalendarHomeScreen(IOSDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @Step("Calendar home screen is loaded")
    public boolean calendarHomeScreenLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(calendarHomeScreenContainer)).isDisplayed();
    }
    @Step("Navigating to New event screen")
    public void clickPlusButton() {
        plusButton.click();
    }
    @Step("Navigating to the Month view")
    public void clickBackButton(){
        backButton.click();
    }

    @Step("Navigating to the calendars screen")
    public void clickCalendarsButton(){
        calendarsButton.click();
    }
}
