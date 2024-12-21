package selenify.unit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.constants.BrowserName;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserFactory;

import static org.junit.Assert.assertEquals;


import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(Parameterized.class)
public class SelenifyElementInteractionsTest {
	private static String HTML_PAGE;
	private BrowserName browser;
	private static final SelenifyBrowserFactory AUTOMATED_BROWSER_FACTORY
			= new SelenifyBrowserFactory();
	// Element id's
	public static final String ID_MESSAGE = "message";
	public static final String ID_BUTTON = "button_element";
	public static final String ID_INPUT = "text_element";
	public static final String ID_SELECT = "select_element";
	public static final String ID_TEXTAREA = "textarea_element";
	public static final String CSS_RADIO_BTN = ".selenify-radio-btn";
	public static final String ID_CHECKBOX = "checkbox1_element";
	public static final String ID_FORM_SUBMIT_BTN = "submit_element";
	public static final String ID_IMAGE_1 = "image1_element";
	public static final String ID_IMAGE_2 = "image2_element";
	public static final String ID_DIV_1 = "div_element";
	public static final String ID_DIV_2 = "div2_element";
	public static final String ID_DIV_3 = "div3_element";
	public static final String ID_DIV_4 = "newdiv_element";

	@BeforeClass
	public static void testSetup() throws URISyntaxException {
		HTML_PAGE = Objects.requireNonNull(SelenifyElementInteractionsTest.class.
				getResource("/test-page.html")).toURI().toString();
	}

	public SelenifyElementInteractionsTest(BrowserName browser) {
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
			automatedBrowser.clickElementById(ID_BUTTON);
			assertEquals("Button Clicked", automatedBrowser.getTextFromElementById(ID_MESSAGE));

			// Basic input element
			automatedBrowser.typeToElementById(ID_INPUT, "text");
			assertEquals("Text Input Changed", automatedBrowser.getTextFromElementById(ID_MESSAGE));

			// Select element
			automatedBrowser.selectOptionByText(ID_SELECT, "Option 2.1");
			assertEquals("Select Changed", automatedBrowser.getTextFromElementById(ID_MESSAGE));

			// Textarea element
			automatedBrowser.typeToElementById(ID_TEXTAREA, "text");
			assertEquals("Text Area Changed", automatedBrowser.getTextFromElementById(ID_MESSAGE));

			// Basic radio button element
			AtomicInteger index = new AtomicInteger(1);
			automatedBrowser.findElementsByCss(CSS_RADIO_BTN).forEach(element -> {
				element.click();
				assertEquals(String.format("Radio Button %s Changed", index.get()),
						automatedBrowser.getTextFromElementById(ID_MESSAGE));
				index.incrementAndGet();
			});

			// Basic checkbox element
			automatedBrowser.clickElementById(ID_CHECKBOX);
			assertEquals("Checkbox Changed", automatedBrowser.getTextFromElementById(ID_MESSAGE));

			// Input submit element
			automatedBrowser.clickElementById(ID_FORM_SUBMIT_BTN);
			assertEquals("Form Submitted", automatedBrowser.getTextFromElementById(ID_MESSAGE));

			// Basic Image element
			automatedBrowser.clickElementById(ID_IMAGE_1);
			assertEquals("Image 1 Clicked", automatedBrowser.getTextFromElementById(ID_MESSAGE));

			// Broken Image element
			automatedBrowser.clickElementById(ID_IMAGE_2);
			assertEquals("Image 2 Clicked", automatedBrowser.getTextFromElementById(ID_MESSAGE));

			// Basic Div element
			automatedBrowser.clickElementById(ID_DIV_1);
			assertEquals("Div Clicked", automatedBrowser.getTextFromElementById(ID_MESSAGE));

			// Basic Div element
			automatedBrowser.clickElementById(ID_DIV_2);
			assertEquals("Div 2 Clicked", automatedBrowser.getTextFromElementById(ID_MESSAGE));

			// Div element with delay
			automatedBrowser.waitForVisibleById(ID_DIV_3);
			automatedBrowser.clickElementById(ID_DIV_3);
			assertEquals("Div 3 Clicked", automatedBrowser.getTextFromElementById(ID_MESSAGE));

//			automatedBrowser.waitForVisibleById(ID_DIV_4);
//			automatedBrowser.clickElementById(ID_DIV_4);
//			assertEquals("Div 2 Clicked", automatedBrowser.getTextFromElementById(ID_MESSAGE));
		} finally {
			automatedBrowser.destroy();
		}
	}
}
