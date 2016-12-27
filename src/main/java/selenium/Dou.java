package selenium;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by i.gonchar on 26.12.2016.
 */
public class Dou extends BaseSeleniumMethod {

    private final String douCalendarKyivUrl = "https://dou.ua/calendar/city/%D0%9A%D0%B8%D0%B5%D0%B2/";
    private String dayEventsCompoundSelector = (".//article[count(preceding-sibling::div)=");
    private By eventHeader = By.xpath(".//h2/a");
    private By eventPrice = By.cssSelector(".when-and-where > span:nth-child(2)");
    private By eventDescription = By.cssSelector(".when-and-where ~ p");

    public List<String> getTodaysITEventsInKyiv() {
        List<String> events = new ArrayList<String>();

        try {
            initialize();
            open(douCalendarKyivUrl);

            for (int i = 1; i <= 2; i++) {

                if (i == 1) {
                    events.add("ИТ Мероприятия сегодня в Киеве:");
                    events.addAll(getEventsDataPerDay(i));
                } else if (i == 2) {
                    events.add("\n");
                    events.add("\nИТ Мероприятия завтра в Киеве:");
                    events.addAll(getEventsDataPerDay(i));
                }
            }
        } finally {
            closeDriver();
        }
        return events;
    }

    private List<String> getEventsDataPerDay(int dayNumber) {
        List<String> dayEvents = new ArrayList<String>();
        By dayEventsSelector = By.xpath(dayEventsCompoundSelector + dayNumber + "]");

        for (int i = 0; i < $$(dayEventsSelector).size(); i++) {
            int counter = i + 1;
            dayEvents.add("\n" + counter + ". " + $$(dayEventsSelector).get(i).findElement(eventHeader).getText() + " , ЦЕНА: " + $$(dayEventsSelector).get(i).findElement(eventPrice).getText());
            dayEvents.add("\n" + $$(dayEventsSelector).get(i).findElement(eventDescription).getText());
            if ($$(dayEventsSelector).get(i).findElement(eventPrice).getText().equalsIgnoreCase("бесплатно")) {
                dayEvents.add("\n" + $$(dayEventsSelector).get(i).findElement(eventHeader).getAttribute("href"));
            }
        }

        return dayEvents;
    }
}
