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

public class Map extends Pane {

    private final int tileSize = 48; //To refactor as constructor parameter along with the ground sprite

    private TilePane ground = new TilePane();
    private Group firstPlan = new Group();
    private Group secondPlan = new Group();
    private int row;
    private int col;
    private ArrayList<Creature> creatures = new ArrayList<Creature>();
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
        int j = 0; //Row
        for(int i = 1; i <= col * row; i++) {
            //TODO : Implement Drawable for tiles (Tile)
            final ImageView tileView = new ImageView(texture);
            tileView.setViewport(new Rectangle2D(0, 0, 48, 48)); //Temporary ugly one
            ground.getChildren().add(tileView);
            tiles[i % row][j] = new Tile(new Location(i % row, j));
            if(i % row == 0) {
                j++;
            }
        }
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return row;
    }
    /**
     * Returns true if the Tile was available, false if not
     */
    public boolean addCreature(Creature creature, Location location) {
        Tile tile = getTile(location);
        boolean available = tile.isAvailable();
        if(available) {
            creatures.add(creature);
            tile.add(creature);
            creature.setTile(tile);
            //Add graphics
            Node drawing = creature.getDrawing();
            secondPlan.getChildren().add(drawing);
            drawing.relocate(location.getCol() * tileSize, location.getRow() * tileSize);
        }
        return available;
    }
    public void removeCreature(Creature creature) {
        //TODO remove creature from Tile
        getTile(creature).removeCreature();
        creatures.remove(creature);
        //Remove graphics
        secondPlan.getChildren().remove(creature.getDrawing());
    }
    public ArrayList<Creature> getCreatures() {
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
    public synchronized void move(Creature target, Tile from, Tile to) {
        from.removeCreature();
        to.add(target);
        target.setTile(to);
    }
    public void move(Creature target, Location from, Location to) {
        move(target, getTile(from), getTile(to));
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
