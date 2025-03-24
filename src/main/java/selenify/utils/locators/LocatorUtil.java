package selenify.utils.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenify.common.exceptions.SelenifyLocatorException;
import selenify.common.exceptions.SelenifyWebElementException;
import selenify.common.functional.LocatorConditionCallback;

import java.time.Duration;
import java.util.MissingFormatArgumentException;

public class LocatorUtil {
	public static WebElement getElement(
			WebDriver webDriver,
			By by,
			int waitTime,
			LocatorConditionCallback expectedConditionCallback) {

		try {
			final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(waitTime));
			final ExpectedCondition<WebElement> condition =
					expectedConditionCallback.getExpectedCondition(by);
			return wait.until(condition);
		} catch (Exception e) {
			throw new SelenifyWebElementException(
					"Failed to find element with locator: " + by.toString(), e);
		}
	}

	public static Locator byXpath(String locator, String... args) {
		return new Locator(By.xpath(formatArgs(locator, args)));
	}

	public static Locator byCss(String locator, String... args) {
		return new Locator(By.cssSelector(formatArgs(locator, args)));
	}

	public static Locator byId(String locator, String... args) {
		return new Locator(By.id(formatArgs(locator, args)));
	}

	public static Locator byName(String locator, String... args) {
		return new Locator(By.name(formatArgs(locator, args)));
	}

	public static Locator byClass(String locator, String... args) {
		return new Locator(By.className(formatArgs(locator, args)));
	}

	private static String formatArgs(String locator, String... args) {
		try {
			return String.format(locator, (Object[]) args);
		} catch (MissingFormatArgumentException e) {
			throw new SelenifyLocatorException(
					"Missing format arguments for locator: " + locator, e);
		}
	}
}
