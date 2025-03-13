package selenify.core;

import selenify.common.constants.BrowserName;
import selenify.core.decorators.ChromeDecorator;
import selenify.core.decorators.FirefoxDecorator;
import selenify.core.decorators.WebDriverDecorator;

public class SelenifyBrowserFactory {
	private static String PROPERTY_BASE = "driver.%s.path";
	private static String _path;

	public static String getPath() {
		return _path;
	}

	public static void setPath(String path) {
		_path = path;
	}

	public SelenifyBrowser getAutomatedBrowser(final BrowserName browser) {
		switch (browser) {
			case CHROME -> {
				setPath(System.getProperty(String.format(PROPERTY_BASE, browser.name.toLowerCase())));
				return getChromeBrowser();
			}
			case FIREFOX -> {
				setPath(System.getProperty(String.format(PROPERTY_BASE, browser.name.toLowerCase())));
				return getFirefoxBrowser();
			}
			default -> throw new IllegalArgumentException("Unknown browser " + browser.name);
		}
	}


	private SelenifyBrowser getChromeBrowser() {
		return new ChromeDecorator(new WebDriverDecorator(), getPath());
	}

	private SelenifyBrowser getFirefoxBrowser() {
		return new FirefoxDecorator(new WebDriverDecorator(), getPath());
	}
}
