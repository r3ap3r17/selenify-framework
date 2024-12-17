package selenify.core.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserBase;

public class FirefoxDecorator extends SelenifyBrowserBase {
	private static String path;

	public FirefoxDecorator(final SelenifyBrowser automatedBrowser, String path) {
		super(automatedBrowser);
		this.path = path;
	}

	@Override
	public void init() {
		System.setProperty("webdriver.gecko.driver", path);
		final WebDriver webDriver = new FirefoxDriver();
		getSelenifyBrowser().setWebDriver(webDriver);
	}
}
