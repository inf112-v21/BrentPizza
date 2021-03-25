package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.GUI.RoboRallyGUI;
import inf112.skeleton.app.GUI.Screens.GameScreen;

import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import inf112.skeleton.app.Server.ServerConnect;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**

 * For each test the application will run and open,
 * to let the tests run correctly push the start button to open the map and then just close the window
 * by pressing on the X in the top right corner.
 *
 */
public class PlayerLifeTests {

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
        this.game = new RoboRallyGUI();
        cfg.setWindowedMode(1920, 1080);
        new Lwjgl3Application(this.game, cfg);

        this.gameScreen = this.game.getGameScreen();

        board = gameScreen.boardLogic;

        myPlayer = board.getMyPlayer();

        connection.stop();
    }
    /**
     * Test if the player have three lives in beginning of game.
     */
    @Test
    public void testIfRobotHasThreeLives(){

        assertEquals(3, myPlayer.getLifeTokens());
    }
    /**
     * Test if the player loses one life if he falls in a hole.
     */
    @Test
    public void testFallsInHole(){

        myPlayer.rotatePlayer(-180);
        myPlayer.moveForward();
        myPlayer.rotatePlayer(-90);
        for(int i = 0; i < 5; i++){
            board.robotFallHole();
            myPlayer.moveForward();
        }
        assertEquals(2, myPlayer.getLifeTokens());
    }
    /**
     * Test if the player loses one life if he falls of the map.
     */
    @Test
    public void testFallsOutsideMap(){

        myPlayer.moveForward();
        board.robotFallOutsideMap();

        assertEquals(2, myPlayer.getLifeTokens());
    }
}
