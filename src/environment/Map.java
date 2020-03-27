package environment;

import creatures.Creature;
import rendering.DrawingHandler;
import creatures.list.Rabbit;
import creatures.list.Wolf;
import javafx.scene.Node;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Map extends Pane {

    private final int tileSize = 48; //To refactor as constructor parameter along with the ground sprite

    private TilePane ground = new TilePane();
    private Group firstPlan = new Group();
    private Group secondPlan = new Group();
    private int row;
    private int col;
    private CopyOnWriteArrayList<Creature> creatures = new CopyOnWriteArrayList<Creature>();
    private Tile[][] tiles;

    public Map(double maxWidth, double maxHeight) {
        col = (int) (maxWidth / tileSize);
        row = (int) ((maxHeight - 40) / tileSize); //-40 because we're not full screen
        tiles = new Tile[row][col];
        System.out.println(tiles.length + " and " + tiles[0].length);
        generateGround();
        ground.setPrefRows((int) row);
        ground.setPrefColumns((int) col);
        super.setPrefSize(col * tileSize,row * tileSize);
        super.getChildren().addAll(ground, secondPlan, firstPlan);
    }
    public void generateGround() {
        Image texture = new Image("ground_texture.png");
        //TODO Implement it a lot more easily, just double for and [index] = col + row
        int j = 0; //Row
        for(int i = 1; i <= col * row; i++) {
            //TODO : Implement Drawable for tiles (Tile)
            final ImageView tileView = new ImageView(texture);
            tileView.setViewport(new Rectangle2D(975, 62, 48, 48)); //Temporary ugly one
            ground.getChildren().add(tileView);
            tiles[i % row][j] = new Tile(new Location(i % row, j));
            if(i % row == 0) {
                j++;
            }
        }
    }
    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }
    /**
     * Returns true if the Tile was available, false if not
     */
    public boolean addCreature(Creature creature, Location location) {
        return addCreature(creature, getTile(location));
    }
    public boolean addCreature(Creature creature, Tile tile) {
        boolean available = tile.isAvailable();
        if(available) {
            creatures.add(creature);
            tile.add(creature);
            creature.setTile(tile);
            //Add graphics
            Node drawing = creature.getDrawing();
            secondPlan.getChildren().add(drawing);
            drawing.relocate(tile.getLocation().getCol() * tileSize, tile.getLocation().getRow() * tileSize);
        }
        return available;

    }
    public boolean addFood(Food food, Location location) {
        boolean available = add(food, location);
        if(available) {
            //Add graphics
            Node drawing = food.getDrawing();
            secondPlan.getChildren().add(drawing);
            drawing.relocate(location.getCol() * tileSize, location.getRow() * tileSize);
        }
        return available;
    }
    public boolean add(TileHoldable tileHoldable, Location location) {
        Tile tile = getTile(location);
        boolean available = tile.isAvailable();
        if(available) {
            tile.add(tileHoldable);
            tileHoldable.setTile(tile);
        }
        return available;
    }
    //Add synchronized ?
    public void removeCreature(Creature creature) {
        Tile tile = getTile(creature);
        if(tile != null) {
            tile.remove(creature);
        }
        creatures.remove(creature);
        //Remove graphics
        secondPlan.getChildren().remove(creature.getDrawing());
    }
    public void removeFood(Food food) {
        secondPlan.getChildren().remove(food.getDrawing());
    }
    public CopyOnWriteArrayList<Creature> getCreatures() {
        return creatures;
    }
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }
    public Tile getTile(Location location) {
        return getTile(location.getRow(), location.getCol());
    }
    public Tile getTile(Creature creature) {
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {
                Tile tile = tiles[i][j];
                if(tile.getCreature() == creature) {
                    return tile;
                }
            }
        }
        return null;
    }
    public int getTileSize() {
        return tileSize;
    }

    public boolean isInside(Location loc) {
        return loc.isInside(tiles);
    }
    public void move(Creature target, Tile from, Tile to) {
        from.remove(target);
        to.add(target);
        target.setTile(to);
    }
    public void move(Creature target, Location from, Location to) {
        move(target, getTile(from), getTile(to));
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * @param available : if must be available or not
     * @return
     */
    public Tile getRandomTile(boolean available) {
        Tile tile = null;
        do {
            int randomCol = (int) (Math.random() * getCol());
            int randomRow = (int) (Math.random() * getRow());
            tile = getTile(randomRow, randomCol);
        } while(tile.isAvailable() != available);
        return tile;
    }
}
