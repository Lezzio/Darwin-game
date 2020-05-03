package rendering;

import core.DarwinGame;
import creatures.Creature;
import environment.Food;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Iterator;

public class DrawingHandler {

    //0 BOT 1 LEFT 2 RIGHT 3 TOP
    public final static int NONE = -1;
    public final static int BOT = 0;
    public final static int LEFT = 1;
    public final static int RIGHT = 2;
    public final static int TOP = 3;
    /**
     *
     * @param drawable
     * @param param
     * This param is useful to personalize the drawing of the same drawable for example creatures
     * @return
     */
    public static Node draw(Drawable drawable, int param) {
        ImageView result = null;
        if(drawable instanceof Creature) {
            //If creature, can apply the DNA differences
            Image image = new Image(drawable.getAddress());
            result = new ImageView(image);
            result.setViewport(new Rectangle2D(0, 0, 48, 48));

            //TODO Put into the Creature class after the draw(call) on the returned Node
            result.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Creature target = null;
                    Iterator<Creature> creatureIterator = DarwinGame.map.getCreatures().iterator();
                    while(creatureIterator.hasNext() && target == null) {
                        Creature next = creatureIterator.next();
                        if(next.getDrawing() == mouseEvent.getSource()) {
                            target = next;
                        }
                    }
                    System.out.println(target.getClass().getName() + " : CLICK ");
                    System.out.println(target.getHealth());
                    System.out.println(target.getDNA().tendencies);
                }
            });
        }
        if(drawable instanceof Food) {
            Image image = new Image(drawable.getAddress());
            result = new ImageView((image));
            result.setViewport(new Rectangle2D(4,4,24,24));
        }
        return result;
    }

    /**
     * The orientation corresponds to the multiplier for the sprite image
     * @param directionX
     * @param directionY
     * @return
     */
    public static int getOrientation(int directionX, int directionY) {
        if(directionX == 1) return RIGHT;
        if(directionX == -1) return LEFT;
        if(directionY == 1)  return BOT;
        if(directionY == -1) return TOP;
        return NONE;
    }

    public static int getDnaOffset(Creature target) {
        //TODO Implement this method
        return 0;
    }
}
