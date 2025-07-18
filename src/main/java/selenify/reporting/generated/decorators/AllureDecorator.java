package selenify.reporting.generated.decorators;

import org.junit.runner.Description;
import selenify.reporting.generated.SelenifyReportGenerator;
import selenify.reporting.generated.impl.SelenifyReportGeneratorBase;

public class AllureDecorator extends SelenifyReportGeneratorBase {

	public AllureDecorator() {}

	public AllureDecorator(final SelenifyReportGenerator selenifyReportGenerator) {
		super(selenifyReportGenerator);
	}

	@Override
	public void testStarting(Description description) {
		System.out.println("#$$$ TEST ALLURE STARTED");
	}

	@Override
	public void testPassed(Description description) {
		System.out.println("#$$$ TEST ALLURE PASSED");
	}

	@Override
	public void testFailed(Throwable e, Description description, String screenshotPath) {
		System.out.println("#$$$ TEST ALLURE FAILED");
	}

	@Override
	public void testFinished(Description description, String browserName, String harPath) {
		System.out.println("#$$$ TEST ALLURE FINISHED");
	}
}
