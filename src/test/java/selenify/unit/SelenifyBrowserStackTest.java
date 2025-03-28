package selenify.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.base.test.SelenifyTestBase;
import selenify.common.constants.BrowserName;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SelenifyBrowserStackTest extends SelenifyTestBase {
	private static final String URL = "https://example.com/";

	@Parameterized.Parameters(name = "Browser: {0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{BrowserName.Remote.BROWSER_STACK_EDGE},
				{BrowserName.Remote.BROWSER_STACK_IOS},
		});
	}

	private final BrowserName.Remote browser;

	public SelenifyBrowserStackTest(BrowserName.Remote browser) {
		this.browser = browser;
	}

	@Test
	public void createBrowserStackDriverAndOpenUrl() {
		setAutomatedBrowser(browser);
		init();
		maximizeWindow();

		goTo(URL);
		assertEquals(URL, getCurrentUrl());
	}
}
