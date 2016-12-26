package timers;

import selenium.BaseSeleniumMethod;
import selenium.Dou;
import selenium.Gismeteo;
import selenium.IFinance;
import utils.GoogleMail;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AppTimer {
    private Gismeteo gismeteo = new Gismeteo();
    private IFinance iFinance = new IFinance();
    private Dou dou = new Dou();

    GoogleMail googleMail = new GoogleMail();
    private Timer timer = new Timer();
    private Calendar today = Calendar.getInstance();

    {
        today.set(Calendar.HOUR_OF_DAY, 7);
        today.set(Calendar.MINUTE, 30);
        today.set(Calendar.SECOND, 0);
    }

    TimerTask timeTask = new TimerTask() {
        public void run() {
            List<String> weather = gismeteo.getTodaysWeather();
            List<String> currency = iFinance.getTodaysCurrencyRates();
            List<String> events = dou.getTodaysEventsInKyiv();
            System.out.println(events.toString());
            List<String> colected = BaseSeleniumMethod.collectDataFrom3ArrayLists(weather, currency, events);

            try {
                googleMail.send("IgorGoncharTest", "Test_Test", "IgorGoncharUA@gmail.com", "", "Ужедневная рассылка", colected.toString(), "");
                System.out.println("Email was sent to recipients");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    // every dat at 7:30am you run your task
    public void go() {
        // timer.schedule(timeTask, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // 60*60*24*100 = 8640000ms
        timer.schedule(timeTask, 500, 200000); // 60*60*24*100 = 8640000ms
    }

}

