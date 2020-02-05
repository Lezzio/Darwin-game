package creatures.list;

import creatures.Creature;

public class Rabbit extends Creature {

    //Construct with the appropriate behaviours
    public Rabbit(int params) {
        super(params);
    }

    public String getAddress() {
        return "rabbit_sprite.png";
    }

}
