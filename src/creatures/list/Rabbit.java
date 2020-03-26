package creatures.list;

import creatures.Attribute;
import creatures.BoundedDouble;
import creatures.Creature;
import creatures.DNA;
import creatures.actions.Action;
import creatures.actions.Idle;
import creatures.actions.RandomMove;
import creatures.actions.TrackEntity;
import environment.Food;
import environment.TileHoldable;
import environment.foods.Apple;
import rendering.DrawingHandler;

import java.util.HashMap;

public class Rabbit extends Creature {

    //Construct with the appropriate behaviours
    public Rabbit(int params, DNA dna) {
        super(params, dna);
    }
    public Rabbit(int params) {
        super(params, new DNA());
        dna.tendencies.put(new RandomMove(), new BoundedDouble(1.0));
        dna.tendencies.put(new Idle(), new BoundedDouble(5.0));
        HashMap<Class<? extends TileHoldable>, BoundedDouble> trackedEntities = new HashMap<>();
        trackedEntities.put(Apple.class, new BoundedDouble(1.0));
        dna.tendenciesParameters.put("trackedEntities", trackedEntities);
        dna.tendencies.put(new TrackEntity(), new BoundedDouble(1.0));
        dna.tendenciesParameters.put("idleTime", new BoundedDouble(250.0, 100, 2000));
        dna.diet.add(Apple.class);
        dna.traits.put("speed", new BoundedDouble(275.0, 150, 1500));
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

    @Override
    public Rabbit reproduce() {
        DNA dna = super.mutate();
        return new Rabbit(DrawingHandler.NONE, dna);
    }
}
