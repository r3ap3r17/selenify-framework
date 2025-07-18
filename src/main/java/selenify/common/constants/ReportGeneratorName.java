package selenify.common.constants;

public enum ReportGeneratorName {
	EXTENT_REPORT("ExtentReport"), ALLURE("Allure");

	public String name;

	ReportGeneratorName(String name) {
		this.name = name;
	}
}
