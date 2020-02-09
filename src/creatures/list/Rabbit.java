package creatures.list;

import creatures.Creature;
import creatures.actions.Idle;
import creatures.actions.RandomMove;
import creatures.actions.TrackEntity;
import environment.Food;
import environment.TileHoldable;

import java.util.HashMap;

public class Rabbit extends Creature {

    //Construct with the appropriate behaviours
    public Rabbit(int params) {
        super(params);
        dna.tendencies.put(new RandomMove(), 10.0);
        dna.tendencies.put(new Idle(300), 1.0);
        HashMap<Class<? extends TileHoldable>, Double> trackedEntities = new HashMap<>();
        trackedEntities.put(Food.class, 1.0);
        dna.tendencies.put(new TrackEntity(trackedEntities), 0.1);
        dna.traits.put("speed", 275);
    }

    public String getAddress() {
        return "rabbit_sprite.png";
    }

    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public int getValue() {
        return 5;
    }
}
