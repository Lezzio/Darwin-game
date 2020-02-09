package environment;

import creatures.Creature;

import java.util.ArrayList;
import java.util.Iterator;

public class Tile {

    private Location location;
    private ArrayList<TileHoldable> holdables = new ArrayList<TileHoldable>();

    public Tile(Location location) {
        this.location = location;
    }

    public void add(TileHoldable holdable) {
        holdables.add(holdable);
    }
    public void remove(TileHoldable holdable) {
        holdables.remove(holdable);
    }
    public Creature getCreature() {
        Object[] objects = holdables.stream().filter(th -> th instanceof Creature).toArray();
        return objects.length > 0 ? (Creature) objects[0] : null;
    }
    public Food getFood() {
        Object[] objects = holdables.stream().filter(th -> th instanceof Food).toArray();
        return objects.length > 0 ? (Food) objects[0] : null;
    }

    /**
     * Strict available checking, no obstacle nor creature/food on the tile
     */
    public boolean isAvailable() {
        return !isObstaclePresent() && !isCreaturePresent() && !isFoodPresent();
    }
    public boolean isObstaclePresent() {
        boolean obstacle = false;
        Iterator<TileHoldable> iterator = holdables.iterator();
        while(iterator.hasNext() && !obstacle) {
            if (iterator.next().isObstacle()) {
                obstacle = true;
            }
        }
        return obstacle;
    }
    public boolean isCreaturePresent() {
        return getCreature() != null;
    }
    public boolean isFoodPresent() {
        return getFood() != null;
    }
    public boolean isEmpty() {
        return holdables.isEmpty();
    }
    public Location getLocation() {
        return location;
    }

    public ArrayList<TileHoldable> getHoldables() {
        return holdables;
    }

}
