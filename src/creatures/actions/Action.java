package creatures.actions;

import creatures.Creature;
import environment.Map;

public interface Action {
    public void perform(Creature source, Map map);
    public double getCost();
}
