package selenify.reporter;

public interface SelenifyReporter {
	void logStart();

	void logFinish();

	void markAsPassed();

	void markAsFailed();
}
