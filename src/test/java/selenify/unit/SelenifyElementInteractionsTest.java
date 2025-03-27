package selenify.unit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.common.constants.BrowserName;
import selenify.common.exceptions.SelenifyLocatorException;
import selenify.common.exceptions.SelenifyWebElementException;
import selenify.test.SelenifyTestBase;
import selenify.utils.locators.Locator;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class SelenifyElementInteractionsTest extends SelenifyTestBase {
	private static String HTML_PAGE;

	@Parameterized.Parameters(name = "Browser: {0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{BrowserName.CHROME},
				{BrowserName.FIREFOX},
				{BrowserName.CHROME_HEADLESS},
				{BrowserName.FIREFOX_HEADLESS}
		});
	}

	@BeforeClass
	public static void testSetup() throws URISyntaxException {
		HTML_PAGE = Objects.requireNonNull(SelenifyElementInteractionsTest.class.
				getResource("/test-page.html")).toURI().toString();
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
		setAutomatedBrowser(browser);

		init();
		goTo(HTML_PAGE);

		try {
			// Basic button element
			clickElement(ID_BUTTON);
			assertEquals("Button Clicked", getTextFromElement(ID_MESSAGE));

			// Basic input element
			typeToElement(ID_INPUT, "text");
			assertEquals("Text Input Changed", getTextFromElement(ID_MESSAGE));

			// Select element
			selectOptionByText(ID_SELECT, "Option 2.1");
			assertEquals("Select Changed", getTextFromElement(ID_MESSAGE));

			// Textarea element
			typeToElement(ID_TEXTAREA, "text");
			assertEquals("Text Area Changed", getTextFromElement(ID_MESSAGE));

			// Basic radio button element
			AtomicInteger index = new AtomicInteger(1);
			findElements(CSS_RADIO_BTN).forEach(element -> {
				element.click();
				assertEquals(String.format("Radio Button %s Changed", index.get()),
						getTextFromElement(ID_MESSAGE));
				index.incrementAndGet();
			});

			// Basic checkbox element
			clickElement(ID_CHECKBOX);
			assertEquals("Checkbox Changed", getTextFromElement(ID_MESSAGE));

			// Input submit element
			clickElement(ID_FORM_SUBMIT_BTN);
			assertEquals("Form Submitted", getTextFromElement(ID_MESSAGE));

			// Basic Image element
			clickElement(ID_IMAGE_1);
			assertEquals("Image 1 Clicked", getTextFromElement(ID_MESSAGE));

			// Broken Image element
			clickElement(ID_IMAGE_2);
			assertEquals("Image 2 Clicked", getTextFromElement(ID_MESSAGE));

			// Basic Div element
			clickElement(ID_DIV_1);
			assertEquals("Div Clicked", getTextFromElement(ID_MESSAGE));

			// Basic Div element
			clickElement(ID_DIV_2);
			assertEquals("Div 2 Clicked", getTextFromElement(ID_MESSAGE));

			// Div element with delay
			waitForVisible(ID_DIV_3);
			clickElement(ID_DIV_3);
			assertEquals("Div 3 Clicked", getTextFromElement(ID_MESSAGE));

//			waitForVisible(ID_DIV_4);
//			clickElement(ID_DIV_4);
//			assertEquals("Div 2 Clicked", getTextFromElement(ID_MESSAGE));
		} finally {
			destroy();
		}
	}

	@Test
	public void testElementLocatorStrategies() {
		setAutomatedBrowser(browser);
		init();
		goTo(HTML_PAGE);

		try {
			// Basic button element
			waitForVisible(byCss("#button_element"));
			waitForVisible(byXpath("//button[@id='button_element']"));
			waitForVisible(byXpath("//%s[@id='%s']", "button", "button_element"));
			waitForVisible(byName("button_element"));
			waitForVisible(byClass("button_element"));
		} catch (SelenifyWebElementException | SelenifyLocatorException e) {
			fail("Method should not throw SelenifyWebElementException!");
		} finally {
			destroy();
		}
	}
}
