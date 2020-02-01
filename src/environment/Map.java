package environment;

import creatures.Creature;
import creatures.Livable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class Map extends Scene {

    private TilePane pane;
    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private Tile[][] tiles;

    public Map(Parent root) {
        super(root);
    }
}
