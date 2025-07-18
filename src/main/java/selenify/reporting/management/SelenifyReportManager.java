package selenify.reporting.management;

public interface SelenifyReportManager {
	void log(String text);

	void logStart();

	void logFinish();

	void markAsPassed();

	void markAsFailed();
}
