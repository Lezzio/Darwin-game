package environment;

import creatures.Creature;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

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

    /**
     * Strict available checking, no obstacle nor creature on the tile
     */
    public boolean isAvailable() {
        boolean obstacle = false;
        Iterator<TileHoldable> iterator = holdables.iterator();
        while(iterator.hasNext() && !obstacle) {
            if (iterator.next().isObstacle()) {
                obstacle = true;
            }
        }
        return getCreature() == null && !obstacle;
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
