package selenify.base.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import selenify.base.component.SelenifyComponentBase;
import selenify.common.constants.ReporterName;
import selenify.reporter.SelenifyReporter;
import selenify.reporter.impl.SelenifyReporterFactory;
import selenify.utils.extentReports.ExtentManager;
import selenify.utils.screenshot.ScreenshotUtil;

import java.io.File;
import java.util.UUID;

public class SelenifyTestBase extends SelenifyComponentBase implements SelenifyReporter {
	protected static ExtentReports extent = ExtentManager.getInstance();
	protected static ExtentTest suite = extent.createTest(SelenifyTestBase.class.getPackage().getName());
	protected static ExtentTest clazz;
	protected static ExtentTest test;
	private static final SelenifyReporterFactory REPORTER = new SelenifyReporterFactory();

	@BeforeClass
	public static void setupExtent() {
		clazz = suite.createNode(SelenifyTestBase.class.getSimpleName());
	}

	@AfterClass
	public static void tearDown() {
		extent.flush();
	}

	// TODO: Implement same decorator pattern to be able to have different reporters
	@Rule
	public TestWatcher _watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			test = clazz.createNode(description.getMethodName());
			setReporter(ReporterName.XRAY);
			logStart();
		}

		@Override
		protected void finished(Description description) {
			test.info("Browser: " + getBrowserName());
			if (getHar() != null) {
				File har = saveHarFile(description.getClassName() + UUID.randomUUID() + "_logs.har");
				test.info("Network Log: <a href='" + har.getAbsolutePath() + "' target='_blank'>HAR File</a>");
			}
			destroy();
			logFinish();
		}

		@Override
		protected void failed(Throwable e, Description description) {
			test.fail("Test failed: " + e.getMessage())
					.addScreenCaptureFromPath(ScreenshotUtil.saveScreenshot(getScreenshot(), description.getMethodName()));
			markAsFailed();
		}

		@Override
		protected void succeeded(Description description) {
			test.pass("Test passed: " + description.getMethodName());
			markAsPassed();
		}
	};
	private SelenifyReporter selenifyReporter;

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

	private SelenifyReporter getReporter() {
		return selenifyReporter;
	}
}
