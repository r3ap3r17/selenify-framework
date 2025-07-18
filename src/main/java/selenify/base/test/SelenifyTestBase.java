package selenify.base.test;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import selenify.base.component.SelenifyComponentBase;
import selenify.common.constants.BrowserName;
import selenify.common.constants.ReportGeneratorName;
import selenify.common.constants.ReportManagerName;
import selenify.reporting.generated.SelenifyReportGenerator;
import selenify.reporting.generated.decorators.ExtentReportsDecorator;
import selenify.reporting.generated.impl.SelenifyReportGeneratorFactory;
import selenify.reporting.management.SelenifyReportManager;
import selenify.reporting.management.impl.SelenifyReportManagerFactory;
import selenify.utils.screenshot.ScreenshotUtil;

import java.io.File;

public class SelenifyTestBase extends SelenifyComponentBase implements SelenifyReportManager, SelenifyReportGenerator {
	private static final SelenifyReportManagerFactory REPORT_MANAGER = new SelenifyReportManagerFactory();
	private static final SelenifyReportGeneratorFactory REPORT_GENERATOR = new SelenifyReportGeneratorFactory();

	public SelenifyTestBase() {
		setAutomatedBrowser(BrowserName.CHROME_HEADLESS);
		setReportManager(ReportManagerName.TESTRAIL);
		setReportGenerator(ReportGeneratorName.EXTENT_REPORT);
	}

	@AfterClass
	public static void tearDown() {
		ExtentReportsDecorator.flush(); // find a better way to do this
	}

	@Rule
	public TestWatcher _watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			testStarting(description);
			logStart();
		}

		@Override
		protected void finished(Description description) {
			String harPath = null;
			if (getHar() != null) {
				File har = saveHarFile(description.getMethodName() + "_logs.har");
				harPath = har.getAbsolutePath();
			}
			testFinished(description, getBrowserName(), harPath);
			logFinish();
			destroy();
		}

		@Override
		protected void failed(Throwable e, Description description) {
			testFailed(e, description, ScreenshotUtil.saveScreenshot(getScreenshot(), description.getMethodName()));
			markAsFailed();
		}

		@Override
		protected void succeeded(Description description) {
			testPassed(description);
			markAsPassed();
		}
	};
	private SelenifyReportManager selenifyReportManager;
	private SelenifyReportGenerator selenifyReportGenerator;

	@Override
	public void log(String text) {
		getReportManager().log(text);
	}

	@Override
	public void logStart() {
		getReportManager().logStart();
	}

	@Override
	public void logFinish() {
		getReportManager().logFinish();
	}

	@Override
	public void markAsPassed() {
		getReportManager().markAsPassed();
	}

	@Override
	public void markAsFailed() {
		getReportManager().markAsFailed();
	}

	@Override
	public void testStarting(Description description) {
		getReportGenerator().testStarting(description);
	}

	@Override
	public void testPassed(Description description) {
		getReportGenerator().testPassed(description);
	}

	@Override
	public void testFailed(Throwable e, Description description, String screenshotPath) {
		getReportGenerator().testFailed(e, description, screenshotPath);
	}

	@Override
	public void testFinished(Description description, String browserName, String harPath) {
		getReportGenerator().testFinished(description, browserName, harPath);
	}

	private void setReportManager(ReportManagerName reportManager) {
		this.selenifyReportManager = REPORT_MANAGER.getSelenifyReportManager(reportManager, this);
	}

	private void setReportGenerator(ReportGeneratorName reportGenerator) {
		this.selenifyReportGenerator = REPORT_GENERATOR.getSelenifyReportGenerator(reportGenerator, this);
	}

	private SelenifyReportManager getReportManager() {
		return selenifyReportManager;
	}

	private SelenifyReportGenerator getReportGenerator() {
		return selenifyReportGenerator;
	}
}
