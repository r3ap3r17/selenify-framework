package selenify.reporting.generated.impl;

import selenify.base.test.SelenifyTestBase;
import selenify.common.constants.ReportGeneratorName;
import selenify.reporting.generated.SelenifyReportGenerator;
import selenify.reporting.generated.decorators.AllureDecorator;
import selenify.reporting.generated.decorators.ExtentReportsDecorator;

public class SelenifyReportGeneratorFactory {
	public SelenifyReportGenerator getSelenifyReportGenerator(ReportGeneratorName reportGeneratorName, SelenifyTestBase caller) {
		return switch (reportGeneratorName) {
			case EXTENT_REPORT -> getExtentReportsGenerator(caller);
			case ALLURE -> getAllureReportsGenerator();
		};
	}

	private static SelenifyReportGenerator getExtentReportsGenerator(SelenifyTestBase caller) {
		return new ExtentReportsDecorator(caller);
	}

	private static SelenifyReportGenerator getAllureReportsGenerator() {
		return new AllureDecorator();
	}
}
