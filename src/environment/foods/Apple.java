package environment.foods;

import environment.Food;
import javafx.scene.Node;
import rendering.DrawingHandler;

public class Apple extends Food {

    public Apple(int params) {
        super(params);
    }

    @Override
    public String getAddress() {
        return "food_sprite.jpg";
    }
}
