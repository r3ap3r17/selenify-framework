package selenify.core.decorators;

import org.openqa.selenium.WebDriver;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserBase;

public class WebDriverDecorator extends SelenifyBrowserBase {
	private WebDriver webDriver;

	public WebDriverDecorator() {}

	public WebDriverDecorator(final SelenifyBrowser automatedBrowser) {
		super(automatedBrowser);
	}

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	public void setWebDriver(final WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@Override
	public void destroy() {
		if (getWebDriver() != null) {
			getWebDriver().quit();
		}
	}

	@Override
	public void goTo(final String url) {
		getWebDriver().get(url);
	}

	@Override
	public String getCurrentUrl() {
		return getWebDriver().getCurrentUrl();
	}
}
