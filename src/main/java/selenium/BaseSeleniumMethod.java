package selenium;

import org.openqa.selenium.WebDriver;
import utils.WebDriverManager;

import java.util.ArrayList;
import java.util.List;

public class BaseSeleniumMethod {
    protected WebDriver driver;

    public void initialize(){
        driver = WebDriverManager.initializeWebDriver("chrome");
    }

    public void closeDriver(){
        driver.close();
    }

    public static List<String> collectDataFrom3ArrayLists(List<String> weather, List<String> currency, List<String> itEvents,  List<String> cityEvents){
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
