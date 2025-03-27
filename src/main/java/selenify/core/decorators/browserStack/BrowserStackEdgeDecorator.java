package selenify.core.decorators.browserStack;

import org.openqa.selenium.remote.DesiredCapabilities;
import selenify.core.impl.SelenifyBrowserBase;

import java.util.HashMap;

public class BrowserStackEdgeDecorator extends SelenifyBrowserBase {
	public BrowserStackEdgeDecorator(final SelenifyBrowserBase automatedBrowser) {
		super(automatedBrowser);
	}

	@Override
	public DesiredCapabilities getDesiredCapabilities() {
		final DesiredCapabilities caps = getSelenifyBrowser().getDesiredCapabilities();
		caps.setCapability("browserName", "Edge");

		HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
		bstackOptions.put("os", "Windows");
		bstackOptions.put("osVersion", "10");
		bstackOptions.put("browserVersion", "latest");

		caps.setCapability("bstack:options", bstackOptions);
		return caps;
	}
}
