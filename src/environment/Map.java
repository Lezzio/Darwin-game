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
    public synchronized boolean addCreature(Creature creature, Location location) {
        Tile tile = getTile(location);
        boolean available = tile.isAvailable();
        if(available) {
            creatures.add(creature);
            tile.add(creature);
            //Add graphics
            Node drawing = creature.getDrawing();
            secondPlan.getChildren().add(drawing);
            drawing.relocate(location.getCol() * tileSize, location.getRow() * tileSize);
        }
        return available;
    }
    public synchronized void removeCreature(Creature creature) {
        //TODO remove creature from Tile
        getTile(creature).removeCreature();
        creatures.remove(creature);
        //Remove graphics
        secondPlan.getChildren().remove(creature.getDrawing());
    }
    public Creature getCreature(int id) {
        Iterator<Creature> iterator = creatures.iterator();
        boolean found = false;
        Creature creature = null;
        while(iterator.hasNext() || !found) {
            Creature next = iterator.next();
            if(next.getId() == id) {
                found = true;
                creature = next;
            }
        }
        return creature;
    }
    public Creature getCreature(Creature creature) {
        return getCreature(creature.getId());
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
    public void test() {
        /*
        Rabbit rabbit = new Rabbit(DrawingHandler.NONE);
        Wolf wolf = new Wolf(DrawingHandler.NONE);
        this.addCreature(rabbit, new Location(6, 7));
        this.addCreature(wolf, new Location(14,14));
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        int rand = 0;
                        do {
                            rand = (int) (Math.random() * 3) - 1;
                            System.out.println(rand);
                        } while(rand == 0);
                        System.out.println(rand);
                        Movement test1 = new Movement(rabbit, getTile(rabbit), getTile(getTile(rabbit).getLocation().add(0, rand)));
                        Movement test3 = new Movement(wolf, getTile(wolf).getLocation(), getTile(wolf).getLocation().add(rand, 0));
                        test1.perform();
                        test3.perform();
                        Thread.sleep(500);
                        int rand2 = 0;
                        do {
                            rand2 = (int) (Math.random() * 3) - 1;
                        } while(rand2 == 0);
                        Movement test2 = new Movement(rabbit, getTile(rabbit), getTile(getTile(rabbit).getLocation().add(rand2, 0)));
                        Movement test4 = new Movement(wolf, getTile(wolf).getLocation(), getTile(wolf).getLocation().add(0, rand2));
                        test4.perform();
                        test2.perform();
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
         */
        /*
        final ImageView image2 = new ImageView(new Image("wolf_sprite.png"));
        final ImageView image3 = new ImageView(new Image("rabbit_sprite.png"));
        image3.setX(48*0);
        image2.setX(48*0);
        image3.setY(48*(row-1));
        image2.setY(48*0);
        final Animation animation = new SpriteAnimation(
                image2,
                Duration.millis(1000),
                3, 0,
                48*2, 48,48
        );
        final Animation animation2 = new SpriteAnimation(
                image3,
                Duration.millis(500),
                3, 0,
                48*2, 48,48
        );
        TranslateTransition transition = new TranslateTransition();
        transition.setByX(48 * 10);
        transition.setDuration(Duration.seconds(6));
        transition.setNode(image3);
        transition.play();
        TranslateTransition transition2 = new TranslateTransition();
        transition2.setByX(48 * 13);
        transition2.setDuration(Duration.seconds(6));
        transition2.setNode(image2);
        transition2.play();
        animation.setCycleCount(40);
        animation.play();
        animation2.setCycleCount(40);
        animation2.play();
        Image tree = new Image("trees_sprite.png");
        ImageView treeView = new ImageView(tree);
        treeView.setViewport(new Rectangle2D(83*2+8, 9, 75, 125));
        treeView.setX(48*2);
        firstPlan.getChildren().addAll(treeView);
        secondPlan.getChildren().addAll(image2, image3);
         */
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
    }
    public synchronized void move(Creature target, Location from, Location to) {
        move(target, getTile(from), getTile(to));
    }
}
