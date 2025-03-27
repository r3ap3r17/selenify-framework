package selenify.base.test;

import selenify.base.component.SelenifyComponentBase;
import selenify.common.constants.ReporterName;
import selenify.reporter.SelenifyReporter;
import selenify.reporter.impl.SelenifyReporterFactory;

public class SelenifyTestBase extends SelenifyComponentBase implements SelenifyReporter {
	private SelenifyReporter selenifyReporter;
	private static final SelenifyReporterFactory REPORTER = new SelenifyReporterFactory();

	private SelenifyReporter getReporter() {
		return selenifyReporter;
	}

	public void setReporter(ReporterName reporter) {
		this.selenifyReporter = REPORTER.getSelenifyReporter(reporter, this);
	}

	@Override
	public void logStart() {
		getReporter().logStart();
	}

	@Override
	public void logFinish() {
		getReporter().logFinish();
	}

	@Override
	public void markAsPassed() {
		getReporter().markAsPassed();
	}

	@Override
	public void markAsFailed() {
		getReporter().markAsFailed();
	}
}
