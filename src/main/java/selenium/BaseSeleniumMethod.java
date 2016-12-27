package selenium;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.LoggerManager;
import utils.WebDriverManager;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> collectDataFrom3ArrayLists(List<String> weather, List<String> currency, List<String> itEvents, List<String> cityEvents) {
        List<String> collectedData = new ArrayList<String>();
        collectedData.addAll(weather);
        collectedData.add("\n");
        collectedData.add("\n");
        collectedData.addAll(currency);
        collectedData.add("\n");
        collectedData.add("\n");
        collectedData.addAll(itEvents);
        collectedData.add("\n");
        collectedData.add("\n");
        collectedData.addAll(cityEvents);

        return collectedData;
    }
}
