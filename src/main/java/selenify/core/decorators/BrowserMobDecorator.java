package selenify.core.decorators;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenify.core.SelenifyBrowserBase;

public class BrowserMobDecorator extends SelenifyBrowserBase {
	private BrowserMobProxy proxy;

	public BrowserMobDecorator(final SelenifyBrowserBase automatedBrowser) {
		super(automatedBrowser);
	}

	@Override
	public DesiredCapabilities getDesiredCapabilities() {
		proxy = new BrowserMobProxyServer();
		proxy.start(8888);

		final DesiredCapabilities desiredCapabilities =
				getSelenifyBrowser().getDesiredCapabilities();

		final Proxy seleniumProxy = new Proxy();
		final String proxyStr = "localhost:" + proxy.getPort();

		seleniumProxy.setHttpProxy(proxyStr);
		seleniumProxy.setSslProxy(proxyStr);
		desiredCapabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
		return desiredCapabilities;
	}

	@Override
	public void destroy() {
		getSelenifyBrowser().destroy();
		if (proxy != null) {
			proxy.stop();
		}
	}
}
