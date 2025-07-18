package selenify.reporting.generated;

import org.junit.runner.Description;

public interface SelenifyReportGenerator {
	void testStarting(Description description);

	void testPassed(Description description);

	void testFailed(Throwable e, Description description, String screenshotPath);

	void testFinished(Description description, String browserName, String harPath);
}
