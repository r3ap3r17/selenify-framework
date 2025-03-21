package selenify.common.exceptions;

public class SelenifyFileSaveException extends RuntimeException {
	public SelenifyFileSaveException() {
	}

	public SelenifyFileSaveException(final String message) {
		super(message);
	}

	public SelenifyFileSaveException(final Throwable cause) {
		super(cause);
	}

	public SelenifyFileSaveException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
