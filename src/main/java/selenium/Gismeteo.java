package selenium;

import org.openqa.selenium.By;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static utils.Operations.extractNumbersAndSignsFromString;

public class Gismeteo extends BaseSeleniumMethod {

    private final String gismeteoIndexPageUrl = "https://www.gismeteo.ua/weather-kyiv-4944/";
    private final By CURRENT_WEATHER_VALUE_LOCATOR = By.xpath(".//*[@id='weather']/div[3]/div[3]/dl/dd/table/tbody/tr/td");

    private Date date = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String currentFormattedDate = sdf.format(date);

    public List<String> getTodaysWeather() {
        List<String> gismeteoTemperature = new ArrayList<String>();
        try {
            initialize();
            open(gismeteoIndexPageUrl);

            String currentTemperature = $(By.xpath(".//*[@id='weather']/div[3]/div[3]/div[2]/dd[1]")).getText();
            String currentWeather = $(CURRENT_WEATHER_VALUE_LOCATOR).getText();

            gismeteoTemperature.add("Температура:");
            gismeteoTemperature.add("\nСейчас: " + extractNumbersAndSignsFromString(currentTemperature) + ", " + currentWeather);

            String tempNight = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-02']/td[3]/span[1]")).getText();
            String weatherNight = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-02']/td[2]")).getText();

            String tempMorning = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-08']/td[3]/span[1]")).getText();
            String weatherMorning = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-08']/td[2]")).getText();

            String tempDay = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-14']/td[3]/span[1]")).getText();
            String weatherDay = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-14']/td[2]")).getText();

            String tempEvening = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-20']/td[3]/span[1]")).getText();
            String weatherEvening = $(By.xpath(".//*[@id='wrow-" + currentFormattedDate + "-20']/td[2]")).getText();

            gismeteoTemperature.add("\nБыла ночью: " + extractNumbersAndSignsFromString(tempNight) + ", " + weatherNight);
            gismeteoTemperature.add("\nБыла утром: " + extractNumbersAndSignsFromString(tempMorning) + ", " + weatherMorning);
            gismeteoTemperature.add("\nБудет днем: " + extractNumbersAndSignsFromString(tempDay) + ", " + weatherDay);
            gismeteoTemperature.add("\nБудет вечером: " + extractNumbersAndSignsFromString(tempEvening) + ", " + weatherEvening);

        } finally {
            closeDriver();
        }
        return gismeteoTemperature;
    }

}
