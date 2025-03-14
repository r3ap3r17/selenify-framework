package selenify.common.exceptions;

public class SelenifyLocatorException extends RuntimeException {
	public SelenifyLocatorException() {}

	public SelenifyLocatorException(final String message) {super(message);}

	public SelenifyLocatorException(final String message, final Throwable ex) {super(message, ex);}

	public SelenifyLocatorException(final Exception ex) {super(ex);}
}
