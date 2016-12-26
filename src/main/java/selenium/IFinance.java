package selenium;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IFinance extends BaseSeleniumMethod {
    private final String ifinanceIndexPageUrl = "http://finance.i.ua/";
    private By USDBlackMarketBuyPriceLocator = By.xpath(".//div[2]/div/table/tbody/tr[1]/td[1]/span/span[1]");
    private By USDBlackMarketSalePriceLocator = By.xpath(".//div[2]/div/table/tbody/tr[1]/td[2]/span/span[1]");

    public List<String> getTodaysCurrencyRates() {
        List<String> currencyRates = new ArrayList<String>();

        try {
            initialize();
            open(ifinanceIndexPageUrl);

            currencyRates.add("Курсы валют:");
            currencyRates.add("\nПокупка доллара на черном рынке: " + $(USDBlackMarketBuyPriceLocator).getText());
            currencyRates.add("\nПродажа доллара на черном рынке: " + $(USDBlackMarketSalePriceLocator).getText());


        } finally {
            closeDriver();
        }

        return currencyRates;

    }
}
