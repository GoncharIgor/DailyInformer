package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Pair;
import utils.WebDriverManager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static utils.Operations.extractNumbersAndSignsFromString;

/**
 * Created by i.gonchar on 22.12.2016.
 */
public class GismeteoWeather {

    private WebDriver driver;
    private static String currentTemperature = "not defined yet";
    private final String gismeteoIndexPageUrl = "https://www.gismeteo.ua/weather-kyiv-4944/";
    private final By CURRENT_WEATHER_VALUE_LOCATOR = By.xpath(".//*[@id='weather']/div[3]/div[3]/dl/dd/table/tbody/tr/td");

    private Date date = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String currentFormattedDate = sdf.format(date);

    public Pair<List<String>, List<String>> getTodaysWeather() {
        List<String> gismeteoTemperature = new ArrayList<String>();
        List<String> gismeteoWeather = new ArrayList<String>();
        try {
            driver = WebDriverManager.initializeWebDriver("chrome");
            open(gismeteoIndexPageUrl);

            currentTemperature = $(By.xpath(".//*[@id='weather']/div[3]/div[3]/div[2]/dd[1]")).getText();
            String currentWeather = $(CURRENT_WEATHER_VALUE_LOCATOR).getText();

            gismeteoTemperature.add("Температура сейчас: " + extractNumbersAndSignsFromString(currentTemperature));
            gismeteoWeather.add("Погода сейчас: " + currentWeather);

            String tempNight = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-02']/td[3]/span[1]")).getText();
            String weatherNight = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-02']/td[2]")).getText();

            String tempMorning = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-08']/td[3]/span[1]")).getText();
            String weatherMorning = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-08']/td[2]")).getText();

            String tempDay = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-14']/td[3]/span[1]")).getText();
            String weatherDay = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-14']/td[2]")).getText();

            String tempEvening = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-20']/td[3]/span[1]")).getText();
            String weatherEvening = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-20']/td[2]")).getText();


            gismeteoTemperature.add("\nБыла ночью: " + extractNumbersAndSignsFromString(tempNight));
            gismeteoTemperature.add("\nБыла утром: " + extractNumbersAndSignsFromString(tempMorning));
            gismeteoTemperature.add("\nБудет днем: " + extractNumbersAndSignsFromString(tempDay));
            gismeteoTemperature.add("\nБудет вечером: " + extractNumbersAndSignsFromString(tempEvening));

            gismeteoWeather.add(weatherNight);
            gismeteoWeather.add(weatherMorning);
            gismeteoWeather.add(weatherDay);
            gismeteoWeather.add(weatherEvening);

        } finally {
            driver.close();
        }
        return new Pair<List<String>, List<String>>(gismeteoTemperature, gismeteoWeather);
    }

}
