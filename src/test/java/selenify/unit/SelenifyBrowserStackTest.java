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
public class SelenifyBrowserStackTest {
	private static final String URL = "https://example.com/";
	private static final SelenifyBrowserFactory AUTOMATED_BROWSER_FACTORY
			= new SelenifyBrowserFactory();

	@Parameterized.Parameters(name = "Browser: {0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{BrowserName.Remote.BROWSER_STACK_EDGE},
				{BrowserName.Remote.BROWSER_STACK_ANDROID},
		});
	}

	private final BrowserName.Remote browser;

	public SelenifyBrowserStackTest(BrowserName.Remote browser) {
		this.browser = browser;
	}

	@Test
	public void createBrowserStackDriverAndOpenUrl() {
		final SelenifyBrowser automatedBrowser = AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
		try {
			automatedBrowser.init();
			automatedBrowser.maximizeWindow();
			automatedBrowser.goTo(URL);
			assertEquals(URL, automatedBrowser.getCurrentUrl());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			automatedBrowser.destroy();
		}
	}
}
