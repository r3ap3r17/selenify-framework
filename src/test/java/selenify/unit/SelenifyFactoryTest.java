package selenify.unit;

import selenify.constants.BrowserName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserFactory;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SelenifyFactoryTest {
	private static final String URL = "https://example.com/";
	private BrowserName browser;
	private static final SelenifyBrowserFactory AUTOMATED_BROWSER_FACTORY
			= new SelenifyBrowserFactory();


	public SelenifyFactoryTest(BrowserName browser) {
		this.browser = browser;
	}

	@Parameterized.Parameters
	public static Iterable data() {
		return Arrays.asList(
				BrowserName.CHROME,
				BrowserName.FIREFOX
		);
	}

	@Test
	public void createDriverAndOpenUrl() {
		final SelenifyBrowser automatedBrowser =
				AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
		automatedBrowser.init();
		automatedBrowser.goTo(URL);
		assertEquals(URL, automatedBrowser.getCurrentUrl());
		automatedBrowser.destroy();
	}
}
