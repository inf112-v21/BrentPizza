package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.GUI.RoboRallyGUI;
import inf112.skeleton.app.GUI.Screens.GameScreen;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import inf112.skeleton.app.Server.ServerConnect;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * For each test the application will run and open,
 * to let the tests run correctly push the start button to open the map and then just close the window
 * by pressing on the X in the top right corner.
 *
 */

public class MovementTests {

    private RoboRallyGUI game;
    private IBoardLogic board;
    private IPlayer myPlayer;
    private GameScreen gameScreen;

    @Before
    public void setUp(){

        ServerConnect connection = new ServerConnect();
        try{
            connection.start(11, 7979, 7878);
        }catch (Exception e){
            System.out.println(e);
        }

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        this.game = new RoboRallyGUI(true);
        cfg.setWindowedMode(1920, 1080);
        new Lwjgl3Application(this.game, cfg);

        this.gameScreen = this.game.getGameScreen();

        board = gameScreen.boardLogic;

        myPlayer = board.getMyPlayer();

        myPlayer.getSprite().setX(500);
        myPlayer.getSprite().setY(500);

        connection.stop();
    }
    /**
     * Test if player moves forward.
     */
    @Test
    public void testIfPlayerMovesForward(){

        myPlayer.moveForward();
        Vector2 move = new Vector2(350, 500);

        assertEquals(myPlayer.getLocation(), move);
    }
    /**
     * Test if player rotates and moves.
     */
    @Test
    public void testIfPlayerRotatesAndMoves(){

        myPlayer.rotatePlayer(-90);
        myPlayer.moveForward();
        Vector2 move = new Vector2(500, 650);

        assertEquals(myPlayer.getLocation(), move);
    }
}
