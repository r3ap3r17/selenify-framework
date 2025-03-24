package selenify.test;

import selenify.utils.locators.Locator;

public interface SelenifyTest {
	Locator byXpath(String locator, String... args);

	Locator byCss(String locator, String... args);

	Locator byId(String locator, String... args);

	Locator byName(String locator, String... args);

	Locator byClass(String locator, String... args);
}
