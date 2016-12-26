package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by i.gonchar on 26.12.2016.
 */
public class Vgorode extends BaseSeleniumMethod {
    private String freeKyivEvents = "http://kiev.vgorode.ua/event/free/";
    private String basicLocatorPart = ".//*[@id='isotope']/div";
    private String eventStartLocator = ".//*[@id='isotope']/div[";
    private String eventTitleEndLocator = "]/article/div[2]/a";
    private String eventTimeEndLocator = "]/article/div[3]";


    public List<String> getTodaysFreeEventsInKyiv() {
        List<String> events = new ArrayList<String>();

        try {
            initialize();
            open(freeKyivEvents);
            int amountOfFreeEventsInKyiv = getAmountOfFreeEvents();

            for (int i = 1; i <= amountOfFreeEventsInKyiv; i++) {
                By eventLocatorHeader = By.xpath(eventStartLocator + i + eventTitleEndLocator);
                System.out.println($(eventLocatorHeader).getText());
                // System.out.println($(By.xpath(eventStartLocator + i + eventTimeEndLocator)).getText());

                String a = geNodeText($(By.xpath(eventStartLocator + i + eventTimeEndLocator)));
                System.out.println(a);
            }

        } finally {
            closeDriver();
        }
        return events;
    }

    private int getAmountOfFreeEvents() {
        return $$(By.xpath(basicLocatorPart)).size();
    }

    private static String geNodeText(WebElement element) {
        String text = element.getText();
        for (WebElement child : element.findElements(By.xpath("./*"))) {
            text = text.replaceFirst(child.getText(), "");
            return text;
        }
        return text;
    }

}
