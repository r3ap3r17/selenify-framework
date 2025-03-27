package selenify.utils.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}
}
