package selenify.reporting.management.impl;

import selenify.reporting.management.SelenifyReportManager;

public class SelenifyReportManagerBase implements SelenifyReportManager {
	private SelenifyReportManager _selenifyReportManager;

	public SelenifyReportManagerBase() {}

	public SelenifyReportManagerBase(SelenifyReportManager selenifyReporter) {
		this._selenifyReportManager = selenifyReporter;
	}

	@Override
	public void log(String text) {
		getSelenifyReportManager().log(text);
	}

	@Override
	public void logStart() {
		getSelenifyReportManager().logStart();
	}

	@Override
	public void logFinish() {
		getSelenifyReportManager().logFinish();
	}

	@Override
	public void markAsPassed() {
		getSelenifyReportManager().markAsPassed();
	}

	@Override
	public void markAsFailed() {
		getSelenifyReportManager().markAsFailed();
	}

	private SelenifyReportManager getSelenifyReportManager() {
		return _selenifyReportManager;
	}
}
