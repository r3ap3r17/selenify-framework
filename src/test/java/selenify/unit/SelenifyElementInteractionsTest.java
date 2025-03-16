package selenify.unit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.common.constants.BrowserName;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserFactory;
import selenify.utils.locators.impl.Locator;
import selenify.utils.locators.impl.LocatorUtil;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SelenifyElementInteractionsTest extends LocatorUtil {
	private static String HTML_PAGE;
	private static final SelenifyBrowserFactory AUTOMATED_BROWSER_FACTORY
			= new SelenifyBrowserFactory();

	@BeforeClass
	public static void testSetup() throws URISyntaxException {
		HTML_PAGE = Objects.requireNonNull(SelenifyElementInteractionsTest.class.
				getResource("/test-page.html")).toURI().toString();
	}

	@Parameterized.Parameters
	public static Iterable data() {
		return Arrays.asList(
				BrowserName.FIREFOX_HEADLESS,
				BrowserName.FIREFOX_HEADLESS
		);
	}
	// Element id's
	public final Locator ID_MESSAGE = byId("message");
	public final Locator ID_BUTTON = byId("button_element");
	public final Locator ID_INPUT = byId("text_element");
	public final Locator ID_SELECT = byId("select_element");
	public final Locator ID_TEXTAREA = byId("textarea_element");
	public final Locator CSS_RADIO_BTN = byId(".selenify)-radio-btn");
	public final Locator ID_CHECKBOX = byId("checkbox1_element");
	public final Locator ID_FORM_SUBMIT_BTN = byId("submit_element");
	public final Locator ID_IMAGE_1 = byId("image1_element");
	public final Locator ID_IMAGE_2 = byId("image2_element");
	public final Locator ID_DIV_1 = byId("div_element");
	public final Locator ID_DIV_2 = byId("div2_element");
	public final Locator ID_DIV_3 = byId("div3_element");
	public final Locator ID_DIV_4 = byId("div4_element");
	private BrowserName browser;

	public SelenifyElementInteractionsTest(BrowserName browser) {
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
			automatedBrowser.clickElement(ID_BUTTON);
			assertEquals("Button Clicked", automatedBrowser.getTextFromElement(ID_MESSAGE));

			// Basic input element
			automatedBrowser.typeToElement(ID_INPUT, "text");
			assertEquals("Text Input Changed", automatedBrowser.getTextFromElement(ID_MESSAGE));

			// Select element
			automatedBrowser.selectOptionByText(ID_SELECT, "Option 2.1");
			assertEquals("Select Changed", automatedBrowser.getTextFromElement(ID_MESSAGE));

			// Textarea element
			automatedBrowser.typeToElement(ID_TEXTAREA, "text");
			assertEquals("Text Area Changed", automatedBrowser.getTextFromElement(ID_MESSAGE));

			// Basic radio button element
			AtomicInteger index = new AtomicInteger(1);
			automatedBrowser.findElements(CSS_RADIO_BTN).forEach(element -> {
				element.click();
				assertEquals(String.format("Radio Button %s Changed", index.get()),
						automatedBrowser.getTextFromElement(ID_MESSAGE));
				index.incrementAndGet();
			});

			// Basic checkbox element
			automatedBrowser.clickElement(ID_CHECKBOX);
			assertEquals("Checkbox Changed", automatedBrowser.getTextFromElement(ID_MESSAGE));

			// Input submit element
			automatedBrowser.clickElement(ID_FORM_SUBMIT_BTN);
			assertEquals("Form Submitted", automatedBrowser.getTextFromElement(ID_MESSAGE));

			// Basic Image element
			automatedBrowser.clickElement(ID_IMAGE_1);
			assertEquals("Image 1 Clicked", automatedBrowser.getTextFromElement(ID_MESSAGE));

			// Broken Image element
			automatedBrowser.clickElement(ID_IMAGE_2);
			assertEquals("Image 2 Clicked", automatedBrowser.getTextFromElement(ID_MESSAGE));

			// Basic Div element
			automatedBrowser.clickElement(ID_DIV_1);
			assertEquals("Div Clicked", automatedBrowser.getTextFromElement(ID_MESSAGE));

			// Basic Div element
			automatedBrowser.clickElement(ID_DIV_2);
			assertEquals("Div 2 Clicked", automatedBrowser.getTextFromElement(ID_MESSAGE));

			// Div element with delay
			automatedBrowser.waitForVisible(ID_DIV_3);
			automatedBrowser.clickElement(ID_DIV_3);
			assertEquals("Div 3 Clicked", automatedBrowser.getTextFromElement(ID_MESSAGE));

//			automatedBrowser.waitForVisible(ID_DIV_4);
//			automatedBrowser.clickElement(ID_DIV_4);
//			assertEquals("Div 2 Clicked", automatedBrowser.getTextFromElement(ID_MESSAGE));
		} finally {
			automatedBrowser.destroy();
		}
	}
}
