package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class AddCalendarScreen {
    IOSDriver driver;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Add Calendar\" AND type == \"XCUIElementTypeNavigationBar\"")
    private RemoteWebElement addCalendarScreenHeader;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"calendar-title-field\"")
    private RemoteWebElement calendarTitleField;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"calendar-current-selected-color\"")
    private RemoteWebElement calendarColourButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"Add Calendar\"]/*[3]" )
    private RemoteWebElement doneButton;


    public AddCalendarScreen(IOSDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean addCalendarScreenLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(addCalendarScreenHeader)).isDisplayed();
    }

    public void setCalendarTitle(String title){
        calendarTitleField.clear();
        calendarTitleField.sendKeys(title);
    }

    public void navigateToCalendarColourScreen(){
        calendarColourButton.click();
    }

    public void clickDoneButton(){
        doneButton.click();
    }
}
