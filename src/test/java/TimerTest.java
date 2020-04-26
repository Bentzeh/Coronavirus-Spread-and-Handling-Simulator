import com.orens.cshs.infra.logger.LoggerHandler;
import com.orens.cshs.infra.logger.ReportLevel;
import com.orens.cshs.infra.utils.TimerUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class TimerTest extends TimerTask {
    public void run() {
        LoggerHandler.getInstance().log(ReportLevel.INFO, "Current time is: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()), ZoneId.systemDefault()));

        long a = this.scheduledExecutionTime();
        long b = TimerUtils.getCurrentTimeStampAsRawLongFromSystem();

        long ans = TimerUtils.getMillisTimeGapInSeconds(a, b);

        System.out.println("Hello World!");
        System.out.println(ans);
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

    public void mtTask2() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture =
                scheduledExecutorService.schedule(new Callable() {
                                                      public Object call() throws Exception {
                                                          System.out.println("Executed!");
                                                          return "Called!";
                                                      }
                                                  },
                        5,
                        TimeUnit.SECONDS);

        System.out.println("result = " + scheduledFuture.get());

        scheduledExecutorService.shutdown();
    }

    public static void main(String[] args) {
        // And From your main() method or any other method
        Timer timer = new Timer();
        timer.schedule(new TimerTest(), 0, 6000);

    }

}


