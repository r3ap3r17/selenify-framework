package selenify.reporter.decorators;

import org.slf4j.Logger;
import selenify.reporter.SelenifyReporter;
import selenify.reporter.impl.SelenifyReporterBase;
import selenify.utils.logging.LoggerUtils;

public class TestRailDecorator extends SelenifyReporterBase {
	private final Logger logger = LoggerUtils.getLogger(this.getClass());

	public TestRailDecorator() {}

	public TestRailDecorator(final SelenifyReporter selenifyReporter) {
		super(selenifyReporter);
	}

	@Override
	public void markAsFailed() {
		logger.error("TestRail Marked as Failed!");
		// implement logic
	}

	@Override
	public void markAsPassed() {
		logger.info("TestRail Marked as Passed!");
		// implement logic
	}
}
