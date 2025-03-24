package selenify.common.exceptions;

public class SelenifyConfigurationException extends RuntimeException {
	public SelenifyConfigurationException() {
	}

	public SelenifyConfigurationException(final String message) {
		super(message);
	}

	public SelenifyConfigurationException(final String message, final Throwable ex) {
		super(message, ex);
	}

	public SelenifyConfigurationException(final Exception ex) {
		super(ex);
	}
}
