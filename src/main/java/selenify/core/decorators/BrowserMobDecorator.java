package selenify.core.decorators;

import com.google.common.net.HttpHeaders;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
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
import java.util.regex.Pattern;

public class BrowserMobDecorator extends SelenifyBrowserBase {
	private static final int PROXY_PORT = 8888;
	private static final String HAR_FILE_DIR = "target/har/";
	private BrowserMobProxy proxy;

	public BrowserMobDecorator(final SelenifyBrowserBase automatedBrowser) {
		super(automatedBrowser);
		new File(HAR_FILE_DIR).mkdirs();
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
		desiredCapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

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
	public void saveHarFile(String fileDir, final String file) {
		try {
			proxy.getHar().writeTo(new File(fileDir, file));
		} catch (final IOException ex) {
			throw new SelenifyFileSaveException("Could not save a file!", ex);
		}
	}

	@Override
	public void saveHarFile(final String file) {
		saveHarFile(HAR_FILE_DIR, file);
	}

	@Override
	public void blockRequestTo(String urlRegex, int responseCode) {
		proxy.addRequestFilter((request, contents, messageInfo) -> {
			if (Pattern.compile(urlRegex).matcher(messageInfo.getOriginalUrl()).matches()) {
				System.out.println(request.getUri());
				final HttpResponse response = new DefaultHttpResponse(
						request.getProtocolVersion(),
						HttpResponseStatus.valueOf(responseCode));
				response.headers().add(HttpHeaders.CONNECTION, "Close");
				return response;
			}
			return null;
		});

//		getSelenifyBrowser().blockRequestTo(urlRegex, responseCode); // Uncomment if ever needed
	}
}
