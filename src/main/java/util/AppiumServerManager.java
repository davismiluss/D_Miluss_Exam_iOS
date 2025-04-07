package util;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumServerManager {

    private AppiumDriverLocalService appiumService;

    public void startAppiumServer() {
        appiumService = AppiumDriverLocalService.buildDefaultService();
        appiumService.start();
        System.out.println("Appium Server started at: " + appiumService.getUrl());
    }

    public void stopAppiumServer() {
        if (appiumService != null) {
            appiumService.stop();
        }
    }
}
