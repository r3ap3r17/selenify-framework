package selenify.core.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserBase;

public class FirefoxDecorator extends SelenifyBrowserBase {
	private static String path;
	private final boolean headless;


	public FirefoxDecorator(final SelenifyBrowser automatedBrowser, String path) {
		super(automatedBrowser);
		this.path = path;
		this.headless = false;
	}

	public FirefoxDecorator(final boolean headless, final SelenifyBrowser automatedBrowser, String path) {
		super(automatedBrowser);
		this.path = path;
		this.headless = headless;
	}

	@Override
	public void init() {
		System.setProperty("webdriver.gecko.driver", path);
		FirefoxOptions options = new FirefoxOptions();
		if (headless) {
			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
		}
		options.addArguments("--no-sandbox");

		options.merge(getDesiredCapabilities());
		final WebDriver webDriver = new FirefoxDriver(options);
		getSelenifyBrowser().setWebDriver(webDriver);
	}
}
