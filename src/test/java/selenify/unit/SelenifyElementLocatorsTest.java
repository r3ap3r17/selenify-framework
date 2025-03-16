package selenify.unit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.common.constants.BrowserName;
import selenify.common.exceptions.SelenifyLocatorException;
import selenify.common.exceptions.SelenifyWebElementException;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserFactory;
import selenify.utils.locators.impl.LocatorUtil;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class SelenifyElementLocatorsTest extends LocatorUtil {
	private static String HTML_PAGE;
	private static final SelenifyBrowserFactory AUTOMATED_BROWSER_FACTORY
			= new SelenifyBrowserFactory();

	@BeforeClass
	public static void testSetup() throws URISyntaxException {
		HTML_PAGE = Objects.requireNonNull(SelenifyElementLocatorsTest.class.
				getResource("/test-page.html")).toURI().toString();
	}

	@Parameterized.Parameters
	public static Iterable data() {
		return Arrays.asList(
				BrowserName.FIREFOX_HEADLESS,
				BrowserName.FIREFOX_HEADLESS
		);
	}
	private BrowserName browser;

	public SelenifyElementLocatorsTest(BrowserName browser) {
		this.browser = browser;
	}

	@Test
	public void testElementInteractions() {
		final SelenifyBrowser automatedBrowser =
				AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
		automatedBrowser.init();
		automatedBrowser.goTo(HTML_PAGE);

		try {
			// Basic button element
			automatedBrowser.waitForVisible(byCss("#button_element"));
			automatedBrowser.waitForVisible(byXpath("//button[@id='button_element']"));
			automatedBrowser.waitForVisible(byXpath("//%s[@id='%s']", "button", "button_element"));
			automatedBrowser.waitForVisible(byName("button_element"));
			automatedBrowser.waitForVisible(byClass("button_element"));
		} catch (SelenifyWebElementException | SelenifyLocatorException e) {
			fail("Method should not throw SelenifyWebElementException!");
		} finally {
			automatedBrowser.destroy();
		}
	}
}
