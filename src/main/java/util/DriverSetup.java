package util;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import pages.*;
@Listeners({ITestListenerUtility.class})
public class DriverSetup extends ConfigReader {

    protected static IOSDriver driver;

    protected RestAssuredUtility restAssuredUtility;
    private final AppiumServerManager appiumServerManager = new AppiumServerManager();


    protected CalendarHomeScreen calendarHomeScreen;
    protected SplashScreen splashScreen;
    protected NewEventScreen newEventScreen;
    protected CalendarsScreen calendarsScreen;
    protected AddCalendarScreen addCalendarScreen;
    protected CalendarColourScreen calendarColourScreen;
    protected EditCalendarScreen editCalendarScreen;
    protected MonthViewScreen monthViewScreen;

    @BeforeSuite
    public void startAppiumServer() {
        appiumServerManager.startAppiumServer();
    }

    @BeforeMethod
    public void setUp(){
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(getProperty("device.name"))
                .setPlatformVersion(getProperty("platform.version"))
                .setBundleId(getProperty("bundle.id"))
                //.setFullReset(true)
                .setAutoAcceptAlerts(true);

        try{
            driver = new IOSDriver(new URI(GlobalVariables.appiumLocalURL).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        restAssuredUtility = new RestAssuredUtility();

        calendarHomeScreen = new CalendarHomeScreen(driver);
        splashScreen = new SplashScreen(driver);
        newEventScreen = new NewEventScreen(driver);
        calendarsScreen = new CalendarsScreen(driver);
        addCalendarScreen = new AddCalendarScreen(driver);
        calendarColourScreen = new CalendarColourScreen(driver);
        editCalendarScreen = new EditCalendarScreen(driver);
        monthViewScreen = new MonthViewScreen(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        appiumServerManager.stopAppiumServer();
    }
}
