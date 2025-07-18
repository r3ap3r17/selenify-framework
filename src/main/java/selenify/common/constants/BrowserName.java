package selenify.common.constants;

public enum BrowserName {
	CHROME("Chrome"), FIREFOX("Firefox"),
	CHROME_HEADLESS("ChromeHeadless"), FIREFOX_HEADLESS("FirefoxHeadless");

	public enum Remote {
		BROWSER_STACK_EDGE("BrowserStackEdge"), BROWSER_STACK_IOS("BrowserStackIOS");

		public String name;

		Remote(String name) {
			this.name = name;
		}
	}

	public String name;

	BrowserName(String name) {
		this.name = name;
	}
}
