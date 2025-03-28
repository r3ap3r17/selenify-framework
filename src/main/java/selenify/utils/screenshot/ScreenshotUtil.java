package selenify.utils.screenshot;

import org.apache.commons.io.FileUtils;
import selenify.common.exceptions.SelenifyFileSaveException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
	public static String saveScreenshot(File screensot, String testName) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotPath = "target/screenshots/" + testName + "_" + timestamp + ".png";
		File destination = new File(screenshotPath);

		try {
			FileUtils.copyFile(screensot, destination);
		} catch (IOException e) {
			throw new SelenifyFileSaveException("Could not save screenshot to " + destination, e);
		}
		return destination.getAbsolutePath();
	}
}
