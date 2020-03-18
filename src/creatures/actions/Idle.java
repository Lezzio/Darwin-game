package creatures.actions;

import creatures.Creature;
import creatures.DNA;
import environment.Map;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class Idle implements Action {

    @Override
    public int perform(Creature source, Map map) {
        DNA dna = source.getDNA();
        if(dna.tendenciesParameters.containsKey("idleTime")) {
            double idleTime = dna.tendenciesParameters.get("idleTime");
            TimerTask task = new TimerTask() {
                public void run() {
                    source.setRunning(false);
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, (long) idleTime);
        } else {
            source.setRunning(false);
        }
        return 0;
    }

    @Override
    public double getCost() {
        return 0.05;
    }
}
