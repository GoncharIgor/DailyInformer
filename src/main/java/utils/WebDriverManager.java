package utils;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static final org.apache.logging.log4j.Logger LOGGER = Logger.createLogger();

    public static WebDriver initializeWebDriver(String browser) {
        WebDriver driver = null;
        if (browser != null) {
            if (browser.equalsIgnoreCase("firefox")) {
                FirefoxDriverManager.getInstance().setup();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("intl.accept_languages", "en");
                driver = new FirefoxDriver(profile);
            } else if (browser.equalsIgnoreCase("chrome")) {
                ChromeDriverManager.getInstance().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=en");
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("ie")) {
                InternetExplorerDriverManager.getInstance().setup();
                driver = new InternetExplorerDriver();
            }
            WebDriverRunner.setWebDriver(driver);
            setUpWaits(driver);
            LOGGER.info(WordUtils.capitalize(browser) + " browser was initialized");
        } else {
            LOGGER.info("Incorrect browser was passed");
        }
        return driver;
    }

    private static void setUpWaits(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        WebDriverRunner.setWebDriver(driver);
    }

    private static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }
}
