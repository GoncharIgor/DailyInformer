package timers;

import selenium.GismeteoWeather;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AppTimer {
    private GismeteoWeather gismeteo = new GismeteoWeather();
    private Timer timer = new Timer();
    private Calendar today = Calendar.getInstance();

    {
        today.set(Calendar.HOUR_OF_DAY, 7);
        today.set(Calendar.MINUTE, 30);
        today.set(Calendar.SECOND, 0);
    }

    TimerTask timeTask = new TimerTask() {
        public void run() {
            gismeteo.getTodaysWeather();

        }
    };

    // every dat at 7:30am you run your task
    public void go() {
        // timer.schedule(timeTask, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // 60*60*24*100 = 8640000ms
        timer.schedule(timeTask, 500, 5000); // 60*60*24*100 = 8640000ms
    }

}

