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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameManager {

    private Map map;

    public GameManager(Map map) {
        this.map = map;
    }

    public void start() {
        for(int k = 12; k < 13; k++) {
            for(int l = 12; l < 13; l++) {
                Rabbit rabbit1 = new Rabbit(DrawingHandler.NONE);
                map.addCreature(rabbit1, new Location(k, l));
            }
        }
        for(int k = 0; k < 1; k++) {
            for(int l = 0; l < 1; l++) {
                Wolf wolf4 = new Wolf(DrawingHandler.NONE);
                map.addCreature(wolf4, new Location(k, l));
            }
        }
        //Initialize update animals and food task
        Thread thread = new Thread(() -> {
            while(true) {
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

    int delayReproduce = 0;
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
                //Apply health cost
                double newHealth = creature.getHealth() - action.getCost();
                creature.setHealth(newHealth);
                //Reproduce & mutate
                if(delayReproduce++ > 150) {
                Creature reproducedCreature = creature.reproduce();
                //Iterate the surrounding tiles
                Location location = creature.getTile().getLocation();
                int row = location.getRow();
                int col = location.getCol();
                Tile lastAvailable = null;
                for(int i = row - 2; i < row; i++) {
                    for(int k = col - 2; k <= col; k++) {
                        //Check for array out of bound exception
                        if(i >= 0 && i < map.getRow() && k >= 0 && k < map.getCol()) {
                            Tile tile = map.getTile(i, k);
                            if(tile.isAvailable()) {
                                lastAvailable = tile;
                            }
                        }
                    }
                }
                if(lastAvailable != null) {
                    Tile finalLastAvailable = lastAvailable;
                    Platform.runLater(() -> map.addCreature(reproducedCreature, finalLastAvailable.getLocation()));
                 }
                    delayReproduce = 0;
                }
            }
        }
    }

    int foodIncrement = 0;
    /**
     * Adds food on the map
     */
    public void updateFood() {
        if(foodIncrement++ > 200) {
            Apple apple = new Apple(DrawingHandler.NONE);
            //Random available tile
            Tile tile = map.getRandomTile(true);
            Platform.runLater(() -> map.addFood(apple, tile.getLocation()));
            foodIncrement = 0;
        }
    }

}
