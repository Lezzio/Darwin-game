package creatures.actions;

import creatures.Creature;
import environment.Map;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class Idle implements Action {

    private int idleTime;

    public Idle(int idleTime) {
        this.idleTime = idleTime;
    }
    @Override
    public int perform(Creature source, Map map) {
        TimerTask task = new TimerTask() {
            public void run() {
                source.setRunning(false);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, idleTime);
        return 0;
    }

    @Override
    public double getCost() {
        return 0;
    }
}
