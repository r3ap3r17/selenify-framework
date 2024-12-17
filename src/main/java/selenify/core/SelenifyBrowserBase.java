package selenify.core;

import org.openqa.selenium.WebDriver;

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
}
