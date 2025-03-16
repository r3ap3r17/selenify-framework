package selenify.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenify.utils.locators.impl.Locator;

import java.util.List;

public interface SelenifyBrowser {

	WebDriver getWebDriver();

	void setWebDriver(WebDriver webDriver);

	DesiredCapabilities getDesiredCapabilities();

	void init();

	void destroy();

	void goTo(String url);

	String getCurrentUrl();

	WebElement findElement(Locator locator);

	List<WebElement> findElements(Locator locator);

	void waitForVisible(Locator locator);

	void waitForVisible(Locator locator, int waitTime);

	void waitForPresent(Locator locator);

	void waitForPresent(Locator locator, int waitTime);

	void clickElement(Locator locator);

	void typeToElement(Locator locator, String text);

	String getTextFromElement(Locator locator);

	void selectOptionByText(Locator locator, String optionText);
}
