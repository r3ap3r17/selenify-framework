package selenify.reporting.management.decorators;

import org.slf4j.Logger;
import selenify.base.test.SelenifyTestBase;
import selenify.reporting.management.impl.SelenifyReportManagerBase;
import selenify.utils.logging.LoggerUtils;

public class LoggerDecorator extends SelenifyReportManagerBase {
	private Logger logger;

	public LoggerDecorator(SelenifyTestBase caller) {
		super(caller);
		logger = LoggerUtils.getLogger(caller.getClass());
	}

	@Override
	public void log(String text) {
		logger.info(text);
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
