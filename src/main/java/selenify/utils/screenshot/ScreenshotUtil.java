package selenify.utils.screenshot;

import org.apache.commons.io.FileUtils;
import selenify.common.constants.FileType;
import selenify.common.exceptions.SelenifyFileSaveException;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
	private static final String DEFAULT_SC_PATH = "target/screenshots/";

	public static String saveScreenshot(File screenshot, String destination, String scName, FileType fileType) {
		String screenshotPath = destination + scName + fileType.name;
		File dest = new File(screenshotPath);

		try {
			FileUtils.copyFile(screenshot, dest);
		} catch (IOException e) {
			throw new SelenifyFileSaveException("Could not save screenshot to " + dest, e);
		}
		return dest.getAbsolutePath();
	}

	public static String saveScreenshot(File screenshot, String scName) {
		return saveScreenshot(screenshot, DEFAULT_SC_PATH, scName, FileType.PNG);
	}
}
