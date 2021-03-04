package inf112.skeleton.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.GUI.RoboRallyGUI;
import inf112.skeleton.app.GameLogic.BoardLogic;
import inf112.skeleton.app.GameLogic.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * For each test the application will run and open,
 * to let the tests run correctly just close the window
 * by pressing on the X in the top right corner.
 */

public class mapTests {

    private RoboRallyGUI game;
    private BoardLogic board;
    private Player myPlayer;

    @Before
    public void setUp(){
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        game = new RoboRallyGUI();
        new Lwjgl3Application(game, cfg);

        try {
            board = new BoardLogic(game.tiledMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myPlayer = board.getMyPlayer();
    }
    /**
     * Test if map exist.
     */
    @Test
    public void testMap(){
        assertNotNull(board);
    }
    /**
     * Test If player is inside map.
     */
    @Test
    public void testIfPlayerIsInsideMap(){
        assertTrue(board.checkOutOfBounds());

    }
    /**
     * Test If player touched winner flag.
     */
    @Test
    public void testIfPlayerTouchedFlag(){
        myPlayer.moveRight();
        for(int i = 0; i < 4; i++){
            myPlayer.moveUp();
        }
        assertTrue(board.checkWin());
    }
}