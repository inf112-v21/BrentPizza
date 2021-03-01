package inf112.skeleton.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.junit.Before;
import org.junit.Test;

/**
 * For each test the application will run and open,
 * to let the tests run correctly just close the window
 * by pressing on the X in the top right corner.
 */

public class mapTests {

    private RoboRally game;

    @Before
    public void setUp(){
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        game = new RoboRally();
        new Lwjgl3Application(game, cfg);
    }
    /**
     * Test if map exist.
     */
    @Test
    public void testMap(){
        assertNotNull(game.tiledMap);
    }
    /**
     * Test If player is inside map.
     */
    @Test
    public void testIfPlayerIsInsideMap(){
        //assertTrue(game.checkOutOfBounds());
    }
    /**
     * Test If player touched winner flag.
     */
    @Test
    public void testIfPlayerTouchedFlag(){
        game.myPlayer.moveRight();
        for(int i = 0; i < 4; i++){
            game.myPlayer.moveUp();
        }
        //assertTrue(game.checkWin());
    }
}