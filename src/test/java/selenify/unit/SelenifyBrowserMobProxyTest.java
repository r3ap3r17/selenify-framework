package selenify.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.common.constants.BrowserName;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserFactory;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SelenifyBrowserMobProxyTest {
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
				automatedBrowser.saveHarFile(browser + "_TEST.har");
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
				automatedBrowser.saveHarFile(browser + "_COMPLETE_TEST.har");
			} finally {
				automatedBrowser.destroy();
			}
		}
	}
}
