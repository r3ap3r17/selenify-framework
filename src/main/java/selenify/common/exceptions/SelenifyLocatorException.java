package selenify.common.exceptions;

public class SelenifyLocatorException extends RuntimeException {
	public SelenifyLocatorException() {}

	public SelenifyLocatorException(final String message) {super(message);}

	public SelenifyLocatorException(final Exception ex) {super(ex);}

	public SelenifyLocatorException(final String message, final Throwable ex) {super(message, ex);}
}
