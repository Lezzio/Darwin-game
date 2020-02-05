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

public class GameManager extends Thread {

    private Map map;
    ExecutorService service = Executors.newCachedThreadPool();

    public GameManager(Map map) {
        this.map = map;
    }

    public void start() {
        //Initialize update animals task
        Rabbit rabbit = new Rabbit(DrawingHandler.NONE);
        Wolf wolf = new Wolf(DrawingHandler.NONE);
        map.addCreature(rabbit, new Location(0, 0));
        map.addCreature(wolf, new Location(0, 1));
        for(int k = 0; k < 12; k++) {
            for(int l = 0; l < 8; l++) {
                Wolf wolf4 = new Wolf(DrawingHandler.NONE);
                map.addCreature(wolf4, new Location(k, l));
            }
        }
        service.submit(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    updateAnimals();
                }
            }
        });
    }

    //Execute action in parallel
    //Block if already running action on the same creature
    public void updateAnimals() {
        for(Creature creature : map.getCreatures()) {
            if (!creature.isRunning()) {
                System.out.println("Calling once for " + creature + " DATA : " +  map.getTile(creature).getLocation() + " |" + map.getTile(creature).getCreature());
                creature.setRunning(true);
                service.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Action action = ActionManager.getAction(creature);
                            int duration = action.perform(creature, map);
                            Thread.sleep(duration + 200);
                            creature.setRunning(false);
                            /*
                            synchronized (creature) {
                                System.out.println("Starting the wait");
                                creature.wait();
                                creature.setRunning(false);
                                System.out.println("Passed the wait");
                            }
                             */
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

}
