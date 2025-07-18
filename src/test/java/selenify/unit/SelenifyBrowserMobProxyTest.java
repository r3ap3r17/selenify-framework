package selenify.unit;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.base.test.SelenifyTestBase;
import selenify.common.constants.BrowserName;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SelenifyBrowserMobProxyTest extends SelenifyTestBase {
	private static final String URL = "https://example.com/";

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
	public void captureHarFileTest() {
		setAutomatedBrowser(browser);
		init();

		captureHarFile();
		goTo(URL);

		File har = saveHarFile(browser + "_TEST.har");
		assertEquals("BrowserMob Proxy", getHar().getLog().getCreator().getName());
		assertTrue(har.exists() && har.length() > 0);
	}

	@Test
	public void captureCompleteHarFileTest() {
		setAutomatedBrowser(browser);
		init();

		captureCompleteHarFile();
		goTo(URL);

		File har = saveHarFile(browser + "_COMPLETE_TEST.har");
		assertEquals("BrowserMob Proxy", getHar().getLog().getCreator().getName());
		assertTrue(har.exists() && har.length() > 0);
	}

	@Test
	public void blockRequests() {
		setAutomatedBrowser(browser);
		init();

		blockRequestTo(".*?\\.png", 201);
		goTo("https://google.com/");
	}

	@Test
	public void modifyResponseTest() throws URISyntaxException {
		final String REQ_BODY_TITLE = "New title !";
		final String uri = "/todos/7";
		final String responseBody = "{\"userId\": 1, \"id\": 1, \"title\": \"" + REQ_BODY_TITLE + "\", \"completed\": false}";

		String HTML_PAGE = Objects.requireNonNull(SelenifyElementInteractionsTest.class.
				getResource("/api-mock-page.html")).toURI().toString();

		setAutomatedBrowser(browser);
		init();

		// Modify response and validate a new value is parsed and displayed on page
		modifyResponse((response, contents, messageInfo) -> {
			if (messageInfo.getOriginalUrl().contains(uri)) {
				contents.setTextContents(responseBody);
				response.setStatus(HttpResponseStatus.OK);
				response.headers().remove("Content-Encoding");
				response.headers().set("Content-Type", "application/json; charset=UTF-8");
			}
		});
		goTo(HTML_PAGE);
		waitForVisible(byId("p-title"));
		assertEquals(REQ_BODY_TITLE, getTextFromElement(byId("p-title")));
	}
}
