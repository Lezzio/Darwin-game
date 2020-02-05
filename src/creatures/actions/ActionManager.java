package creatures.actions;

import creatures.Creature;

public class ActionManager {
    public static Action getAction(Creature creature) {
        return new RandomMove();
    }
}
