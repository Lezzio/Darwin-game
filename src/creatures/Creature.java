package creatures;

import core.DarwinGame;
import environment.Edible;
import environment.Map;
import environment.Tile;
import environment.TileHoldable;
import javafx.application.Platform;
import javafx.scene.Node;
import rendering.Drawable;
import rendering.DrawingHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public abstract class Creature implements LivingEntity, Edible, Mutable, TileHoldable, Drawable {

    //Attributs
    protected DNA dna;
    private double health = 20.0;

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
    public void addHealth(double health) {
        this.health += health;
    }
    @Override
    public void setHealth(double health) {
        this.health = health;
        if(this.health <= 0.0) {
            die();
        }
    }

    @Override
    public boolean isAlive() {
        return this.health < 0.0;
    }

    @Override
    public void die() {
        Map map = DarwinGame.map;
        Platform.runLater(() -> map.removeCreature(this));
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
        DNA mutatedDNA = dna; //Initialize with default value (same as previous per default)
        try {
            mutatedDNA = dna.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        //Now apply a random varation (mutation) on a one of the DNA elements
        ArrayList<BoundedDouble> boundedDoubles = new ArrayList<>();

        boundedDoubles.addAll(dna.traits.values());
        boundedDoubles.addAll(dna.tendencies.values());

        //Specific parameters
        boundedDoubles.addAll(((HashMap<Class<? extends TileHoldable>, BoundedDouble>) dna.tendenciesParameters.get("trackedEntities")).values());

        return mutatedDNA;
    }
}
