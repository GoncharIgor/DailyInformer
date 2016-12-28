package selenium;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.LoggerManager;
import utils.WebDriverManager;

public class BaseSeleniumMethod {
    protected WebDriver driver;
    protected static final Logger LOGGER = LoggerManager.createLogger();

    public void initialize() {
        driver = WebDriverManager.initializeWebDriver("chrome");
    }

    public void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                LOGGER.error("Failed to close browser. Error message:" + e.getMessage());
            }
        }
    }
}
