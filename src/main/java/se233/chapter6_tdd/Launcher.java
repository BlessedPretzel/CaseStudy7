package se233.chapter6_tdd;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se233.chapter6_tdd.controller.GameLoop;
import se233.chapter6_tdd.model.Food;
import se233.chapter6_tdd.model.Snake;
import se233.chapter6_tdd.view.Platform;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Platform platform = new Platform();
        Snake snake = new Snake(new Point2D(Platform.WIDTH /2, Platform.HEIGHT/2));
        Food food = new Food();
        GameLoop gameLoop = new GameLoop(platform, snake, food);
        Scene scene = new Scene(platform, Platform.WIDTH * Platform.TILE_SIZE, Platform.HEIGHT * Platform.TILE_SIZE);
        scene.setOnKeyPressed(keyEvent -> platform.setKey(keyEvent.getCode()));
        scene.setOnKeyReleased(keyEvent -> platform.setKey(null));
        primaryStage.setTitle("Snek");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        (new Thread(gameLoop)).start();
    }
}
