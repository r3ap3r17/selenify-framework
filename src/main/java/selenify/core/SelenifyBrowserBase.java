package selenify.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	public WebElement findElementById(String id) {
		return getSelenifyBrowser().findElementById(id);
	}

	@Override
	public List<WebElement> findElementsByCss(String cssSelector) {
		return getSelenifyBrowser().findElementsByCss(cssSelector);
	}

	@Override
	public void waitForVisibleById(String id) {
		getSelenifyBrowser().waitForVisibleById(id);
	}

	@Override
	public void waitForPresentById(String id) {
		getSelenifyBrowser().waitForPresentById(id);
	}

	@Override
	public void clickElementById(String id) {
		getSelenifyBrowser().clickElementById(id);
	}

	@Override
	public void typeToElementById(String id, String text) {
		getSelenifyBrowser().typeToElementById(id, text);
	}

	@Override
	public String getTextFromElementById(String id) {
		return getSelenifyBrowser().getTextFromElementById(id);
	}

	@Override
	public void selectOptionByText(String selectId, String optionText) {
		getSelenifyBrowser().selectOptionByText(selectId, optionText);
	}
}
