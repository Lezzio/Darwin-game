package creatures.list;

import creatures.Creature;
import creatures.DNA;
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
        dna.tendencies.put(new RandomMove(), 1.0);
        dna.tendencies.put(new Idle(), 10.0);
        HashMap<Class<? extends TileHoldable>, Double> trackedEntities = new HashMap<>();
        trackedEntities.put(Apple.class, 1.0);
        dna.tendencies.put(new TrackEntity(trackedEntities), 1.0);
        dna.tendenciesParameters.put("idleTime", 250.0);
        dna.diet.add(Apple.class);
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
    @Override
    public Rabbit reproduce() {
        DNA dna = super.mutate();
        return new Rabbit(DrawingHandler.NONE, dna);
    }
}
