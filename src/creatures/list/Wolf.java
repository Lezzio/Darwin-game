package creatures.list;

import creatures.BoundedDouble;
import creatures.Creature;
import creatures.DNA;
import creatures.actions.Idle;
import creatures.actions.RandomMove;
import creatures.actions.TrackEntity;
import rendering.DrawingHandler;

public class Wolf extends Creature {

    //Construct with the appropriate behaviours
    public Wolf(DNA dna) {
        super(DrawingHandler.NONE, dna, 40.0);
    }
    public Wolf() {
        this(new DNA());
        dna.tendencies.put(new RandomMove(), new BoundedDouble(1.0));
        dna.tendencies.put(new Idle(), new BoundedDouble(1.0));
        dna.trackedEntities.put(Rabbit.class, new BoundedDouble(1.0));
        dna.tendencies.put(new TrackEntity(), new BoundedDouble(1.0));
        dna.tendenciesParameters.put("idleTime", new BoundedDouble(200, 100, 300));
        dna.diet.add(Rabbit.class);
        dna.traits.put("speed", new BoundedDouble(500, 150, 1500));
    }

    public String getAddress() {
        return "wolf_sprite.png";
    }

    @Override
    public int getValue() {
        return 5;
    }
    @Override
    public Wolf reproduce() {
        DNA dna = super.mutate(false);
        return new Wolf(dna);
    }
}
