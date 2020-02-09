package creatures.actions;

import core.DarwinGame;
import creatures.Creature;
import creatures.MovementHandler;
import environment.Location;
import environment.Map;
import environment.Tile;
import rendering.MovementAnimation;

public class RandomMove implements Action {

    @Override
    public int perform(Creature source, Map map) {
        Tile tile =  map.getTile(source);
        Location currentLocation = null;
        if(tile != null) {
            currentLocation = tile.getLocation();
        }
        int rand = 0;
        do {
            rand = (int) (Math.random() * 3) - 1;
        } while(rand == 0);
        if(Math.random() > 0.5 && currentLocation != null) {
            if(!MovementHandler.move(source, currentLocation, currentLocation.add(0,rand))) source.setRunning(false);
        } else if(currentLocation != null) {
            if(!MovementHandler.move(source, currentLocation, currentLocation.add(rand,0))) source.setRunning(false);
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
