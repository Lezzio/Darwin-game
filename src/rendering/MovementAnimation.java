package rendering;

import core.DarwinGame;
import creatures.Creature;
import environment.Location;
import environment.Tile;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MovementAnimation {

    private TranslateTransition translate;
    private Creature target;
    private Location from;
    private Location to;

    public MovementAnimation(Creature target, Location from, Location to) {
        this.target = target;
        this.from = from;
        this.to = to;
    }
    public boolean isPossible() {
        boolean available = false;
        if(DarwinGame.map.isInside(to)) {
            available = DarwinGame.map.getTile(to).isAvailable();
        }
        return available;
    }

    /**
     * IMPORTANT check with isPossible before performing this method!
     * @return
     */
    public int perform() {
        //Move creature on the map
        DarwinGame.map.move(target, from, to);

        //Step 1 - Translation
        int duration = 50; //Add speed equation;
        translate = new TranslateTransition();
        int tileSize = DarwinGame.map.getTileSize();
        int col = to.getCol() - from.getCol();
        int row = to.getRow() - from.getRow();
        if(col != 0 && row != 0) {
            System.out.println("ERROR 890");
        }
        translate.setDuration(Duration.millis(duration));
        translate.setByX(col * tileSize);
        translate.setByY(row * tileSize);
        translate.setNode(target.getDrawing());
        //translate.play();
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
        //animation.play();

        translate.setOnFinished(event -> {
            target.setRunning(false);
        });
        //Add to the queue on the JavaFX Thread
        Platform.runLater(translate::play);
        Platform.runLater(animation::play);
        /*
        translate.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Calling EVENTHANDLER");
                synchronized (target) {
                    target.notifyAll();
                }
                System.out.println("Notify passed");
            }
        });
         */
        /*
         */
        return duration;
    }
    public boolean isDone() {
        return translate.getStatus().equals(Animation.Status.STOPPED);
    }

    //TranslationTransition, setOnClick to display DNA and stats

}
