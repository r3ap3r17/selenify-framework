package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtils {
    protected static WebDriver driver;

    // Used in combination with WebDriverManager
    public static void initDriver() {
        switch (ReadProperties.readConfigBrowser().toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Please make sure to provide a valid browser name in 'Configurations.properties' file !");
        }
    }

    // Used in combination with downloaded drivers
//    public static WebDriver initDriver(WebDriver driver) {
//        switch (ReadProperties.readConfigBrowser().toLowerCase()) {
//            case "chrome":
//                System.setProperty("webdriver.chrome.driver", ReadProperties.readConfigChromePath());
//                driver = new ChromeDriver();
//                break;
//            case "firefox":
//                System.setProperty("webdriver.gecko.driver", ReadProperties.readConfigFirefoxPath());
//                driver = new FirefoxDriver();
//                break;
//            case "edge":
//                System.setProperty("webdriver.edge.driver", ReadProperties.readConfigEdgePath());
//                driver = new EdgeDriver();
//                break;
//            default:
//                System.out.println("Please make sure to provide a valid browser name in 'Configurations.properties' file !");
//        }
//        return driver;
//    }

    public static void configDriver(String url) {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(ReadProperties.readConfigUrl());
    }
}
