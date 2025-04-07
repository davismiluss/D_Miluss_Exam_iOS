package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class SplashScreen {
    protected IOSDriver driver;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"SplashScreen\"")
    private RemoteWebElement splashScreenContainer;

    @iOSXCUITFindBy(iOSNsPredicate = "value == \"Continue\"")
    private RemoteWebElement continueButton;

    public SplashScreen(IOSDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean splashScreenLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(splashScreenContainer)).isDisplayed();
    }

    public void clickContinueButton(){
        continueButton.click();
    }

}
