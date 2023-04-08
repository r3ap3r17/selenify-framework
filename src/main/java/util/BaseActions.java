package util;

import data.Timeouts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import static util.DriverUtils.driver;

public class BaseActions {
    protected static WebDriverWait wait;
    protected static WebElement element;
    private static int counter = 1;
    // Prints a comment to console
    protected void comment(String message) {
        System.out.println("STEP " + counter + ": " + message.toUpperCase());
        counter++;
    }
    // Method Used To Take ScreenShot
    public void takeScreenShot(String fileName) {
        try {
            File file = new File(ReadProperties.readConfigScreenShotDirPath() + "/" + fileName + ".png");
            Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies
                    .viewportPasting(100)).takeScreenshot(driver);
            ImageIO.write(myScreenshot.getImage(),"PNG", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Method Used To Take ScreenShot Before Closing Driver
    public void takeScreenShotBeforeClosing() {
        try {
            File file = new File(ReadProperties.readConfigScreenShotDirPath()
                    + "/" + Thread.currentThread().getStackTrace()[3].getClassName() + ".png");
            Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies
                    .viewportPasting(100)).takeScreenshot(driver);
            ImageIO.write(myScreenshot.getImage(),"PNG", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Sleeps for 3 seconds
    protected void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    // Initialises and configure WebDriver
    public void openUrl(String URL) {
        counter = 1;
        DriverUtils.initDriver();
        DriverUtils.configDriver(URL);
    }
    // Closes WebDriver
    public void closeDriver() {
        takeScreenShotBeforeClosing();
        driver.quit();
    }
    // Waits for URL to change
    public void waitForUrlChange(String url, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.urlContains(url));
    }
    // Waits until element is visible and returns WebElement obj
    protected WebElement waitForElementToBeVisible(By locator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait  = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // Waits for WebElement to be visible
    protected void waitToBeVisible(By locator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // Waits for element to not be visible
    protected void waitNotToBeVisible(By locator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    // Waits for element to be visible than returns a list of WebElements
    protected List<WebElement> waitForVisibleElements(By lcoator, Integer... time) {
        int timeout = (time.length > 0) ? time[0] : Timeouts.TIMEOUT_MEDIUM;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(lcoator));
    }
    // Clicks WebElement
    protected void clickElement(By locator) {
        element = waitForElementToBeVisible(locator);
        element.click();
    }
}
