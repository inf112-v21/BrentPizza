package inf112.skeleton.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.junit.Before;
import org.junit.Test;

public class mapTests {

    private RoboRally game;

    @Before
    public void setUp(){
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        game = new RoboRally();
        new Lwjgl3Application(game, cfg);
    }
    /**
     * Test if map exist ?
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
        assertTrue(game.checkOutOfBounds());
    }
    /**
     * Test If player touched winner flag.
     */
    @Test
    public void testIfPlayerTouchedFlag(){
        assertTrue(game.checkWin());
    }
}