package creatures.list;

import creatures.BoundedDouble;
import creatures.Creature;
import creatures.DNA;
import creatures.actions.Idle;
import creatures.actions.RandomMove;
import creatures.actions.TrackEntity;
import environment.TileHoldable;
import rendering.DrawingHandler;

import java.util.HashMap;

public class Wolf extends Creature {

    public Wolf(int params, DNA dna) {
        super(params, dna);
    }
    public Wolf(int params) {
        super(params, new DNA());
        dna.tendencies.put(new RandomMove(), new BoundedDouble(1.0));
        dna.tendencies.put(new Idle(), new BoundedDouble(0.2));
        HashMap<Class<? extends TileHoldable>, BoundedDouble> trackedEntities = new HashMap<>();
        trackedEntities.put(Rabbit.class, new BoundedDouble(1.0));
        dna.tendenciesParameters.put("trackedEntities", trackedEntities);
        dna.tendencies.put(new TrackEntity(), new BoundedDouble(1.0));
        dna.diet.add(Rabbit.class);
        dna.traits.put("speed", new BoundedDouble(500));
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
    @Override
    public Wolf reproduce() {
        DNA dna = super.mutate();
        return new Wolf(DrawingHandler.NONE, dna);
    }
}
