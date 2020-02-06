package core;

import environment.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DarwinGame extends Application {

    public static Map map;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Darwin Game - Natural Selection");
        //Generate map and start the features

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        System.out.println(screenWidth + " " + screenHeight);
        map = new Map(screenWidth, screenHeight);
        map.setStyle("-fx-background-color:gray;"); //Temporary
        Scene scene = new Scene(map);
        primaryStage.setScene(scene);
        primaryStage.show();
        GameManager gameManager = new GameManager(map);
        gameManager.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}