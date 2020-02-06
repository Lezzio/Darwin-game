package creatures.list;

import creatures.Creature;
import creatures.actions.Idle;
import creatures.actions.RandomMove;
import creatures.actions.TrackEntity;

public class Rabbit extends Creature {

    //Construct with the appropriate behaviours
    public Rabbit(int params) {
        super(params);
        dna.tendencies.put(new RandomMove(), 1.0);
        dna.tendencies.put(new Idle(150), 1.0);
    }

    public String getAddress() {
        return "rabbit_sprite.png";
    }

    @Override
    public boolean isObstacle() {
        return false;
    }
}
