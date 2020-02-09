package environment;

public class Food implements TileHoldable {
    private Tile tile;

    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public Tile getTile() {
        return tile;
    }

    @Override
    public void setTile(Tile tile) {
        this.tile = tile;
    }
}
