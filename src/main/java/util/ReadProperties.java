package util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ReadProperties {
    // Returns a value from Configurations.properties file based on propertyKey arg
    private static String readConfigValues(String propertyKey) {
        Properties properties = null;
        // Try block to check exception
        try {
            InputStream propertyFile = Files.newInputStream(Paths.get("src/main/java/data/Config.properties"));
            properties = new Properties();
            properties.load(propertyFile);
        }
        // Catch block to handle exceptions
        catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertyKey);
    }

    // Returns value from Configurations.properties file using 'browser' as key
    public static String readConfigBrowser() {
        return readConfigValues("browser");
    }

    // Returns value from Configurations.properties file using 'startUrl' as key
    public static String readConfigUrl() {
        return readConfigValues("startUrl");
    }

    // Returns chrome driver path
    public static String readConfigChromePath() {
        return readConfigValues("chromeDriverPath");
    }
    // Returns firefox driver path
    public static String readConfigFirefoxPath() {
        return readConfigValues("firefoxDriverPath");
    }
    // Returns edge driver path
    public static String readConfigEdgePath() {
        return readConfigValues("edgeDriverPath");
    }
    // Returns Screenshot directory path
    public static String readConfigScreenShotDirPath() {
        return readConfigValues("ssdir");
    }
}
