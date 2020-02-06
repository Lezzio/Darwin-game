package environment;

public class Food implements TileHoldable {
    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public Tile getTile() {
        return null;
    }

    @Override
    public void setTile(Tile tile) {

    }
}
