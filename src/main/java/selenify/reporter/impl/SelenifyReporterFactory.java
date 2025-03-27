package selenify.reporter.impl;

import selenify.base.test.SelenifyTestBase;
import selenify.common.constants.ReporterName;
import selenify.common.exceptions.SelenifyConfigurationException;
import selenify.reporter.SelenifyReporter;
import selenify.reporter.decorators.LoggerDecorator;
import selenify.reporter.decorators.TestRailDecorator;
import selenify.reporter.decorators.XRayDecorator;

public class SelenifyReporterFactory {
	public SelenifyReporter getSelenifyReporter(final ReporterName reporter, SelenifyTestBase caller) {
		switch (reporter) {
			case XRAY -> {
				return getXRayReporter(caller);
			}
			case TESTRAIL -> {
				return getTestRailReporter(caller);
			}
			default -> throw new SelenifyConfigurationException("Unknown Report Service: " + reporter.name);
		}
	}

	private SelenifyReporter getXRayReporter(SelenifyTestBase caller) {
		return new XRayDecorator(new LoggerDecorator(caller));
	}


	private SelenifyReporter getTestRailReporter(SelenifyTestBase caller) {
		return new TestRailDecorator(new LoggerDecorator(caller));
	}
}
