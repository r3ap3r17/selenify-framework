package selenify.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.common.constants.BrowserName;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserFactory;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SelenifyFactoryTest {
	private static final String URL = "https://example.com/";
	private static final SelenifyBrowserFactory AUTOMATED_BROWSER_FACTORY
			= new SelenifyBrowserFactory();

	@Parameterized.Parameters
	public static Iterable data() {
		return Arrays.asList(
				BrowserName.CHROME,
				BrowserName.FIREFOX,
				BrowserName.CHROME_HEADLESS,
				BrowserName.FIREFOX_HEADLESS
		);
	}
	private BrowserName browser;

	public SelenifyFactoryTest(BrowserName browser) {
		this.browser = browser;
	}

	@Test
	public void createDriverAndOpenUrl() {
		final SelenifyBrowser automatedBrowser;
		automatedBrowser = AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
		try {
			automatedBrowser.init();
			automatedBrowser.goTo(URL);
			assertEquals(URL, automatedBrowser.getCurrentUrl());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			automatedBrowser.destroy();
		}
	}
}
