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
        HashMap<Class<? extends TileHoldable>, Double> trackedEntities = new HashMap<>();
        trackedEntities.put(Rabbit.class, 1.0);
        dna.tendencies.put(new TrackEntity(trackedEntities), 1.0);
    }

    public String getAddress() {
        return "wolf_sprite.png";
    }

    @Override
    public boolean isObstacle() {
        return false;
    }
}
