package utils;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;

/**
 * Created by i.gonchar on 22.12.2016.
 */
public class LoggerManager {
    private static String loggerFilePath = "./target/log.log";

    public static org.apache.logging.log4j.Logger createLogger() {
        org.apache.logging.log4j.Logger logger = LogManager.getRootLogger();
        logger.info("LoggerManager was initialized");
        return logger;
    }

    public static void createLogFile(FirefoxProfile profile) throws IOException {
        File outFile = new File(loggerFilePath);

        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        profile.setPreference("webdriver.log.driver", "DEBUG");
        profile.setPreference("webdriver.log.file", loggerFilePath);
    }

}
