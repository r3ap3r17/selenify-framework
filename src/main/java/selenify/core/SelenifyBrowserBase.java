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

public class SelenifyBrowserBase implements SelenifyBrowser {
	private SelenifyBrowser _selenifyBrowser;

	public SelenifyBrowserBase() {}

	public SelenifyBrowserBase(SelenifyBrowser automatedBrowser) {
		this._selenifyBrowser = automatedBrowser;
	}

	public SelenifyBrowser getSelenifyBrowser() {
		return _selenifyBrowser;
	}

	@Override
	public WebDriver getWebDriver() {
		return getSelenifyBrowser().getWebDriver();
	}

	@Override
	public void setWebDriver(WebDriver webDriver) {
		getSelenifyBrowser().setWebDriver(webDriver);
	}

	@Override
	public DesiredCapabilities getDesiredCapabilities() {
		if (getSelenifyBrowser() != null) {
			return getSelenifyBrowser().getDesiredCapabilities();
		}
		return new DesiredCapabilities();
	}

	@Override
	public void init() {
		getSelenifyBrowser().init();
	}

	@Override
	public void destroy() {
		getSelenifyBrowser().destroy();
	}

	@Override
	public void goTo(String url) {
		getSelenifyBrowser().goTo(url);
	}

	@Override
	public String getCurrentUrl() {
		return getSelenifyBrowser().getCurrentUrl();
	}

	@Override
	public Har getHar() {
		return getSelenifyBrowser().getHar();
	}

	@Override
	public void captureHarFile() {
		getSelenifyBrowser().captureHarFile();
	}

	@Override
	public void captureCompleteHarFile() {
		getSelenifyBrowser().captureCompleteHarFile();
	}

	@Override
	public File saveHarFile(String file) {
		return getSelenifyBrowser().saveHarFile(file);
	}

	@Override
	public File saveHarFile(String fileDir, String file) {
		return getSelenifyBrowser().saveHarFile(fileDir, file);
	}

	@Override
	public void modifyRequest(ProxyRequestModifier modifier) {
		if (getSelenifyBrowser() != null) {
			getSelenifyBrowser().modifyRequest(modifier);
		}
	}

	@Override
	public void modifyRequest(String urlRegex, String responseBody) {
		if (getSelenifyBrowser() != null) {
			getSelenifyBrowser().modifyRequest(urlRegex, responseBody);
		}
	}

	@Override
	public void modifyResponse(ProxyResponseModifier modifier) {
		if (getSelenifyBrowser() != null) {
			getSelenifyBrowser().modifyResponse(modifier);
		}
	}

	@Override
	public void modifyResponse(String urlRegex, int responseCode, String responseBody) {
		if (getSelenifyBrowser() != null) {
			getSelenifyBrowser().modifyResponse(urlRegex, responseCode, responseBody);
		}
	}

	@Override
	public void blockRequestTo(final String urlRegex, final int responseCode) {
		if (getSelenifyBrowser() != null) {
			getSelenifyBrowser().blockRequestTo(urlRegex, responseCode);
		}
	}

	@Override
	public WebElement findElement(Locator locator) {
		return getSelenifyBrowser().findElement(locator);
	}

	@Override
	public List<WebElement> findElements(Locator locator) {
		return getSelenifyBrowser().findElements(locator);
	}

	@Override
	public void waitForVisible(Locator locator) {
		getSelenifyBrowser().waitForVisible(locator);
	}

	@Override
	public void waitForVisible(Locator locator, int waitTime) {
		getSelenifyBrowser().waitForVisible(locator, waitTime);
	}

	@Override
	public void waitForPresent(Locator locator) {
		getSelenifyBrowser().waitForPresent(locator);
	}

	@Override
	public void waitForPresent(Locator locator, int waitTime) {
		getSelenifyBrowser().waitForVisible(locator, waitTime);
	}

	@Override
	public void clickElement(Locator locator) {
		getSelenifyBrowser().clickElement(locator);
	}

	@Override
	public void typeToElement(Locator locator, String text) {
		getSelenifyBrowser().typeToElement(locator, text);
	}

	@Override
	public String getTextFromElement(Locator locator) {
		return getSelenifyBrowser().getTextFromElement(locator);
	}

	@Override
	public void selectOptionByText(Locator locator, String optionText) {
		getSelenifyBrowser().selectOptionByText(locator, optionText);
	}
}
