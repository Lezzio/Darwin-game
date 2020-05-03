package environment.foods;

import environment.Food;
import javafx.scene.Node;
import rendering.DrawingHandler;

public class Carrot extends Food {

    public Carrot(int params) {
        super(params);
    }

    @Override
    public String getAddress() {
        return "food_sprite.jpg";
    }

    @Override
    public int getValue() {
        return 20;
    }
}
