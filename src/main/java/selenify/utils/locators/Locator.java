package selenify.utils.locators;

import org.openqa.selenium.By;

public class Locator {
	private By _by;

	public Locator(By by) {
		this._by = by;
	}

	public By getBy() {
		return this._by;
	}
}
