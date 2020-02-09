package creatures;

import environment.Edible;
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

    public Creature(int params, DNA dna) {
        draw(params);
        this.dna = dna;
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
    @Override
    public void draw(int param) {
        drawing = DrawingHandler.draw(this, param);
    }
    @Override
    public Node getDrawing() {
        return drawing;
    }

    @Override
    public double getHealth() {
        return health;
    }
    @Override
    public void setHealth(double health) {
        this.health = health;
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
        return dna;
    }
}
