package rendering;

import core.DarwinGame;
import creatures.Creature;
import creatures.DNA;
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

    /**
     * IMPORTANT check with isPossible before performing this method!
     * @return
     */
    public static void perform(Creature target, Location from, Location to) {
        DNA dna = target.getDNA();

        //Step 1 - Translation
        double duration = dna.traits.get("speed").getValue();
        TranslateTransition translate = new TranslateTransition();
        int tileSize = DarwinGame.map.getTileSize();
        int col = to.getCol() - from.getCol();
        int row = to.getRow() - from.getRow();
        translate.setDuration(Duration.millis(duration));
        translate.setByX(col * tileSize);
        translate.setByY(row * tileSize);
        translate.setNode(target.getDrawing());
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
        animation.setCycleCount((int) (duration / cycleDuration));

        translate.setOnFinished(event -> {
            target.setRunning(false);
        });
        //Add to the queue on the JavaFX Thread
        Platform.runLater(translate::play);
        Platform.runLater(animation::play);
    }

    //TranslationTransition, setOnClick to display DNA and stats

}
