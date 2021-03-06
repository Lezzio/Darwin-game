package creatures.actions;

import creatures.BoundedDouble;
import creatures.Creature;
import creatures.DNA;
import creatures.MovementHandler;
import environment.Food;
import environment.Location;
import environment.Map;
import environment.TileHoldable;
import rendering.MovementAnimation;

import java.util.HashMap;

public class TrackEntity implements  Action {

    RandomList<Class<? extends TileHoldable>> entities = new RandomList<>();

    /*
    public TrackEntity(HashMap<Class<? extends TileHoldable>, BoundedDouble> weights) {
        //weights.keySet().forEach(tileHoldable -> entities.add(tileHoldable, weights.get(tileHoldable)));
        entities = RandomList.from(weights);
    }
     */
    public TrackEntity() {

    }

    @Override
    public int perform(Creature source, Map map) {
        DNA dna = source.getDNA();
        Class<? extends TileHoldable> tileHoldableType = RandomList.from(dna.trackedEntities).getRandomElement();
        Location from = source.getTile().getLocation();
        TileHoldable tileHoldable = MovementHandler.closestTypeFrom(map, from, tileHoldableType);
        //Check if a trackable entity exists
        if(tileHoldable != null) {
        Location to = tileHoldable.getTile().getLocation();
        Location locationTowards = MovementHandler.getLocationTowards(from, to);
        //TODO Implement at so it gets the faster way even if the first one wasn't possible
        if(MovementHandler.move(source, from, locationTowards)) return 0;
        }
        source.setRunning(false);
        return 0;
    }
    @Override
    public double getCost(DNA dna) {
        return 1 + (dna.traits.get("speed").getValue() / 1000);
    }
}
