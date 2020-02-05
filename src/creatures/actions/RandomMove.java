package creatures.actions;

import creatures.Creature;
import environment.Map;
import rendering.MovementAnimation;

public class RandomMove implements Action {
    @Override
    public void perform(Creature source, Map map) {
        int rand = 0;
        do {
            rand = (int) (Math.random() * 3) - 1;
        } while(rand == 0);
        System.out.println(rand);
        if(Math.random() > 0.5) {
            MovementAnimation test1 = new MovementAnimation(source, map.getTile(source), map.getTile(map.getTile(source).getLocation().add(rand, 0)));
            test1.perform();
        } else {
            MovementAnimation test1 = new MovementAnimation(source, map.getTile(source), map.getTile(map.getTile(source).getLocation().add(0, rand)));
            test1.perform();
        }
    }

    @Override
    public double getCost() {
        return 0;
    }
}
