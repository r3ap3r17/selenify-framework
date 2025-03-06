package selenify.core.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		final WebDriver webDriver = new FirefoxDriver(options);
		getSelenifyBrowser().setWebDriver(webDriver);
	}
}
