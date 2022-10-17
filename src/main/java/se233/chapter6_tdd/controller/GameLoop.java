package se233.chapter6_tdd.controller;

import javafx.scene.input.KeyCode;
import se233.chapter6_tdd.model.Direction;
import se233.chapter6_tdd.model.Food;
import se233.chapter6_tdd.model.Score;
import se233.chapter6_tdd.model.Snake;
import se233.chapter6_tdd.view.Platform;

public class GameLoop implements Runnable {
    private Platform platform;
    private Snake snake;
    private Food food;
    private Score score;
    private float interval = 1000.f / 10;
    private boolean running;

    public GameLoop(Platform platform, Snake snake, Food food) {
        this.platform = platform;
        this.snake = snake;
        this.food = food;
        this.score = platform.getScore();
        running = true;
    }

    private void update() {
        KeyCode cur_key = platform.getKey();
        Direction cur_direction = snake.getCurrentDirection();
        if (cur_key == KeyCode.UP && cur_direction != Direction.DOWN)
            snake.setCurrentDirection(Direction.UP);
        else if (cur_key == KeyCode.DOWN && cur_direction != Direction.UP)
            snake.setCurrentDirection(Direction.DOWN);
        else if (cur_key == KeyCode.LEFT && cur_direction != Direction.RIGHT)
            snake.setCurrentDirection(Direction.LEFT);
        else if (cur_key == KeyCode.RIGHT && cur_direction != Direction.LEFT)
            snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
    }
    private void checkCollision() {
        if (snake.isCollidingWith(food)) {
            if (food.isSpecial()) {
                javafx.application.Platform.runLater(() -> score.plusFive());
            }
            else {
                javafx.application.Platform.runLater(() -> score.plusOne());
            }
            snake.grow();
            food.respawn();
        }
        if (snake.isDead()) {
            running = false;
            javafx.application.Platform.runLater(() -> platform.deadDialog());
        }
    }
    private void redraw() {
        platform.render(snake, food);
    }
    @Override
    public void run() {
        while (running) {
            update();
            checkCollision();
            redraw();
            try {
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Platform getPlatform() {
        return platform;
    }

    public Snake getSnake() {
        return snake;
    }
}
