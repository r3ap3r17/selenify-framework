package selenify.utils.locators;

import selenify.utils.locators.impl.Locator;

public interface LocatorProvider {
	Locator byXpath(String locator);
	Locator byCss(String locator);
	Locator byId(String locator);
	Locator byName(String locator);
	Locator byClass(String locator);
}
