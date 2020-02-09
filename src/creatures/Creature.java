package creatures;

import environment.Edible;
import environment.Location;
import environment.Tile;
import environment.TileHoldable;
import javafx.scene.Node;
import rendering.Drawable;
import rendering.DrawingHandler;

public abstract class Creature implements Livable, Edible, Mutable, TileHoldable, Drawable {

    //Attributs
    protected DNA dna;
    private double health;

    //Environment
    private Node drawing;
    private Tile tile;
    private boolean running;

    public Creature(int params) {
        draw(params);
        dna = new DNA();
    }

    public Tile getTile() {
        return tile;
    }
    public void setTile(Tile tile) {
        this.tile = tile;
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public void draw(int param) {
        drawing = DrawingHandler.draw(this, param);
    }
    public Node getDrawing() {
        return drawing;
    }
    @Override
    public void reproduce() {

    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public double modifyHealth() {
        return 0;
    }

    @Override
    public double setHealth() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public boolean die() {
        return false;
    }

    @Override
    public DNA getDNA() {
        return dna;
    }

    @Override
    public void setDNA() {
        this.dna = dna;
    }

    @Override
    public DNA mutate() {
        return null;
    }
}
