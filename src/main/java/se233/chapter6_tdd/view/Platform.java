package se233.chapter6_tdd.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import se233.chapter6_tdd.model.Food;
import se233.chapter6_tdd.model.Score;
import se233.chapter6_tdd.model.Snake;

public class Platform extends Pane {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int TILE_SIZE = 10;
    private Canvas canvas;
    private Alert deadDialog;
    private Score score;
    private KeyCode key;

    public Platform() {
        this.setHeight(TILE_SIZE * HEIGHT);
        this.setWidth(TILE_SIZE * WIDTH);

        score = new Score();

        canvas = new Canvas(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
        this.getChildren().addAll(canvas, score.getScoreLabel());
    }

    public void render(Snake snake, Food food) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,WIDTH*TILE_SIZE,HEIGHT*TILE_SIZE);
        gc.setFill(Color.DARKBLUE);
        snake.getBody().forEach(p -> {
            gc.fillRect(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        });
        if (food.isSpecial())
            gc.setFill(Color.GREEN);
        else
            gc.setFill(Color.RED);
        gc.fillRect(food.getPosition().getX() * TILE_SIZE,food.getPosition().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    // EXERCISE 1
    public void deadDialog() {
        this.deadDialog = new Alert(Alert.AlertType.INFORMATION);

        deadDialog.setTitle("Result");
        deadDialog.setHeaderText("GAME OVER! YOU ARE DEAD");
        deadDialog.setOnCloseRequest(dialogEvent -> System.exit(0));
        deadDialog.showAndWait();
    }

    public KeyCode getKey() {
        return key;
    }

    public Score getScore() {
        return score;
    }

    public void setKey(KeyCode key) {
        this.key = key;
    }
}
