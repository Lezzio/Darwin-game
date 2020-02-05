package core;

import creatures.Creature;
import creatures.actions.Action;
import creatures.actions.ActionManager;
import creatures.list.Rabbit;
import creatures.list.Wolf;
import environment.Location;
import environment.Map;
import javafx.concurrent.Task;
import rendering.DrawingHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameManager {

    private Map map;

    public GameManager(Map map) {
        this.map = map;
    }

    public void start() {
        //Initialize update animals task
        Rabbit rabbit = new Rabbit(DrawingHandler.NONE);
        Wolf wolf = new Wolf(DrawingHandler.NONE);
        map.addCreature(rabbit, new Location(2, 2));
        map.addCreature(wolf, new Location(2,4));
        updateAnimals();
    }

    //Execute action in parallel
    //Block if already running action on the same creature

    public void updateAnimals() {
        ExecutorService service = Executors.newCachedThreadPool();
        for(Creature creature : map.getCreatures()) {
            System.out.println("Calling once for " + creature);
            Action action = ActionManager.getAction(creature);
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                            action.perform(creature, map);
                            System.out.println("Sent action performance");
                        synchronized (creature) {
                            creature.wait();
                            System.out.println("Passed the wait");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
