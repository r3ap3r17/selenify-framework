package selenify.core.impl;

import selenify.common.constants.BrowserName;
import selenify.common.exceptions.SelenifyConfigurationException;
import selenify.core.SelenifyBrowser;
import selenify.core.decorators.BrowserMobDecorator;
import selenify.core.decorators.ChromeDecorator;
import selenify.core.decorators.FirefoxDecorator;
import selenify.core.decorators.WebDriverDecorator;
import selenify.core.decorators.browserStack.BrowserStackDecorator;
import selenify.core.decorators.browserStack.BrowserStackEdgeDecorator;
import selenify.core.decorators.browserStack.BrowserStackIOSDecorator;

public class SelenifyBrowserFactory {
	private static String _path;

	public static String getPath() {
		return _path;
	}

	public static void setPath(String path) {
		_path = path;
	}

	public SelenifyBrowser getAutomatedBrowser(final BrowserName browser) {
		String BROWSER_PROPERTY = switch (browser) {
			case CHROME, CHROME_HEADLESS -> System.getProperty("driver.chrome.path");
			case FIREFOX, FIREFOX_HEADLESS -> System.getProperty("driver.gecko.path");
		};

		switch (browser) {
			case CHROME -> {
				setPath(BROWSER_PROPERTY);
				return getChromeBrowser(false);
			}
			case CHROME_HEADLESS -> {
				setPath(BROWSER_PROPERTY);
				return getChromeBrowser(true);
			}
			case FIREFOX -> {
				setPath(BROWSER_PROPERTY);
				return getFirefoxBrowser(false);
			}
			case FIREFOX_HEADLESS -> {
				setPath(BROWSER_PROPERTY);
				return getFirefoxBrowser(true);
			}
			default -> throw new SelenifyConfigurationException("Unknown Browser " + browser.name);
		}
	}

	public SelenifyBrowser getAutomatedBrowser(final BrowserName.Remote browser) {
		switch (browser) {
			case BROWSER_STACK_EDGE -> {
				return getBrowserStackEdge();
			}
			case BROWSER_STACK_IOS -> {
				return getBrowserStackAndroid();
			}
			default -> throw new SelenifyConfigurationException("Unknown Remote Browser " + browser.name);
		}
	}

	private SelenifyBrowser getChromeBrowser(final boolean headless) {
		return new ChromeDecorator(headless, getPath(),
				new BrowserMobDecorator(new WebDriverDecorator())
		);
	}

	private SelenifyBrowser getFirefoxBrowser(final boolean headless) {
		return new FirefoxDecorator(headless, getPath(),
				new BrowserMobDecorator(new WebDriverDecorator())
		);
	}

	private SelenifyBrowser getBrowserStackEdge() {
		return new BrowserStackDecorator(
				new BrowserStackEdgeDecorator(new WebDriverDecorator())
		);
	}

	private SelenifyBrowser getBrowserStackAndroid() {
		return new BrowserStackDecorator(
				new BrowserStackIOSDecorator(new WebDriverDecorator())
		);
	}
}
