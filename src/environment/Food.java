package environment;

import javafx.scene.Node;
import rendering.Drawable;
import rendering.DrawingHandler;

public abstract class Food implements TileHoldable, Edible, Drawable {

    //Environment
    private Node drawing;
    private Tile tile;

    public Food(int params) {
        draw(params);
    }
    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public void draw(int param) {
        drawing = DrawingHandler.draw(this, param);
    }

    @Override
    public Node getDrawing() {
        return drawing;
    }
    @Override
    public Tile getTile() {
        return tile;
    }

    @Override
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    @Override
    public int getValue() {
        return 0;
    }
}
