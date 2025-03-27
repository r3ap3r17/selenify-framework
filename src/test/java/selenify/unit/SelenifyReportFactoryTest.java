package selenify.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import selenify.base.test.SelenifyTestBase;
import selenify.common.constants.BrowserName;
import selenify.common.constants.ReporterName;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SelenifyReportFactoryTest extends SelenifyTestBase {
	@Parameterized.Parameters(name = "reporter: {0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{ReporterName.XRAY},
				{ReporterName.TESTRAIL},
		});
	}

	private final ReporterName reporter;

	public SelenifyReportFactoryTest(ReporterName reporter) {
		this.reporter = reporter;
	}

	@Test
	public void createReporter() {
		setAutomatedBrowser(BrowserName.CHROME_HEADLESS);
		setReporter(reporter);

		logStart();
		markAsPassed();
		markAsFailed();
		logFinish();
	}
}
