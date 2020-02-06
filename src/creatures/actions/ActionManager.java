package creatures.actions;

import creatures.Creature;
import creatures.DNA;

public class ActionManager {

    /**
     * Always set the running state of the creature to false when action is done
     * @param creature
     * @return The action to be performed
     */
    public static Action getAction(Creature creature) {
        DNA dna = creature.getDNA();
        RandomList<Action> actions = RandomList.from(dna.tendencies);
        return actions.getRandomElement();
    }
}
