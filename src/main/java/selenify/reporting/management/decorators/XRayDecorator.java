package selenify.reporting.management.decorators;

import org.slf4j.Logger;
import selenify.reporting.management.SelenifyReportManager;
import selenify.reporting.management.impl.SelenifyReportManagerBase;
import selenify.utils.logging.LoggerUtils;

public class XRayDecorator extends SelenifyReportManagerBase {
	private final Logger logger = LoggerUtils.getLogger(this.getClass());

	public XRayDecorator() {}

	public XRayDecorator(final SelenifyReportManager selenifyReporter) {
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
