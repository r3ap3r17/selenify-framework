package selenify.core.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserBase;

public class ChromeDecorator extends SelenifyBrowserBase {
	private static String path;

	public ChromeDecorator(final SelenifyBrowser automatedBrowser, String path) {
		super(automatedBrowser);
		this.path = path;
	}

	@Override
	public void init() {
		System.setProperty("webdriver.chrome.driver", path);
		final WebDriver webDriver = new ChromeDriver();
		getSelenifyBrowser().setWebDriver(webDriver);
	}
}
