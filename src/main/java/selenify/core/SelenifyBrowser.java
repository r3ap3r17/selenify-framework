package selenify.core;

import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenify.core.decorators.modifiers.ProxyRequestModifier;
import selenify.core.decorators.modifiers.ProxyResponseModifier;
import selenify.utils.locators.impl.Locator;

import java.io.File;
import java.util.List;

public interface SelenifyBrowser {

	WebDriver getWebDriver();

	void setWebDriver(WebDriver webDriver);

	DesiredCapabilities getDesiredCapabilities();

	void init();

	void destroy();

	void goTo(String url);

	String getCurrentUrl();

	Har getHar();

	void captureHarFile();

	void captureCompleteHarFile();

	File saveHarFile(String file);

	File saveHarFile(String fileDir, String file);

	void modifyRequest(ProxyRequestModifier modifier);

	void modifyRequest(String urlRegex, String responseBody);

	void modifyResponse(ProxyResponseModifier modifier);

	void modifyResponse(String urlRegex, int responseCode, String responseBody);

	void blockRequestTo(String urlRegex, int responseCode);

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
