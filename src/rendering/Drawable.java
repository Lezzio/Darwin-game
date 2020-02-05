package rendering;

import javafx.scene.Node;

//Generic Drawable?
public interface Drawable {
    public void draw(int param);
    public Node getDrawing();
    public String getAddress();
}
