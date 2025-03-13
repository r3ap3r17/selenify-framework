package selenify.common.exceptions;

public class SelenifyWebElementException extends RuntimeException {
	public SelenifyWebElementException() {}

	public SelenifyWebElementException(final String message) {
		super(message);
	}

	public SelenifyWebElementException(final String message, final Throwable ex) {
		super(message, ex);
	}

	public SelenifyWebElementException(final Exception ex) {super(ex);}
}
