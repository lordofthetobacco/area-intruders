import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ShootingHandler implements GameConstants {
    private final Random rand = new Random();
    private final Timer timer = new Timer();
    private TimerTask timerTask = createTimerTask();
    ShootingHandler() {
        timer.scheduleAtFixedRate(timerTask, 10, GameVariables.shootingDelayInSeconds * 1000);
    }


    private TimerTask createTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                int invaderIndex = rand.nextInt(globalInvaders.size());
                globalInvaders.get(invaderIndex).shoot();
            }
        };
    }
}
