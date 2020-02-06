package creatures;

import core.DarwinGame;
import creatures.list.Wolf;
import environment.Location;
import environment.Map;
import environment.Tile;
import environment.TileHoldable;

import java.util.ArrayList;
import java.util.Iterator;

public class MovementHandler {

    public static Location getLocationTowards(Location from, Location to) {
        if(from.getCol() != to.getCol()) {
            int diffCol = to.getCol() - from.getCol();
            int unitCol = diffCol/Math.abs(diffCol);
            System.out.println("Col diff -> " + unitCol);
            return from.add(0, unitCol);
        } else if(from.getRow() != to.getRow()) {
            int diffRow = to.getRow() - from.getRow();
            int unitRow = diffRow/Math.abs(diffRow);
            System.out.println("Row diff -> " + (unitRow));
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
}
