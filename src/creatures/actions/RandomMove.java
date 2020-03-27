package creatures.actions;

import creatures.Creature;
import creatures.DNA;
import creatures.MovementHandler;
import environment.Location;
import environment.Map;
import environment.Tile;

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
        return 0;
    }

    @Override
    public double getCost(DNA dna) {
        return 1 + (dna.traits.get("speed").getValue() / 1000);
    }
}
