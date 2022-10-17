package se233.chapter6_tdd;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import se233.chapter6_tdd.controller.GameLoop;
import se233.chapter6_tdd.model.Food;
import se233.chapter6_tdd.model.Snake;
import se233.chapter6_tdd.view.Platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class GameLoopTest {
    private GameLoop gameLoopUnderTest;
    private Method update, collision, redraw;

    @Before
    public void init() throws NoSuchMethodException {
        JFXPanel jfxPanel = new JFXPanel();
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0, 0)), new Food());
        update = GameLoop.class.getDeclaredMethod("update");
        update.setAccessible(true);
        collision = GameLoop.class.getDeclaredMethod("checkCollision");
        collision.setAccessible(true);
        redraw = GameLoop.class.getDeclaredMethod("redraw");
        redraw.setAccessible(true);
    }

    private void clockTickHelper() throws InvocationTargetException, IllegalAccessException {
        update.invoke(gameLoopUnderTest);
        collision.invoke(gameLoopUnderTest);
        redraw.invoke(gameLoopUnderTest);
    }

    @Test
    public void testClockTick() throws InvocationTargetException, IllegalAccessException {
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0, 0)), new Food());
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0, 1));
    }

    @Test
    public void testNoBack() throws InvocationTargetException, IllegalAccessException {
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0, 0)), new Food());
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0, 1));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0, 2));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.UP);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0, 3));
    }

    /*@Test
    public void scoreIncreasedByOneAfterSnakeEatTheFood() throws InvocationTargetException, IllegalAccessException, InterruptedException {
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0, 0)), new Food(new Point2D(0, 1), false));
        assertEquals(0, gameLoopUnderTest.getPlatform().getScore().getValue());
        Thread.sleep(100);
        clockTickHelper();
        assertEquals(1, gameLoopUnderTest.getPlatform().getScore().getValue());
    }

    @Test
    public void scoreIncreasedByFiveAfterSnakeEatTheSpecialFood() throws InvocationTargetException, IllegalAccessException, InterruptedException {
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0, 0)), new Food(new Point2D(0, 1), true));
        assertEquals(0, gameLoopUnderTest.getPlatform().getScore().getValue());
        Thread.sleep(100);
        clockTickHelper();
        assertEquals(5, gameLoopUnderTest.getPlatform().getScore().getValue());
    }*/
}