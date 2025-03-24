package selenify.test.impl;

import selenify.test.SelenifyTest;
import selenify.utils.locators.Locator;
import selenify.utils.locators.LocatorUtil;

public abstract class SelenifyTestBase implements SelenifyTest {
	@Override
	public Locator byXpath(String locator, String... args) {
		return LocatorUtil.byXpath(locator, args);
	}

	@Override
	public Locator byCss(String locator, String... args) {
		return LocatorUtil.byCss(locator, args);
	}

	@Override
	public Locator byId(String locator, String... args) {
		return LocatorUtil.byId(locator, args);
	}

	@Override
	public Locator byName(String locator, String... args) {
		return LocatorUtil.byName(locator, args);
	}

	@Override
	public Locator byClass(String locator, String... args) {
		return LocatorUtil.byClass(locator, args);
	}
}
