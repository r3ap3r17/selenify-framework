package selenify.reporter.decorators;

import org.slf4j.Logger;
import selenify.reporter.SelenifyReporter;
import selenify.reporter.impl.SelenifyReporterBase;
import selenify.utils.logging.LoggerUtils;

public class XRayDecorator extends SelenifyReporterBase {
	private final Logger logger = LoggerUtils.getLogger(this.getClass());

	public XRayDecorator() {}

	public XRayDecorator(final SelenifyReporter selenifyReporter) {
		super(selenifyReporter);
	}

	@Override
	public void markAsFailed() {
		logger.error("XRay Marked as Failed!");
		// implement logic
	}

	@Override
	public void markAsPassed() {
		logger.info("XRay Marked as Passed!");
		// implement logic
	}
}
