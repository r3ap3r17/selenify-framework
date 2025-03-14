package selenify.utils.locators;

import selenify.utils.locators.impl.Locator;

public interface LocatorProvider {
	Locator byXpath(String locator, String ...args);
	Locator byCss(String locator, String ...args);
	Locator byId(String locator, String ...args);
	Locator byName(String locator, String ...args);
	Locator byClass(String locator, String ...args);
}
