package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.GUI.RoboRallyGUI;
import inf112.skeleton.app.GameLogic.BoardLogic;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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

public class MovementTests {

    private RoboRallyGUI game;
    private IBoardLogic board;
    private IPlayer myPlayer;

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

        myPlayer.getSprite().setX(500);
        myPlayer.getSprite().setY(500);
    }
    /**
     * Test if player moves forward.
     */
    @Test
    public void testIfPlayerMovesForward(){

        myPlayer.moveForward();
        Vector2 move = new Vector2(500, 350);

        assertEquals(myPlayer.getLocation(), move);
    }
    /**
     * Test if player rotates and moves.
     */
    @Test
    public void testIfPlayerRotatesAndMoves(){

        myPlayer.rotatePlayer(-90);
        myPlayer.moveForward();
        Vector2 move = new Vector2(350, 500);

        assertEquals(myPlayer.getLocation(), move);
    }
}
