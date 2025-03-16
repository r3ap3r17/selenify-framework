package selenify.core.decorators;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenify.common.exceptions.SelenifyFileSaveException;
import selenify.core.SelenifyBrowserBase;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

public class BrowserMobDecorator extends SelenifyBrowserBase {
	private static final int PROXY_PORT = 8888;
	private BrowserMobProxy proxy;

	public BrowserMobDecorator(final SelenifyBrowserBase automatedBrowser) {
		super(automatedBrowser);
	}

	@Override
	public DesiredCapabilities getDesiredCapabilities() {
		proxy = new BrowserMobProxyServer();
		proxy.start(PROXY_PORT);

		final String proxyUrl = "localhost:" + PROXY_PORT;

		final Proxy seleniumProxy = new Proxy();
		seleniumProxy.setHttpProxy(proxyUrl);
		seleniumProxy.setSslProxy(proxyUrl);
//		seleniumProxy.setSocksProxy(proxyUrl);
//		seleniumProxy.setSocksVersion(5);

		final DesiredCapabilities desiredCapabilities =
				getSelenifyBrowser().getDesiredCapabilities();
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

	@Override
	public void captureHarFile() {
		proxy.newHar();
	}

	@Override
	public void captureCompleteHarFile() {
		final EnumSet<CaptureType> captureTypes =
				CaptureType.getAllContentCaptureTypes();

		captureTypes.addAll(CaptureType.getHeaderCaptureTypes());
		captureTypes.addAll(CaptureType.getCookieCaptureTypes());

		proxy.setHarCaptureTypes(captureTypes);
		proxy.newHar();
	}

	@Override
	public void saveHarFile(final String file) {
		try {
			proxy.getHar().writeTo(new File(file));
		} catch (final IOException ex) {
			throw new SelenifyFileSaveException("Could not save a file!", ex);
		}
	}
}
