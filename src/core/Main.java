package core;

import creatures.rendering.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        image3.setX(48*6);
        image3.setY(48*12);
        image2.setY(48*5);
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
        TranslateTransition transition = new TranslateTransition();
        transition.setByX(48 * 10);
        transition.setDuration(Duration.seconds(8));
        transition.setNode(image3);
        transition.play();
        TranslateTransition transition2 = new TranslateTransition();
        transition2.setByX(48 * 13);
        transition2.setDuration(Duration.seconds(8));
        transition2.setNode(image2);
        transition2.play();
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        animation2.setCycleCount(Animation.INDEFINITE);
        animation2.play();

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        TilePane tilePane = new TilePane();
        tilePane.setPrefRows((int) (screenHeight / 48));
        tilePane.setPrefColumns((int) (screenWidth / 48));
        primaryStage.setWidth(tilePane.getPrefColumns() * 48);
        primaryStage.setHeight(tilePane.getPrefRows() * 48);
        //Image wolfSprite = new Image("wolf_sprite.png"); //To add in static field on the creature class
        //tileView.setViewport(new Rectangle2D(104, 58, 48, 48)); environment grass
        //tileView.setViewport(new Rectangle2D(975, 62, 48, 48)); grass matched with tallest tree
        //tileView.setViewport(new Rectangle2D(83*2+8, 9, 75, 125)); tallest tree
        Image texture = new Image("ground_texture.png");
        //Max  tiles 819
        for (int i = 0; i < 840; i++) {
            final ImageView tileView = new ImageView(texture);
            tileView.setViewport(new Rectangle2D(975, 62, 48, 48));
            tilePane.getChildren().add(tileView);
        }
        Group pane = new Group();
        Image tree = new Image("trees_sprite.png");
        ImageView treeView = new ImageView(tree);
        treeView.setX(48*8);
        treeView.setY(48*4);
        treeView.setViewport(new Rectangle2D(83*2+8, 9, 75, 125));
        pane.getChildren().add(treeView);
        Scene scene = new Scene(new Group(tilePane, new Group(image2, image3), pane)); //pane being the last one on "z" axis to be displayed 1st layout
        primaryStage.setScene(scene);
        primaryStage.show();
        ImageView treeView2 = new ImageView(tree);
        treeView2.setX(48*7);
        treeView2.setY(48*4);
        treeView2.setViewport(new Rectangle2D(83*2+8, 9, 75, 125));
        pane.getChildren().add(treeView2);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
