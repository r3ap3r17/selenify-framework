package selenify.core.decorators.browserStack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import selenify.common.exceptions.SelenifyConfigurationException;
import selenify.core.SelenifyBrowserBase;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDecorator extends SelenifyBrowserBase {
	private static final String USERNAME_ENV = "BROWSERSTACK_USERNAME";
	private static final String AUTOMATE_KEY_ENV = "BROWSERSTACK_ACCESS_KEY";
	private static final String URL_TEMPLATE = "https://%s:%s@hub.browserstack.com/wd/hub\"";

	public BrowserStackDecorator(final SelenifyBrowserBase automatedBrowser) {
		super(automatedBrowser);
	}

	@Override
	public void init() {
		try {
			final String url = String.format(URL_TEMPLATE,
					System.getenv(USERNAME_ENV), System.getenv(AUTOMATE_KEY_ENV));
			final WebDriver webDriver = new RemoteWebDriver(new URL(url), getDesiredCapabilities());
			getSelenifyBrowser().setWebDriver(webDriver);
		} catch (MalformedURLException ex) {
			throw new SelenifyConfigurationException(ex);
		}
	}
}
