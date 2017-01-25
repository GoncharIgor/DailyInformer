package selenium;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class Korrespondent extends BaseSeleniumMethod {
    private final String korrespondentUrl = "http://korrespondent.net/";
    private By smallArticlesSelector = By.cssSelector(".top-block.main-grid > .article.article_top_small");
    private By smallArticlesTitleSelector = By.cssSelector(".article_top_small .article__title a");
    private By mainArticlesTitleSelector = By.cssSelector(".article.article_top  .article__title a");
    private By mainArticleBodySelector = By.cssSelector(".post-item__text>p");

    public List<String> getTodaysNews() {
        List<String> news = new ArrayList<String>();

        try {
            initialize();
            open(korrespondentUrl);

            news.add("Новости:");
            for (int i = 0; i < $$(smallArticlesSelector).size(); i++) {
                int counter = i + 1;
                news.add("\n" + counter + ". " + $$(smallArticlesSelector).get(i).findElement(smallArticlesTitleSelector).getText());
                news.add("\n" + $$(smallArticlesSelector).get(i).findElement(smallArticlesTitleSelector).getAttribute("href"));
            }

            getMainNews(news);

        } finally {
            closeDriver();
        }
        return news;
    }

    private List<String> getMainNews(List<String> list) {
        list.add("\n\nГлавная новость дня:  ");
        list.add($(mainArticlesTitleSelector).getText());
        $(mainArticlesTitleSelector).click();

        for (int i = 0; i < $$(mainArticleBodySelector).size(); i++) {
            String s = $$(mainArticleBodySelector).get(i).getText().trim();
            if (!s.equals("")) {
                list.add("\n" + s);
            }
        }
        return list;
    }
}
