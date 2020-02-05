package creatures.actions;

import creatures.Creature;
import environment.Map;

public class FeedCarnivore implements Action {
    @Override
    public int perform(Creature source, Map map) {
        return 0;
    }

    @Override
    public double getCost() {
        return 0;
    }
}
