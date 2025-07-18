package selenify.reporting.management.decorators;

import org.slf4j.Logger;
import selenify.reporting.management.SelenifyReportManager;
import selenify.reporting.management.impl.SelenifyReportManagerBase;
import selenify.utils.logging.LoggerUtils;

public class TestRailDecorator extends SelenifyReportManagerBase {
	private final Logger logger = LoggerUtils.getLogger(this.getClass());

	public TestRailDecorator() {}

	public TestRailDecorator(final SelenifyReportManager selenifyReporter) {
		super(selenifyReporter);
	}

	@Override
	public void markAsFailed() {
		log("TestRail Marked as Failed!");
		// implement logic
	}

	@Override
	public void markAsPassed() {
		log("TestRail Marked as Passed!");
		// implement logic
	}
}
