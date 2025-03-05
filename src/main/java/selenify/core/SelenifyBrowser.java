package selenify.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface SelenifyBrowser {

	WebDriver getWebDriver();

	void setWebDriver(WebDriver webDriver);

	void init();

	void destroy();

	void goTo(String url);
	String getCurrentUrl();

	WebElement findElementById(String id);
	List<WebElement> findElementsByCss(String cssSelector);
	void waitForVisibleById(String id);
	void waitForPresentById(String id);
	void clickElementById(String id);
	void typeToElementById(String id, String text);
	String getTextFromElementById(String id);
	void selectOptionByText(String selectId, String optionText);
}
