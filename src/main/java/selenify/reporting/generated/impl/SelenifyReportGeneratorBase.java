package selenify.reporting.generated.impl;

import org.junit.runner.Description;
import selenify.reporting.generated.SelenifyReportGenerator;

public class SelenifyReportGeneratorBase implements SelenifyReportGenerator {
	private SelenifyReportGenerator _selenifyReporterBase;

	public SelenifyReportGeneratorBase() {}

	public SelenifyReportGeneratorBase(SelenifyReportGenerator selenifyReporter) {
		this._selenifyReporterBase = selenifyReporter;
	}

	@Override
	public void testStarting(Description description) {
		getSelenifyReporterBaseBase().testStarting(description);
	}

	@Override
	public void testPassed(Description description) {
		getSelenifyReporterBaseBase().testPassed(description);
	}

	@Override
	public void testFailed(Throwable e, Description description, String screenshotPath) {
		getSelenifyReporterBaseBase().testFailed(e, description, screenshotPath);
	}

	@Override
	public void testFinished(Description description, String browserName, String harPath) {
		getSelenifyReporterBaseBase().testFinished(description, browserName, harPath);
	}

	private SelenifyReportGenerator getSelenifyReporterBaseBase() {
		return _selenifyReporterBase;
	}
}
