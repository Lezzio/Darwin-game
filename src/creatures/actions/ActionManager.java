package creatures.actions;

import creatures.Creature;

public class ActionManager {
    /**
     * Always creature.setRunning(false) when action is done
     * @param creature
     * @return
     */
    public static Action getAction(Creature creature) {
        return new RandomMove();
    }
}
