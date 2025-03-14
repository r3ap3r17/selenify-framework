package selenify.core.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import selenify.common.constants.Timeouts;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserBase;
import selenify.utils.locators.impl.Locator;
import selenify.utils.locators.impl.LocatorUtil;

import java.time.Duration;
import java.util.List;

public class WebDriverDecorator extends SelenifyBrowserBase {
	private static final Duration DEFAULT_TIMEOUT = Timeouts.MEDIUM;
	private WebDriver webDriver;
	private static final LocatorUtil LOCATOR_UTIL = new LocatorUtil();


	public WebDriverDecorator() {}

	public WebDriverDecorator(final SelenifyBrowser automatedBrowser) {
		super(automatedBrowser);
	}

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

	@Override
	public void setWebDriver(final WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@Override
	public void destroy() {
		if (getWebDriver() != null) {
			getWebDriver().quit();
		}
	}

	@Override
	public void goTo(final String url) {
		getWebDriver().get(url);
	}

	@Override
	public String getCurrentUrl() {
		return getWebDriver().getCurrentUrl();
	}

	@Override
	public WebElement findElement(Locator locator) {
		return getWebDriver().findElement(locator.getBy());
	}

	@Override
	public List<WebElement> findElements(Locator locator) {
		return getWebDriver().findElements(locator.getBy());
	}

	@Override
	public void waitForVisible(Locator locator) {
		LOCATOR_UTIL.getElement(
				getWebDriver(),
				locator.getBy(),
				DEFAULT_TIMEOUT,
				ExpectedConditions::visibilityOfElementLocated);
	}

	@Override
	public void waitForVisible(Locator locator, int waitTime) {
		LOCATOR_UTIL.getElement(
				getWebDriver(),
				locator.getBy(),
				Duration.ofMillis(waitTime),
				ExpectedConditions::visibilityOfElementLocated);
	}

	@Override
	public void waitForPresent(Locator locator) {
		LOCATOR_UTIL.getElement(
				getWebDriver(),
				locator.getBy(),
				DEFAULT_TIMEOUT,
				ExpectedConditions::presenceOfElementLocated);
	}

	@Override
	public void waitForPresent(Locator locator, int waitTime) {
		LOCATOR_UTIL.getElement(
				getWebDriver(),
				locator.getBy(),
				Duration.ofMillis(waitTime),
				ExpectedConditions::presenceOfElementLocated);
	}

	@Override
	public void clickElement(Locator locator) {
		getWebDriver().findElement(locator.getBy()).click();
	}

	@Override
	public void typeToElement(Locator locator, String text) {
		getWebDriver().findElement(locator.getBy()).sendKeys(text);
	}

	@Override
	public String getTextFromElement(Locator locator) {
		return getWebDriver().findElement(locator.getBy()).getText();
	}

	@Override
	public void selectOptionByText(Locator locator, String optionText) {
		new Select(getWebDriver().findElement(locator.getBy())).selectByVisibleText(optionText);
	}
}
