package selenify.core.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserBase;

public class ChromeDecorator extends SelenifyBrowserBase {
	private static String path;
	private final boolean headless;

	public ChromeDecorator(final SelenifyBrowser automatedBrowser, String path) {
		super(automatedBrowser);
		this.path = path;
		this.headless = false;
	}

	public ChromeDecorator(boolean headless, final SelenifyBrowser automatedBrowser, String path) {
		super(automatedBrowser);
		this.path = path;
		this.headless = headless;
	}

	@Override
	public void init() {
		System.setProperty("webdriver.chrome.driver", path);
		final ChromeOptions options = new ChromeOptions();
		if (headless) {
			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-dev-shm-usage");
		}
		options.addArguments("--no-sandbox");

		options.merge(getDesiredCapabilities());
		final WebDriver webDriver = new ChromeDriver(options);
		getSelenifyBrowser().setWebDriver(webDriver);
	}
}
