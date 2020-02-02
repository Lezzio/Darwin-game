package environment;

import creatures.Creature;
import creatures.Livable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class Map extends TilePane {

    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private Tile[][] tiles;

    public Map(int col, int row) {
        super(col, row);
    }
}
