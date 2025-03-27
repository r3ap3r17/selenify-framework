package selenify.component;

import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenify.common.constants.BrowserName;
import selenify.common.functional.ProxyRequestModifier;
import selenify.common.functional.ProxyResponseModifier;
import selenify.core.SelenifyBrowser;
import selenify.core.impl.SelenifyBrowserFactory;
import selenify.utils.locators.Locator;
import selenify.utils.locators.LocatorUtil;

import java.io.File;
import java.util.List;

public class SelenifyComponentBase implements SelenifyBrowser {
	private SelenifyBrowser automatedBrowser;
	private static final SelenifyBrowserFactory AUTOMATED_BROWSER_FACTORY
			= new SelenifyBrowserFactory();


	public SelenifyBrowser getAutomatedBrowser() {
		return automatedBrowser;
	}

	public void setAutomatedBrowser(BrowserName browser) {
		this.automatedBrowser = AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
	}

	public void setAutomatedBrowser(BrowserName.Remote browser) {
		this.automatedBrowser = AUTOMATED_BROWSER_FACTORY.getAutomatedBrowser(browser);
	}


	public Locator byXpath(String locator, String... args) {
		return LocatorUtil.byXpath(locator, args);
	}

	public Locator byCss(String locator, String... args) {
		return LocatorUtil.byCss(locator, args);
	}

	public Locator byId(String locator, String... args) {
		return LocatorUtil.byId(locator, args);
	}

	public Locator byName(String locator, String... args) {
		return LocatorUtil.byName(locator, args);
	}

	public Locator byClass(String locator, String... args) {
		return LocatorUtil.byClass(locator, args);
	}

	@Override
	public WebDriver getWebDriver() {
		return getAutomatedBrowser().getWebDriver();
	}

	@Override
	public void setWebDriver(WebDriver webDriver) {
		getAutomatedBrowser().setWebDriver(webDriver);
	}

	@Override
	public DesiredCapabilities getDesiredCapabilities() {
		return getAutomatedBrowser().getDesiredCapabilities();
	}

	public void init() {
		getAutomatedBrowser().init();
	}

	public void destroy() {
		getAutomatedBrowser().destroy();
	}

	@Override
	public void maximizeWindow() {
		getAutomatedBrowser().maximizeWindow();
	}

	public void goTo(String url) {
		getAutomatedBrowser().goTo(url);
	}

	@Override
	public String getCurrentUrl() {
		return getAutomatedBrowser().getCurrentUrl();
	}

	@Override
	public Har getHar() {
		return getAutomatedBrowser().getHar();
	}

	@Override
	public void captureHarFile() {
		getAutomatedBrowser().captureHarFile();
	}

	@Override
	public void captureCompleteHarFile() {
		getAutomatedBrowser().captureCompleteHarFile();
	}

	@Override
	public File saveHarFile(String file) {
		return getAutomatedBrowser().saveHarFile(file);
	}

	@Override
	public File saveHarFile(String fileDir, String file) {
		return getAutomatedBrowser().saveHarFile(fileDir, file);
	}

	@Override
	public void modifyRequest(ProxyRequestModifier modifier) {
		getAutomatedBrowser().modifyRequest(modifier);
	}

	@Override
	public void modifyRequest(String urlRegex, String responseBody) {
		getAutomatedBrowser().modifyRequest(urlRegex, responseBody);
	}

	@Override
	public void modifyResponse(ProxyResponseModifier modifier) {
		getAutomatedBrowser().modifyResponse(modifier);
	}

	@Override
	public void modifyResponse(String urlRegex, int responseCode, String responseBody) {
		getAutomatedBrowser().modifyResponse(urlRegex, responseCode, responseBody);
	}

	@Override
	public void blockRequestTo(String urlRegex, int responseCode) {
		getAutomatedBrowser().blockRequestTo(urlRegex, responseCode);
	}

	@Override
	public WebElement findElement(Locator locator) {
		return getAutomatedBrowser().findElement(locator);
	}

	@Override
	public List<WebElement> findElements(Locator locator) {
		return getAutomatedBrowser().findElements(locator);
	}

	@Override
	public void waitForVisible(Locator locator) {
		getAutomatedBrowser().waitForVisible(locator);
	}

	@Override
	public void waitForVisible(Locator locator, int waitTime) {
		getAutomatedBrowser().waitForVisible(locator, waitTime);
	}

	@Override
	public void waitForPresent(Locator locator) {
		getAutomatedBrowser().waitForPresent(locator);
	}

	@Override
	public void waitForPresent(Locator locator, int waitTime) {
		getAutomatedBrowser().waitForPresent(locator, waitTime);
	}

	@Override
	public String getTextFromElement(Locator locator) {
		return getAutomatedBrowser().getTextFromElement(locator);
	}

	@Override
	public void selectOptionByText(Locator locator, String optionText) {
		getAutomatedBrowser().selectOptionByText(locator, optionText);
	}

	@Override
	public void clickElement(Locator locator) {
		getAutomatedBrowser().clickElement(locator);
	}

	@Override
	public void typeToElement(Locator locator, String text) {
		getAutomatedBrowser().typeToElement(locator, text);
	}
}
