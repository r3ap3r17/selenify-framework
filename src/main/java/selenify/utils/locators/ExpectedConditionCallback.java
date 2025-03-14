package selenify.utils.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

@FunctionalInterface
public interface ExpectedConditionCallback {
	ExpectedCondition<WebElement> getExpectedCondition(By by);
}
