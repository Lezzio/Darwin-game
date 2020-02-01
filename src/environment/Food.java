package environment;

public class Food implements TileHoldable {
    @Override
    public boolean isObstacle() {
        return false;
    }
}
