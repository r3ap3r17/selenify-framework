import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageTemplate;
import util.ReadProperties;

public class TestTemplate {
    PageTemplate page = new PageTemplate();

    @BeforeMethod
    public void initDriver() {
        page.openUrl(ReadProperties.readConfigUrl());
    }

    @Test
    public void testTemplateMethod() {
        page.exampleMethod();
    }

    @AfterMethod
    public void closeDriver() {
        page.closeDriver();
    }
}
