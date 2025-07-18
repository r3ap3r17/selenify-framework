package selenify.reporting.management.impl;

import selenify.base.test.SelenifyTestBase;
import selenify.common.constants.ReportManagerName;
import selenify.reporting.management.SelenifyReportManager;
import selenify.reporting.management.decorators.LoggerDecorator;
import selenify.reporting.management.decorators.TestRailDecorator;
import selenify.reporting.management.decorators.XRayDecorator;

public class SelenifyReportManagerFactory {
	public SelenifyReportManager getSelenifyReportManager(final ReportManagerName reporter, SelenifyTestBase caller) {
		return switch (reporter) {
			case XRAY -> getXRayReporter(caller);
			case TESTRAIL -> getTestRailReporter(caller);
		};
	}

	private static SelenifyReportManager getXRayReporter(SelenifyTestBase caller) {
		return new XRayDecorator(new LoggerDecorator(caller));
	}

	private static SelenifyReportManager getTestRailReporter(SelenifyTestBase caller) {
		return new TestRailDecorator(new LoggerDecorator(caller));
	}
}
