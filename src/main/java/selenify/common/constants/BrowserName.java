package selenify.common.constants;

public enum BrowserName {
	CHROME("Chrome"), FIREFOX("Firefox"),
	CHROME_HEADLESS("Chrome"), FIREFOX_HEADLESS("Firefox");

	public String name;

	BrowserName(String name) {
		this.name = name;
	}
}
