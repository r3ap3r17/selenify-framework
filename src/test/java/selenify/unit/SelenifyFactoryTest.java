package selenify.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.base.test.SelenifyTestBase;
import selenify.common.constants.BrowserName;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SelenifyFactoryTest extends SelenifyTestBase {
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

	private final BrowserName browser;

	public SelenifyFactoryTest(BrowserName browser) {
		this.browser = browser;
	}

	@Test
	public void createDriverAndOpenUrl() {
		setAutomatedBrowser(browser);
		init();
		goTo(URL);
		assertEquals(URL, getCurrentUrl());
	}
}
