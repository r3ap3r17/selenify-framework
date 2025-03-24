package selenify.core.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenify.core.SelenifyBrowser;
import selenify.core.impl.SelenifyBrowserBase;

public class FirefoxDecorator extends SelenifyBrowserBase {
	private static String path;
	private final boolean headless;


	public FirefoxDecorator(String path, final SelenifyBrowser automatedBrowser) {
		super(automatedBrowser);
		this.path = path;
		this.headless = false;
	}

	public FirefoxDecorator(boolean headless, String path, final SelenifyBrowser automatedBrowser) {
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

		DesiredCapabilities capabilities = getDesiredCapabilities();
		capabilities.getCapabilityNames().forEach(capability ->
				options.setCapability(capability, capabilities.getCapability(capability)));

		final WebDriver webDriver = new FirefoxDriver(options);
		getSelenifyBrowser().setWebDriver(webDriver);
	}
}
