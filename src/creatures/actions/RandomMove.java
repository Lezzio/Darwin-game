package creatures.actions;

import core.DarwinGame;
import creatures.Creature;
import environment.Location;
import environment.Map;
import rendering.MovementAnimation;

public class RandomMove implements Action {
    @Override
    public int perform(Creature source, Map map) {
        Location currentLocation =  map.getTile(source).getLocation();
        int rand = 0;
        do {
            rand = (int) (Math.random() * 3) - 1;
        } while(rand == 0);
        if(Math.random() > 0.5) {
            MovementAnimation movementAnimation = new MovementAnimation(source, currentLocation, currentLocation.add(0,rand));
            if(movementAnimation.isPossible()) movementAnimation.perform();
            else source.setRunning(false);
        } else {
            MovementAnimation movementAnimation = new MovementAnimation(source, currentLocation, currentLocation.add(rand,0));
            if(movementAnimation.isPossible()) movementAnimation.perform();
            else source.setRunning(false);
        }
        /*
        int rand = 0;
        do {
            rand = (int) (Math.random() * 3) - 1;
        } while(rand == 0);
        rand = 1;
        int duration = 0;
        if(Math.random() > 0.5) {
            Location loc = map.getTile(source).getLocation().add(rand, 0);
                MovementAnimation test1 = new MovementAnimation(source, map.getTile(source).getLocation(), loc);
            System.out.println("TO " + loc + " IS POSSIBLE ? -> " + test1.isPossible());
            if(test1.isPossible()) {
                duration = test1.perform();
            } else source.setRunning(false);
        } else {
            Location loc = map.getTile(source).getLocation().add(0, rand);
                MovementAnimation test1 = new MovementAnimation(source, map.getTile(source).getLocation(), loc);
            System.out.println("TO " + loc + " IS POSSIBLE ? -> " + test1.isPossible());
                if(test1.isPossible()) {
                    duration = test1.perform();
                } else source.setRunning(false);
        }
         */
        return 0;
    }

    @Override
    public double getCost() {
        return 0;
    }
}
