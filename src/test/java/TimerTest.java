import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerTest extends TimerTask {
    public void run() {
        System.out.println("Hello World!");
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
        Timer timer = new Timer();
        timer.schedule(new TimerTest(), 0, 5000);

    }

}


