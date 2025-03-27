package selenify.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.common.constants.BrowserName;
import selenify.core.impl.SelenifyBrowserFactory;
import selenify.base.test.SelenifyTestBase;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SelenifyBrowserStackTest extends SelenifyTestBase {
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
		setAutomatedBrowser(browser);
		try {
			init();
			maximizeWindow();
			goTo(URL);
			assertEquals(URL, getCurrentUrl());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			destroy();
		}
	}
}
