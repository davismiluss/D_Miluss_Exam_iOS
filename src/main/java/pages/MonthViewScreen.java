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

public class MonthViewScreen {
    protected IOSDriver driver;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"MonthViewContainerView\"")
    private RemoteWebElement monthViewContainer;


    public MonthViewScreen(IOSDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @Step("Month view screen is displayed")
    public boolean monthViewScreenDisplayed(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(monthViewContainer)).isDisplayed();
    }
    @Step("Event exists on the days of {startDay} and {endDay} in month of {month}")
    public boolean eventExistsOnDates(String startDay,String endDay, String month){
        WebElement startDate = driver.findElement(AppiumBy.iOSNsPredicateString(String.format("name ENDSWITH \"%s %s\"", startDay, month)));
        WebElement endDate = driver.findElement(AppiumBy.iOSNsPredicateString(String.format("name ENDSWITH \"%s %s\"",endDay, month)));

        return startDate.getAttribute("value").equals("1 event") && endDate.getAttribute("value").equals("1 event");
    }



}
