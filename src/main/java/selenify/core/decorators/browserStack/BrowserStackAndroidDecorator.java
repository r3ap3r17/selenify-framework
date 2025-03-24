package selenify.core.decorators.browserStack;

import org.openqa.selenium.remote.DesiredCapabilities;
import selenify.core.SelenifyBrowserBase;

import java.util.HashMap;

public class BrowserStackAndroidDecorator extends SelenifyBrowserBase {
	public BrowserStackAndroidDecorator(final SelenifyBrowserBase automatedBrowser) {
		super(automatedBrowser);
	}

	@Override
	public DesiredCapabilities getDesiredCapabilities() {
		final DesiredCapabilities caps = getSelenifyBrowser().getDesiredCapabilities();
		caps.setCapability("browserName", "chromium");

		HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
		bstackOptions.put("osVersion", "16");
		bstackOptions.put("deviceName", "iPhone 13");

		caps.setCapability("bstack:options", bstackOptions);
		return caps;
	}

	@Override
	public void maximizeWindow() {
		// Do nothing, as Mobiles are maximised by default
	}
}
