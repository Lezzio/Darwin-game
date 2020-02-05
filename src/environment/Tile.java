package environment;

import creatures.Creature;

import java.util.ArrayList;
import java.util.Iterator;

public class Tile {

    private Location location;
    private ArrayList<TileHoldable> holdables = new ArrayList<TileHoldable>();
    private Creature creature;

    public Tile(Location location) {
        this.location = location;
    }

    public void add(Creature creature) {
        this.creature = creature;
    }
    public void add(TileHoldable holdable) {
        holdables.add(holdable);
    }
    public void removeCreature() {
        creature = null;
    }
    public void remove(TileHoldable holdable) {
        holdables.remove(holdable);
    }
    public Creature getCreature() {
        return creature;
    }
    public boolean isAvailable() {
        boolean obstacle = false;
        Iterator<TileHoldable> iterator = holdables.iterator();
        while(iterator.hasNext() && !obstacle) {
            if(iterator.next().isObstacle()) {
                obstacle = true;
            }
        }
        return creature == null && !obstacle;
    }
    public boolean isEmpty() {
        return creature == null && holdables.isEmpty();
    }
    public Location getLocation() {
        if(location == null) System.out.println("HERE IT IS BOYYY NULLLLL ZEOPGJZPOEGJZOEGJZOPGJZPOEGJ86846b88r46re8b4e6b4re");
        return location;
    }
}
