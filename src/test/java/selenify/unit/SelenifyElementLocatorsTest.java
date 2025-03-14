package selenify.unit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.common.constants.BrowserName;
import selenify.common.exceptions.SelenifyWebElementException;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserFactory;
import selenify.utils.locators.impl.Locator;
import selenify.utils.locators.impl.LocatorUtil;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class SelenifyElementLocatorsTest extends LocatorUtil {
	private static String HTML_PAGE;
	private BrowserName browser;
	private static final SelenifyBrowserFactory AUTOMATED_BROWSER_FACTORY
			= new SelenifyBrowserFactory();
	// Element id's
	public final Locator ID_MESSAGE = byId("message");
	public final Locator ID_BUTTON = byId("button_element");
	public final Locator XPATH_BUTTON = byXpath("//button[@id='button_element']");
	public final Locator CSS_BUTTON = byCss("#button_element");
	public final Locator NAME_BUTTON = byName("button_element");
	public final Locator CLASS_BUTTON = byClass("button_element");

	@BeforeClass
	public static void testSetup() throws URISyntaxException {
		HTML_PAGE = Objects.requireNonNull(SelenifyElementLocatorsTest.class.
				getResource("/test-page.html")).toURI().toString();
	}

	public SelenifyElementLocatorsTest(BrowserName browser) {
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
	public void testElementInteractions() {
		final SelenifyBrowser automatedBrowser =
				AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
		automatedBrowser.init();
		automatedBrowser.goTo(HTML_PAGE);

		try {
			// Basic button element
			automatedBrowser.waitForVisible(CSS_BUTTON);
			automatedBrowser.waitForVisible(XPATH_BUTTON);
			automatedBrowser.waitForVisible(NAME_BUTTON);
			automatedBrowser.waitForVisible(CLASS_BUTTON);
		} catch (SelenifyWebElementException e) {
			fail("Method should not throw SelenifyWebElementException!");
		} finally {
			automatedBrowser.destroy();
		}
	}
}
