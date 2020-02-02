package core;

import creatures.rendering.SpriteAnimation;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public class Main extends Application {

    private static final int COLUMNS  =   3;
    private static final int COUNT    =  3;
    private static final int OFFSET_X =  48 * 3;
    private static final int OFFSET_Y =  48 * 2;
    private static final int WIDTH    = 48;
    private static final int HEIGHT   = 48;
    //48x48

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Darwin Game - Natural Selection");
        //Generate map and start the features
        final ImageView image2 = new ImageView(new Image("wolf_sprite.png"));
        final ImageView image3 = new ImageView(new Image("rabbit_sprite.png"));
        image3.setX(100);
        final Animation animation = new SpriteAnimation(
                image2,
                Duration.millis(500),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        final Animation animation2 = new SpriteAnimation(
                image3,
                Duration.millis(500),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );


        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        animation2.setCycleCount(Animation.INDEFINITE);
        animation2.play();
        primaryStage.setScene(new Scene(new Group(image2, image3)));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
