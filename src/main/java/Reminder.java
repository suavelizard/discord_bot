import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Zane on 12/21/2015.
 */
public class Reminder {
    Timer timer;

    public Reminder(int minutes) {
        timer = new Timer();
        timer.schedule(new RemindTask(), minutes*1000*60);
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
            timer.cancel(); //Terminate the timer thread
        }
    }
}
