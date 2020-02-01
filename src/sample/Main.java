package sample;

import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import environment.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Darwin Game - Natural Selection");
        //Generate map and start the features

    }


    public static void main(String[] args) {
        launch(args);
    }
}
