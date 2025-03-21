package selenify.core.decorators;

import com.google.common.net.HttpHeaders;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenify.common.exceptions.SelenifyFileSaveException;
import selenify.core.SelenifyBrowserBase;
import selenify.core.decorators.modifiers.ProxyRequestModifier;
import selenify.core.decorators.modifiers.ProxyResponseModifier;

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
	public Har getHar() {
		Har har = null;
		if (proxy != null) {
			har = proxy.getHar();
		}
		return har;
	}

	@Override
	public DesiredCapabilities getDesiredCapabilities() {
		proxy = new BrowserMobProxyServer();
		proxy.start(PROXY_PORT);
		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT,
				CaptureType.REQUEST_HEADERS, CaptureType.RESPONSE_HEADERS);

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
	public File saveHarFile(String fileDir, final String file) {
		try {
			File harFIle = new File(fileDir, file);
			getHar().writeTo(harFIle);
			return harFIle;
		} catch (final IOException ex) {
			throw new SelenifyFileSaveException("Could not save a file!", ex);
		}
	}

	@Override
	public File saveHarFile(final String file) {
		return saveHarFile(HAR_FILE_DIR, file);
	}

	@Override
	public void modifyRequest(ProxyRequestModifier modifier) {
		proxy.addRequestFilter(modifier::modify);
//		getSelenifyBrowser().modifyRequest(modifier);
	}

	@Override
	public void modifyRequest(final String urlRegex, final String responseBody) {
		modifyRequest((response, contents, messageInfo) -> {
			if (matchUrlRegex(urlRegex, messageInfo.getOriginalUrl())) {
				contents.setTextContents(responseBody);
			}
			return null;
		});
	}

	@Override
	public void modifyResponse(ProxyResponseModifier modifier) {
		proxy.addResponseFilter(modifier::modify);
//		getSelenifyBrowser().modifyResponse(modifier);
	}

	@Override
	public void modifyResponse(final String urlRegex, final int responseCode, final String responseBody) {
		modifyResponse((response, contents, messageInfo) -> {
			if (matchUrlRegex(urlRegex, messageInfo.getOriginalUrl())) {
				contents.setTextContents(responseBody);
				response.setStatus(HttpResponseStatus.OK);
			}
		});
	}

	@Override
	public void blockRequestTo(String urlRegex, int responseCode) {
		modifyRequest((request, contents, messageInfo) -> {
			if (matchUrlRegex(urlRegex, messageInfo.getOriginalUrl())) {
				final HttpResponse response = new DefaultHttpResponse(
						request.getProtocolVersion(),
						HttpResponseStatus.valueOf(responseCode));
				response.headers().add(HttpHeaders.CONNECTION, "Close");
				return response;
			}
			return null;
		});
	}

	private boolean matchUrlRegex(String urlRegex, String url) {
		return Pattern.compile(urlRegex).matcher(url).matches();
	}
}
