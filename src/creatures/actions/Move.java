package creatures.actions;

import creatures.Creature;
import environment.Map;

public class Move implements Action {
    @Override
    public void perform(Creature source, Map map) {
    }

    @Override
    public double getCost() {
        return 0;
    }
}
