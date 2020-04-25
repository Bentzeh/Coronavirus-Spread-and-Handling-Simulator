import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer2Test extends TimerTask {
    public void run() {
        LoggerHandler.getInstance().log(ReportLevel.INFO, "A Current time is: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()));
    }



    public void myTask(){
        Runnable helloRunnable = new Runnable() {
            public void run() {
                System.out.println("Hello world");
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);
    }



    public static void main(String[] args) {
        // And From your main() method or any other method
        Timer timer1 = new Timer("timer1");
        Timer timer2 = new Timer("timer2");
        timer1.schedule(new Timer2Test(), 0, 1000);
        timer2.schedule(new TimerTask(){
            @Override
            public void run() {
                LoggerHandler.getInstance().log(ReportLevel.INFO, "B Current time is: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()));
            }
        }, 0, 2000);
        timer2.schedule(new TimerTask(){
            @Override
            public void run() {
                LoggerHandler.getInstance().log(ReportLevel.INFO, "C Current time is: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()));
            }
        }, 0, 1000);

    }

}


