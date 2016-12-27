package timers;

import org.apache.logging.log4j.Logger;
import selenium.*;
import utils.GoogleMail;
import utils.LoggerManager;
import utils.Operations;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AppTimer {
    private static final Logger LOGGER = LoggerManager.createLogger();
    private Gismeteo gismeteo = new Gismeteo();
    private IFinance iFinance = new IFinance();
    private Dou dou = new Dou();
    private Vgorode vgorode = new Vgorode();

    private GoogleMail googleMail = new GoogleMail();
    private Timer timer = new Timer();
    private Calendar today = Calendar.getInstance();

    TimerTask timeTask = new TimerTask() {
        public void run() {
            List<String> weather = gismeteo.getTodaysWeather();
            List<String> currency = iFinance.getTodaysCurrencyRates();
            List<String> itEvents = dou.getTodaysITEventsInKyiv();
            List<String> cityEvents = vgorode.getTodaysFreeEventsInKyiv();

            List<String> colected = BaseSeleniumMethod.collectDataFrom3ArrayLists(weather, currency, itEvents, cityEvents);

            try {
                googleMail.send("IgorGoncharTest", "Test_Test", "IgorGoncharUA@gmail.com", "", "Ужедневная рассылка", Operations.extractStringDataFromArralList(colected), "");
                LOGGER.info("Email was sent to recipients");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    // every dat at 7:30am you run your task
    public void go() {
        setTimeForTimerRun(7, 30);
        // timer.schedule(timeTask, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // 60*60*24*100 = 8640000ms
        timer.schedule(timeTask, 500, 200000); // 60*60*24*100 = 8640000ms
    }

    private void setTimeForTimerRun(int houurs, int minutes) {
        today.set(Calendar.HOUR_OF_DAY, houurs);
        today.set(Calendar.MINUTE, minutes);
        today.set(Calendar.SECOND, 0);
    }
}

