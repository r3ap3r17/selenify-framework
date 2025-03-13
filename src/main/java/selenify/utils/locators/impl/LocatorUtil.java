package selenify.utils.locators.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenify.common.exceptions.SelenifyWebElementException;
import selenify.utils.locators.ExpectedConditionCallback;
import selenify.utils.locators.LocatorProvider;

import java.time.Duration;

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
			throw new SelenifyWebElementException(e);
		}
	}

	@Override
	public Locator byXpath(String locator) {
		return new Locator(By.xpath(locator));
	}

	@Override
	public Locator byCss(String locator) {
		return new Locator(By.cssSelector(locator));
	}

	@Override
	public Locator byId(String locator) {
		return new Locator(By.id(locator));
	}

	@Override
	public Locator byName(String locator) {
		return new Locator(By.name(locator));
	}

	@Override
	public Locator byClass(String locator) {
		return new Locator(By.className(locator));
	}
}
