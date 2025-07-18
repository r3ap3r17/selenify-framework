package selenify.reporting.generated.decorators;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.runner.Description;
import selenify.base.test.SelenifyTestBase;
import selenify.reporting.generated.SelenifyReportGenerator;
import selenify.reporting.generated.impl.SelenifyReportGeneratorBase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportsDecorator extends SelenifyReportGeneratorBase {
	private static ExtentReports extent;
	private ExtentTest suite;
	private static ExtentTest test;

	public static void flush() {
		extent.flush();
	}

	private static ExtentReports getInstance() {
		if (extent == null) {
			String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
			String reportPath = "target/ExtentReport_" + timestamp + ".html";

			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setReportName("Selenify Automation Report");
			reporter.config().setDocumentTitle("Selenify Test Report");
			reporter.config().setTheme(Theme.DARK);
			reporter.config().setEncoding("utf-8");
			reporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
			reporter.config().setCss("a.node > span { color: #e9eaec; }");
			reporter.config().setTimelineEnabled(true);
			reporter.config().setTimelineEnabled(true);

			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setAnalysisStrategy(AnalysisStrategy.TEST);

			extent.setSystemInfo("Tester", "QAA Engineer");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		}
		return extent;
	}

	public ExtentReportsDecorator(SelenifyTestBase caller) {
		extent = getInstance();
		suite = extent.createTest(caller.getClass().getSimpleName());
	}

	public ExtentReportsDecorator(SelenifyTestBase caller, SelenifyReportGenerator selenifyReportGenerator) {
		super(selenifyReportGenerator);

		extent = getInstance();
		suite = extent.createTest(caller.getClass().getSimpleName());
	}

	@Override
	public void testStarting(Description description) {
		test = suite.createNode(description.getMethodName());
	}

	@Override
	public void testPassed(Description description) {
		test.pass(description.getMethodName());
	}

	@Override
	public void testFailed(Throwable e, Description description, String screenshotPath) {
		test.fail(description.getMethodName())
				.info(e.getMessage())
				.addScreenCaptureFromPath(screenshotPath);
	}

	@Override
	public void testFinished(Description description, String browserName, String harPath) {
		test.info("Browser: " + browserName);
		if (harPath != null) {
			test.info("Network Log: <a href='" + harPath + "' target='_blank'>HAR File</a>");
		}
	}
}
