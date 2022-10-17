package se233.chapter6_tdd.model;

import javafx.geometry.Point2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.chapter6_tdd.view.Platform;

import java.util.Random;

public class Food {
    private Point2D position;
    private Random rn;
    private boolean special;
    Logger logger = LoggerFactory.getLogger(Food.class);
    public Food() {
        this.rn = new Random();
        respawn();
    }
    public Food(Point2D position, Boolean special) {
        this.rn = new Random();
        this.special = special;
        this.position = position;
    }
    public void respawn() {
        Point2D prev_position = this.position;
        do {
            this.special = rn.nextBoolean();
            this.position = new Point2D(rn.nextInt(Platform.WIDTH), rn.nextInt(Platform.HEIGHT));
        } while (prev_position == this.position);
        logger.info("food: x:{} y:{}", this.position.getX(), this.position.getY());
    }

    public boolean isSpecial() {
        return this.special;
    }

    public Point2D getPosition() {
        return position;
    }
}
