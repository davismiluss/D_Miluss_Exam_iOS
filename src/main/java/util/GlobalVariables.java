package util;

import java.time.Duration;

public class GlobalVariables {
    public static final String appiumLocalURL = "http://127.0.0.1:4723/";
    public static final Duration globalTimeout = Duration.ofSeconds(10);
    public static final String boredApiActivityEndpoint = "https://bored-api.appbrewery.com/random";
    public static String response;
}
