package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * For each test the application will run and open,
 * to let the tests run correctly just close the window
 * by pressing on the X in the top right corner.
 */
public class movementTests {

    public RoboRally game;

    @Before
    public void setUp(){
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        game = new RoboRally();
        new Lwjgl3Application(game, cfg);

        game.myPlayer.getSprite().setX(500);
        game.myPlayer.getSprite().setY(500);
    }
    /**
     * Test if player moves forward.
     */
    @Test
    public void testIfPlayerMovesForward(){

        game.myPlayer.moveForward();
        Vector2 move = new Vector2(500, 350);

        assertEquals(game.myPlayer.getLocation(), move);
    }
    /**
     * Test if player rotates and moves.
     */
    @Test
    public void testIfPlayerRotatesAndMoves(){

        game.myPlayer.rotatePlayer(-90);
        game.myPlayer.moveForward();
        Vector2 move = new Vector2(350, 500);

        assertEquals(game.myPlayer.getLocation(), move);
    }
    /**
     * Test if player moves up.
     */
    @Test
    public void testPlayerDirectionUp(){

        game.myPlayer.moveUp();
        Vector2 up = new Vector2(500, 650);

        assertEquals(game.myPlayer.getLocation(), up);
    }
    /**
     * Test if player moves down.
     */
    @Test
    public void testPlayerDirectionDown(){
        game.myPlayer.moveDown();
        Vector2 down = new Vector2(500, 350);

        assertEquals(game.myPlayer.getLocation(), down);
    }
    /**
     * Test if player moves left.
     */
    @Test
    public void testPlayerDirectionLeft(){
        game.myPlayer.moveLeft();
        Vector2 left = new Vector2(350, 500);

        assertEquals(game.myPlayer.getLocation(), left);
    }
    /**
     * Test if player moves right.
     */
    @Test
    public void testPlayerDirectionRight(){
        game.myPlayer.moveRight();
        Vector2 right = new Vector2(650, 500);

        assertEquals(game.myPlayer.getLocation(), right);
    }
}
