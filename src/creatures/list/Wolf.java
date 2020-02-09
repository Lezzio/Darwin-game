package creatures.list;

import creatures.Creature;
import creatures.actions.Idle;
import creatures.actions.RandomMove;
import creatures.actions.TrackEntity;
import environment.TileHoldable;

import java.util.HashMap;

public class Wolf extends Creature {

    public Wolf(int params) {
        super(params);
        dna.tendencies.put(new RandomMove(), 1.0);
        dna.tendencies.put(new Idle(600), 0.2);
        HashMap<Class<? extends TileHoldable>, Double> trackedEntities = new HashMap<>();
        trackedEntities.put(Rabbit.class, 1.0);
        dna.tendencies.put(new TrackEntity(trackedEntities), 1.0);
        dna.diet.add(Rabbit.class);
        dna.traits.put("speed", 400);
    }

    public String getAddress() {
        return "wolf_sprite.png";
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
