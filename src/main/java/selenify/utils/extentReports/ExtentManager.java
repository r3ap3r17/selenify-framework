package selenify.utils.extentReports;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			String reportPath = "target/ExtentReport.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setReportName("Selenify Report");
			reporter.config().setDocumentTitle("Selenify Report");

			extent = new ExtentReports();
			extent.attachReporter(reporter);

			extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Tester", "QAA Engineer");
		}
		return extent;
	}
}
