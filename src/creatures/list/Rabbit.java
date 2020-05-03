package creatures.list;

import creatures.BoundedDouble;
import creatures.Creature;
import creatures.DNA;
import creatures.actions.Idle;
import creatures.actions.RandomMove;
import creatures.actions.TrackEntity;
import environment.foods.Carrot;
import rendering.DrawingHandler;

public class Rabbit extends Creature {

    //Construct with the appropriate behaviours
    public Rabbit(DNA dna) {
        super(DrawingHandler.NONE, dna, 30.0);
    }
    public Rabbit() {
        this(new DNA());
        dna.tendencies.put(new RandomMove(), new BoundedDouble(1.0));
        dna.tendencies.put(new Idle(), new BoundedDouble(1.0));
        dna.trackedEntities.put(Carrot.class, new BoundedDouble(1.0));
        dna.tendencies.put(new TrackEntity(), new BoundedDouble(1.0));
        dna.tendenciesParameters.put("idleTime", new BoundedDouble(500, 100, 1000));
        dna.diet.add(Carrot.class);
        dna.traits.put("speed", new BoundedDouble(300.0, 150, 1500));
    }

    public String getAddress() {
        return "rabbit_sprite.png";
    }

    @Override
    public int getValue() {
        return 60;
    }

    @Override
    public Rabbit reproduce() {
        DNA dna = super.mutate(false);
        return new Rabbit(dna);
    }
}
