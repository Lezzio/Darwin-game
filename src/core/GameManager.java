package core;

import creatures.Creature;
import creatures.actions.Action;
import creatures.actions.ActionManager;
import creatures.list.Rabbit;
import creatures.list.Wolf;
import environment.Food;
import environment.Location;
import environment.Map;
import rendering.DrawingHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameManager {

    private Map map;
    ExecutorService service = Executors.newCachedThreadPool();

    public GameManager(Map map) {
        this.map = map;
    }

    public void start() {
        //Initialize update animals task
        Food food = new Food();
        map.add(food, new Location(20,39));
        Wolf wolf = new Wolf(DrawingHandler.NONE);
        for(int k = 12; k < 19; k++) {
            for(int l = 12; l < 30; l++) {
                Rabbit rabbit1 = new Rabbit(DrawingHandler.NONE);
                map.addCreature(rabbit1, new Location(k, l));
            }
        }
        map.addCreature(wolf, new Location(0, 0));

        for(int k = 0; k < 3; k++) {
            for(int l = 0; l < 3; l++) {
                Wolf wolf4 = new Wolf(DrawingHandler.NONE);
                map.addCreature(wolf4, new Location(k, l));
            }
        }
        Thread thread = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(25);
                    updateAnimals();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    /**
     * Execute actions asynchronously
     * Doesn't pass if already running action on the same creature
     */
    public void updateAnimals() {
        for(Creature creature : map.getCreatures()) {
            if (!creature.isRunning()) {
                creature.setRunning(true);
                Action action = ActionManager.getAction(creature);
                action.perform(creature, map);
            }
        }
    }

}
