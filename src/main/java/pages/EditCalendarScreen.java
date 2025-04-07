package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;

public class EditCalendarScreen {
    IOSDriver driver;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Edit Calendar\" AND type == \"XCUIElementTypeNavigationBar\"")
    private RemoteWebElement editCalendarScreenHeader;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"delete-calendar-button\"")
    private RemoteWebElement deleteCalendarButton;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Cancel\"")
    private RemoteWebElement cancelButton;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Delete Calendar\"")
    private RemoteWebElement confirmDeleteCalendarButton;


    public EditCalendarScreen(IOSDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean editCalendarScreenIsDisplayed(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(editCalendarScreenHeader)).isDisplayed();
    }

    public void deleteCalendar(){
       deleteCalendarButton.click();
    }
}
