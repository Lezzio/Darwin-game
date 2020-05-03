package core;

import creatures.Creature;
import creatures.DNA;
import creatures.actions.Action;
import creatures.actions.ActionManager;
import creatures.list.Rabbit;
import creatures.list.Wolf;
import environment.Food;
import environment.Location;
import environment.Map;
import environment.Tile;
import environment.foods.Apple;
import javafx.application.Platform;
import rendering.DrawingHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameManager {

    private Map map;

    public GameManager(Map map) {
        this.map = map;
    }

    public void start() {

        spawnCreature(Wolf.class);
        spawnCreature(Rabbit.class);

        //Initialize update animals and food task
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                    updateAnimals();
                    updateFood();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private int delayReproduce = 0;
    private int delayReproduce_temp = 0;
    private int REPRODUCE_COUNT = 150;
    private int REPRODUCE_COUNT_TEMP = 350;

    /**
     * Execute actions asynchronously
     * Doesn't pass if already running action on the same creature
     */
    public void updateAnimals() {

        HashMap<Class<? extends Creature>, Integer> specieCounts = new HashMap<>();
        specieCounts.put(Rabbit.class, 0);
        specieCounts.put(Wolf.class, 0);
        //Reproduce & mutate condition
        delayReproduce++;
        delayReproduce_temp++;

        //Iterate all the creatures
        for (Creature creature : map.getCreatures()) {
            //Update action
            if (!creature.isRunning()) {
                creature.setRunning(true);
                Action action = ActionManager.getAction(creature);
                action.perform(creature, map);
                //Apply health cost
                double newHealth = creature.getHealth() - action.getCost(creature.getDNA());
                creature.setHealth(newHealth);
            }
            //Check if died previously
            if(!creature.isAlive()) continue;

            //Reproduce with frequency and probability
            if (((creature instanceof Wolf && delayReproduce_temp > REPRODUCE_COUNT_TEMP) || (creature instanceof Rabbit && delayReproduce > REPRODUCE_COUNT)) && Math.random() > 0.66) { //Called every 20ms with a count of 1000 and 0.5 probability
                Creature reproducedCreature = creature.reproduce();
                //Iterate the surrounding tiles
                Location location = creature.getTile().getLocation();
                int row = location.getRow();
                int col = location.getCol();
                Tile lastAvailable = null;
                for (int i = row - 2; i < row; i++) {
                    for (int j = col - 2; j <= col; j++) {
                        //Check for array out of bound exception
                        if (i >= 0 && i < map.getRow() && j >= 0 && j < map.getCol()) {
                            Tile tile = map.getTile(i, j);
                            if (tile.isAvailable()) {
                                lastAvailable = tile;
                            }
                        }
                    }
                }
                if (lastAvailable != null) {
                    Tile finalLastAvailable = lastAvailable;
                    Platform.runLater(() -> map.addCreature(reproducedCreature, finalLastAvailable.getLocation()));
                }
            }
            //Count species
            specieCounts.computeIfPresent(creature.getClass(), (k, v) -> v + 1);
        }
        //Check if a specie is extinct and save it
        specieCounts.forEach((key, value) -> {
            if (value == 0) {
                Platform.runLater(() -> spawnCreature(key));
            }
        });


        //Reset the reproduce state at the end
        if (delayReproduce > REPRODUCE_COUNT) {
            delayReproduce = 0;
        }
        if(delayReproduce_temp > REPRODUCE_COUNT_TEMP) {
            delayReproduce_temp = 0;
        }
    }

    int foodIncrement = 0;

    /**
     * Adds food on the map
     */
    public void updateFood() {
        if (foodIncrement++ > 75) {
            Apple apple = new Apple(DrawingHandler.NONE);
            //Random available tile
            Tile tile = map.getRandomTile(true);
            Platform.runLater(() -> map.addFood(apple, tile.getLocation()));
            foodIncrement = 0;
        }
    }

    public void spawnCreature(Class<? extends Creature> creatureClass) {
        spawnCreature(creatureClass, map.getRandomTile(true));
    }
    public void spawnCreature(Class<? extends Creature> creatureClass, Tile tile) {
        try {
            Creature creature = creatureClass.getDeclaredConstructor().newInstance();
            map.addCreature(creature, tile);
            creature.setDNA(creature.mutate(true));
            System.out.println(creature.getClass().getName() + " : ");
            System.out.println(creature.getHealth());
            System.out.println(creature.getDNA().tendencies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
