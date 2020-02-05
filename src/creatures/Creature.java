package creatures;

import javafx.scene.Node;
import rendering.Drawable;
import rendering.DrawingHandler;

public abstract class Creature implements Livable, Mutable, Drawable {

    private int id;
    private Node drawing;
    private DNA dna;
    private double health;

    private boolean running;

    public Creature(int params) {
        draw(params);
    }

    /**
     * Called by the game system to perform a behaviour
     */
    public void update() {

    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public int getId() {
        return id;
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
        return null;
    }

    @Override
    public DNA setDNA() {
        return null;
    }

    @Override
    public DNA mutate() {
        return null;
    }
}
