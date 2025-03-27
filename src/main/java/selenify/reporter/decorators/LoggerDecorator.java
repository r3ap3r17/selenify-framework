package selenify.reporter.decorators;

import org.slf4j.Logger;
import selenify.base.test.SelenifyTestBase;
import selenify.reporter.impl.SelenifyReporterBase;
import selenify.utils.logging.LoggerUtils;

public class LoggerDecorator extends SelenifyReporterBase {
	private Logger logger;

	public LoggerDecorator() {}

	public LoggerDecorator(SelenifyTestBase caller) {
		super(caller);
		logger = LoggerUtils.getLogger(caller.getClass());
	}

	@Override
	public void logStart() {
		logger.info("Test Started!");
	}

	@Override
	public void logFinish() {
		logger.info("Test Ended!");
	}
}
