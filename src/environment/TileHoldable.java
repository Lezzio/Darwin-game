package environment;

public interface TileHoldable {
    public boolean isObstacle();
    public Tile getTile();
    public void setTile(Tile tile);
}
