package selenify.core.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserBase;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDecorator extends SelenifyBrowserBase {
	private static String path;
	private final boolean headless;

	public ChromeDecorator(String path, final SelenifyBrowser automatedBrowser) {
		super(automatedBrowser);
		this.path = path;
		this.headless = false;
	}

	public ChromeDecorator(boolean headless, String path, final SelenifyBrowser automatedBrowser) {
		super(automatedBrowser);
		this.headless = headless;
		this.path = path;
	}

	@Override
	public void init() {
		System.setProperty("webdriver.chrome.driver", path);
		System.setProperty("webdriver.chrome.verboseLogging", "true");

		final ChromeOptions options = new ChromeOptions();
		if (headless) {
			options.addArguments("--headless=new");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-dev-shm-usage");
		}
		options.addArguments("--remote-debugging-port=9222");
		options.addArguments("--disable-background-networking");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-background-networking");
		options.addArguments("--no-sandbox");
		options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/chrome-profile");

		DesiredCapabilities capabilities = getDesiredCapabilities();
		capabilities.getCapabilityNames().forEach(capability ->
				options.setCapability(capability, capabilities.getCapability(capability)));

//		final WebDriver webDriver = new ChromeDriver(options);
		try {
			WebDriver webDriver = new RemoteWebDriver(new URL("http://localhost:9515"), options);
			getSelenifyBrowser().setWebDriver(webDriver);
		} catch (MalformedURLException e) {
			// pass
		}

	}
}
