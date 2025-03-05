package selenify.core.decorators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenify.core.SelenifyBrowser;
import selenify.core.SelenifyBrowserBase;

import java.time.Duration;
import java.util.List;

public class WebDriverDecorator extends SelenifyBrowserBase {
	private static final int DEFAULT_TIMEOUT = 5;
	private WebDriver webDriver;

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
	public WebElement findElementById(String id) {
		return getWebDriver().findElement(By.id(id));
	}

	@Override
	public List<WebElement> findElementsByCss(String cssSelector) {
		return getWebDriver().findElements(By.cssSelector(cssSelector));
	}

	@Override
	public void waitForVisibleById(String id) {
		final WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.id(id))));
	}

	@Override
	public void waitForPresentById(String id) {
		final WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
		wait.until(ExpectedConditions.presenceOfElementLocated((By.id(id))));
	}

	@Override
	public void clickElementById(String id) {
		getWebDriver().findElement(By.id(id)).click();
	}

	@Override
	public void typeToElementById(String id, String text) {
		getWebDriver().findElement(By.id(id)).sendKeys(text);
	}

	@Override
	public String getTextFromElementById(String id) {
		return getWebDriver().findElement(By.id(id)).getText();
	}

	@Override
	public void selectOptionByText(String selectId, String optionText) {
		new Select(getWebDriver().findElement(By.id(selectId))).selectByVisibleText(optionText);
	}
}
