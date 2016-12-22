package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Operations;
import utils.WebDriverManager;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by i.gonchar on 22.12.2016.
 */
public class GismeteoWeather {

    private WebDriver driver;
    private static String currentTemperature = "not defined yet";
    private final String gismeteoIndexPageUrl = "https://www.gismeteo.ua/weather-kyiv-4944/";

    public void getTodaysWeather() {
        try {
            driver = WebDriverManager.initializeWebDriver("chrome");
            open(gismeteoIndexPageUrl);
            currentTemperature = $(By.xpath(".//*[@id='weather']/div[3]/div[3]/div[2]/dd[1]")).getText();
            System.out.println(Operations.extractNumbersAndSignsFromString(currentTemperature));
        } finally {
            driver.close();
        }
    }

}
