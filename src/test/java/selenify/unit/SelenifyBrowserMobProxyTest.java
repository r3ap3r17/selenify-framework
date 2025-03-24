package selenify.unit;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.common.constants.BrowserName;
import selenify.core.SelenifyBrowser;
import selenify.core.impl.SelenifyBrowserFactory;
import selenify.test.impl.SelenifyTestBase;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SelenifyBrowserMobProxyTest extends SelenifyTestBase {
	private static final String URL = "https://example.com/";
	private static final SelenifyBrowserFactory AUTOMATED_BROWSER_FACTORY
			= new SelenifyBrowserFactory();

	@Parameterized.Parameters(name = "Browser: {0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{BrowserName.CHROME},
				{BrowserName.FIREFOX},
				{BrowserName.CHROME_HEADLESS},
				{BrowserName.FIREFOX_HEADLESS}
		});
	}

	private BrowserName browser;

	public SelenifyBrowserMobProxyTest(BrowserName browser) {
		this.browser = browser;
	}

	@Test
	public void captureHarFile() {
		SelenifyBrowser automatedBrowser =
				AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
		try {
			automatedBrowser.init();
			automatedBrowser.captureHarFile();
			automatedBrowser.goTo(URL);
		} finally {
			try {
				File har = automatedBrowser.saveHarFile(browser + "_TEST.har");
				assertEquals("BrowserMob Proxy", automatedBrowser.getHar().getLog().getCreator().getName());
				assertTrue(har.exists() && har.length() > 0);
			} finally {
				automatedBrowser.destroy();
			}
		}
	}

	@Test
	public void captureCompleteHarFile() {
		SelenifyBrowser automatedBrowser =
				AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
		try {
			automatedBrowser.init();
			automatedBrowser.captureCompleteHarFile();
			automatedBrowser.goTo(URL);
		} finally {
			try {
				File har = automatedBrowser.saveHarFile(browser + "_COMPLETE_TEST.har");
				assertEquals("BrowserMob Proxy", automatedBrowser.getHar().getLog().getCreator().getName());
				assertTrue(har.exists() && har.length() > 0);
			} finally {
				automatedBrowser.destroy();
			}
		}
	}

	@Test
	public void blockRequests() {
		SelenifyBrowser automatedBrowser =
				AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
		try {
			automatedBrowser.init();
			automatedBrowser.blockRequestTo(".*?\\.png", 201);
			automatedBrowser.goTo("https://google.com/");
		} finally {
			automatedBrowser.destroy();
		}
	}

	@Test
	public void modifyRequests() throws URISyntaxException {
		final String REQ_BODY_TITLE = "New title !";
		final String uri = "/todos/7";
		final String responseBody = "{\"userId\": 1, \"id\": 1, \"title\": \"" + REQ_BODY_TITLE + "\", \"completed\": false}";

		String HTML_PAGE = Objects.requireNonNull(SelenifyElementInteractionsTest.class.
				getResource("/api-mock-page.html")).toURI().toString();

		SelenifyBrowser automatedBrowser =
				AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
		try {
			automatedBrowser.init();
			// Modify response and validate a new value is parsed and displayed on page
			automatedBrowser.modifyResponse((response, contents, messageInfo) -> {
				if (messageInfo.getOriginalUrl().contains(uri)) {
					contents.setTextContents(responseBody);
					response.setStatus(HttpResponseStatus.OK);
					response.headers().remove("Content-Encoding");
					response.headers().set("Content-Type", "application/json; charset=UTF-8");
				}
			});
			automatedBrowser.goTo(HTML_PAGE);
			automatedBrowser.waitForVisible(byId("p-title"));
			assertEquals(REQ_BODY_TITLE, automatedBrowser.getTextFromElement(byId("p-title")));
		} finally {
			automatedBrowser.destroy();
		}
	}
}
