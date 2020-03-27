package creatures.actions;

import creatures.Creature;
import creatures.DNA;
import environment.Map;

public interface Action {
    public int perform(Creature source, Map map);
    public double getCost(DNA dna);
}
