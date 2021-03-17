package inf112.skeleton.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.GUI.RoboRallyGUI;
import inf112.skeleton.app.GameLogic.BoardLogic;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import inf112.skeleton.app.Server.ServerConnect;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * You must run ServerStart before running the tests. Max players 11.
 *
 * You have to run the test one by one with the maximum of eleven test before having to restart the server.
 *
 * For each test the application will run and open,
 * to let the tests run correctly just close the window
 * by pressing on the X in the top right corner.
 *
 */

public class MapTests {

    private RoboRallyGUI game;
    private IBoardLogic board;
    private IPlayer myPlayer;

    @Before
    public void setUp() throws IOException {

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        game = new RoboRallyGUI();
        new Lwjgl3Application(game, cfg);
/*
        try {
            board = new BoardLogic(game.tiledMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

 */
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
        myPlayer.rotatePlayer(-270);
        myPlayer.moveForward();
        myPlayer.rotatePlayer(90);
        for(int i = 0; i < 4; i++){
            myPlayer.moveForward();
        }
        assertTrue(board.checkWin());
    }
}