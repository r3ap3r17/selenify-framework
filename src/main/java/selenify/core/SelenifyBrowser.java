package selenify.core;

import org.openqa.selenium.WebDriver;

public interface SelenifyBrowser {

	WebDriver getWebDriver();

	void setWebDriver(WebDriver webDriver);

	void init();

	void destroy();

	void goTo(String url);
	String getCurrentUrl();

	void clickElementById(String id);

	void typeToElementById(String id, String text);

	String getTextFromElementById(String id);
}
