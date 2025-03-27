package selenify.reporter.impl;

import selenify.reporter.SelenifyReporter;

public class SelenifyReporterBase implements SelenifyReporter {
	private SelenifyReporter _selenifyReporter;

	public SelenifyReporterBase() {}

	public SelenifyReporterBase(SelenifyReporter automatedBrowser) {
		this._selenifyReporter = automatedBrowser;
	}

	private SelenifyReporter getSelenifyReporter() {
		return _selenifyReporter;
	}

	@Override
	public void logStart() {
		getSelenifyReporter().logStart();
	}

	@Override
	public void logFinish() {
		getSelenifyReporter().logFinish();
	}

	@Override
	public void markAsPassed() {
		getSelenifyReporter().markAsPassed();
	}

	@Override
	public void markAsFailed() {
		getSelenifyReporter().markAsFailed();
	}
}
