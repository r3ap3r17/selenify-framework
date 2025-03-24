package selenify.common.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

@FunctionalInterface
public interface LocatorConditionCallback {
	ExpectedCondition<WebElement> getExpectedCondition(By by);
}
