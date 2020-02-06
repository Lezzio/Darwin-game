package creatures.actions;

import creatures.Creature;

public class ActionManager {

    /**
     * Always set the running state of the creature to false when action is done
     * @param creature
     * @return The action to be performed
     */
    public static Action getAction(Creature creature) {
        if(Math.random() > 0.5) {
            return new RandomMove();
        } else {
            return new Idle(500);
        }
    }
}
