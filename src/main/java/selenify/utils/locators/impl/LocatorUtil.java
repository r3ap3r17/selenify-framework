package selenify.utils.locators.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenify.common.exceptions.SelenifyLocatorException;
import selenify.common.exceptions.SelenifyWebElementException;
import selenify.utils.locators.ExpectedConditionCallback;
import selenify.utils.locators.LocatorProvider;

import java.time.Duration;
import java.util.MissingFormatArgumentException;

public class LocatorUtil implements LocatorProvider {
	public WebElement getElement(
			WebDriver webDriver,
			By by,
			Duration waitTime,
			ExpectedConditionCallback expectedConditionCallback) {

		try {
			final WebDriverWait wait = new WebDriverWait(webDriver, waitTime);
			final ExpectedCondition<WebElement> condition =
					expectedConditionCallback.getExpectedCondition(by);
			return wait.until(condition);
		} catch (Exception e) {
			throw new SelenifyWebElementException(
					"Failed to find element with locator: " + by.toString(), e);
		}
	}

	@Override
	public Locator byXpath(String locator, String... args) {
		return new Locator(By.xpath(formatArgs(locator, args)));
	}

	@Override
	public Locator byCss(String locator, String... args) {
		return new Locator(By.cssSelector(formatArgs(locator, args)));
	}

	@Override
	public Locator byId(String locator, String... args) {
		return new Locator(By.id(formatArgs(locator, args)));
	}

	@Override
	public Locator byName(String locator, String... args) {
		return new Locator(By.name(formatArgs(locator, args)));
	}

	@Override
	public Locator byClass(String locator, String... args) {
		return new Locator(By.className(formatArgs(locator, args)));
	}

	private String formatArgs(String locator, String... args) {
		try {
			return String.format(locator, (Object[]) args);
		} catch (MissingFormatArgumentException e) {
			throw new SelenifyLocatorException(
					"Missing format arguments for locator: " + locator, e);
		}
	}
}
