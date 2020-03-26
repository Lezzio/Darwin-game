package creatures;

import core.DarwinGame;
import creatures.list.Wolf;
import environment.*;
import javafx.application.Platform;
import rendering.MovementAnimation;

import java.util.ArrayList;
import java.util.Iterator;

public class MovementHandler {

    public static Location getLocationTowards(Location from, Location to) {
        if(from.getCol() != to.getCol()) {
            int diffCol = to.getCol() - from.getCol();
            int unitCol = diffCol/Math.abs(diffCol);
            return from.add(0, unitCol);
        } else if(from.getRow() != to.getRow()) {
            int diffRow = to.getRow() - from.getRow();
            int unitRow = diffRow/Math.abs(diffRow);
            return from.add(unitRow, 0);
        }
        return to;
    }

    public static TileHoldable closestTypeFrom(Map map, Location from, Class<? extends TileHoldable> tileHoldableType) {
        int distance = Integer.MAX_VALUE;
        TileHoldable closestHoldable = null;
        Tile[][] tiles = map.getTiles();
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {
                Tile target = tiles[i][j];
                Iterator<TileHoldable> holdableIterator = target.getHoldables().iterator();
                boolean found = false;
                while(holdableIterator.hasNext() && !found) {
                    TileHoldable tileHoldable = holdableIterator.next();
                    if(tileHoldableType.isInstance(tileHoldable)) {
                        Location to = tileHoldable.getTile().getLocation();
                        int dist = from.distance(to);
                        if(dist < distance) {
                            distance = dist;
                            closestHoldable = tileHoldable;
                        }
                        found = true;
                    }
                }
            }
        }
        return closestHoldable;
    }

    /**
     * @return return false if the movement wasn't possible
     */
    public static boolean move(Creature target, Location from, Location to) {
        MovementType movementType = isPossible(target, to);
        Map map = DarwinGame.map;
        if(movementType == MovementType.POSSIBLE) {
            //Move creature on the map
            map.move(target, from, to);
            //Launch the animation movement
            MovementAnimation.perform(target, from, to);
            return true;
        }
        if(movementType == MovementType.EAT_CREATURE) {
            //Properly remove the eaten creature
            Creature eaten = map.getTile(to).getCreature();
            Platform.runLater(() -> map.removeCreature(eaten));
            //Move creature on the map
            map.move(target, from, to);
            //Launch the animation movement
            MovementAnimation.perform(target, from, to);
            //Add health (edible value)
            target.addHealth(eaten.getValue());
            return true;
        }
        if(movementType == MovementType.EAT_FOOD) {
            //Properly remove the eaten food
            Tile tile = map.getTile(to);
            Food food = tile.getFood();
            Platform.runLater(() -> map.removeFood(food));
            tile.remove(food);
            //Move creature on the map
            map.move(target, from, to);
            //Launch the animation movement
            MovementAnimation.perform(target, from, to);
            return true;
        }
        return false;
    }
    public static MovementType isPossible(Creature source, Location to) {
        MovementType movementType = MovementType.IMPOSSIBLE;
        Map map = DarwinGame.map;
        if(map.isInside(to)) {
            //Available ?
            if(map.getTile(to).isAvailable()) {
                movementType = MovementType.POSSIBLE;
            }
            //Edible creature ?
            Creature target = map.getTile(to).getCreature();
            if(target != null) {
                if (source.dna.diet.contains(target.getClass())) {
                    movementType = MovementType.EAT_CREATURE;
                }
            }
            //Edible food ?
            Food food = map.getTile(to).getFood();
            if(food != null) {
                if(source.dna.diet.contains(food.getClass())) {
                    movementType = MovementType.EAT_FOOD;
                }
            }
        }
        return movementType;
    }
    enum MovementType {
        IMPOSSIBLE, POSSIBLE, EAT_CREATURE, EAT_FOOD
    }
}
