package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.GUI.RoboRallyGUI;
import inf112.skeleton.app.GUI.Screens.GameScreen;
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
 * to let the tests run correctly push the start button to open the map and then just close the window
 * by pressing on the X in the top right corner.
 *
 */

public class ConveyorBeltTests {

    private RoboRallyGUI game;
    private IBoardLogic board;
    private IPlayer myPlayer;
    private GameScreen gameScreen;

    @Before
    public void setUp(){
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        this.game = new RoboRallyGUI();
        cfg.setWindowedMode(1920, 1080);
        new Lwjgl3Application(this.game, cfg);

        this.gameScreen = this.game.getGameScreen();

        board = gameScreen.boardLogic;

        myPlayer = board.getMyPlayer();

    }
    /**
     * Test if oneArrowWest conveyor-belt moves the player correctly.
     */
    @Test
    public void testOneArrowWestBelt(){

        myPlayer.getSprite().setX(3450);
        myPlayer.getSprite().setY(450);

        board.convey();
        Vector2 moved = new Vector2(3300, 450);

        assertEquals(moved, myPlayer.getLocation());
    }
    /**
     * Test if oneArrowEast conveyor-belt moves the player correctly.
     */
    @Test
    public void testOneArrowEastBelt(){

        myPlayer.getSprite().setX(3300);
        myPlayer.getSprite().setY(750);

        board.convey();
        Vector2 moved = new Vector2(3450, 750);

        assertEquals(moved, myPlayer.getLocation());
    }
    /**
     * Test if oneArrowNorth conveyor-belt moves the player correctly.
     */
    @Test
    public void testOneArrowNorthBelt(){

        myPlayer.getSprite().setX(2700);
        myPlayer.getSprite().setY(1050);

        board.convey();
        Vector2 moved = new Vector2(2700, 1200);

        assertEquals(moved, myPlayer.getLocation());
    }
    /**
     * Test if oneArrowSouth conveyor-belt moves the player correctly.
     */
    @Test
    public void testOneArrowSouthBelt(){

        myPlayer.getSprite().setX(2550);
        myPlayer.getSprite().setY(1200);

        board.convey();
        Vector2 moved = new Vector2(2550, 1050);

        assertEquals(moved, myPlayer.getLocation());
    }
    /**
     * Test if twoArrowSouth conveyor-belt moves the player correctly.
     */
    @Test
    public void testTwoArrowSouthBelt(){

        myPlayer.getSprite().setX(2550);
        myPlayer.getSprite().setY(600);

        board.convey();
        Vector2 moved = new Vector2(2550, 300);

        assertEquals(moved,myPlayer.getLocation());
    }
    /**
     * Test if twoArrowWest conveyor-belt moves the player correctly.
     */
    @Test
    public void testTwoArrowWestBelt(){

        myPlayer.getSprite().setX(2400);
        myPlayer.getSprite().setY(900);

        board.convey();
        Vector2 moved = new Vector2(2100, 900);

        assertEquals(moved, myPlayer.getLocation());
    }
}