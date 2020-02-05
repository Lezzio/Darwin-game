package rendering;

import core.DarwinGame;
import creatures.Creature;
import environment.Location;
import environment.Tile;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MovementAnimation {

    private TranslateTransition translate;
    private Creature target;
    private Tile from;
    private Tile to;

    public MovementAnimation(Creature target, Tile from, Tile to) {
        this.target = target;
        this.from = from;
        this.to = to;
    }
    public MovementAnimation(Creature target, Location from, Location to) {
        this(target, DarwinGame.map.getTile(from), DarwinGame.map.getTile(to));
    }
    public boolean isPossible() {
        return to.isAvailable();
    }
    public void perform() {
        //Step 1 - Translation
        int duration = 500; //Add speed equation;
        translate = new TranslateTransition();
        int tileSize = DarwinGame.map.getTileSize();
        int col = to.getLocation().getCol() - from.getLocation().getCol();
        int row = to.getLocation().getRow() - from.getLocation().getRow();
        translate.setDuration(Duration.millis(duration));
        System.out.println("Translation is " + col + " and + " + row);
        translate.setByX(col * tileSize);
        translate.setByY(row * tileSize);
        translate.setNode(target.getDrawing());
        translate.play();
        //Step 2 - Animation

        //Orientation
        int orientation = DrawingHandler.getOrientation(col, row);
        int dnaOffset = DrawingHandler.getDnaOffset(target);

        //TODO Not forget to add DNA here too on the offsets
        int cycleDuration = 500; //Add speed equation
        final Animation animation = new SpriteAnimation(
                (ImageView) target.getDrawing(),
                Duration.millis(cycleDuration),
                3, 0,
                48*orientation, 48,48
        );
        animation.setCycleCount(duration / cycleDuration);
        animation.play();
        translate.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Calling EVENTHANDLER");
                synchronized (target) {
                    target.notify();
                }
                System.out.println("Notify passed");
            }
        });
    }
    public boolean isDone() {
        return translate.getStatus().equals(Animation.Status.STOPPED);
    }

    //TranslationTransition, setOnClick to display DNA and stats

}
