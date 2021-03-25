package inf112.skeleton.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

import java.util.ArrayList;

/**

 * For each test the application will run and open,
 * to let the tests run correctly push the start button to open the map and then just close the window
 * by pressing on the X in the top right corner.
 *
 */
public class MapTests {

    private RoboRallyGUI game;
    private IBoardLogic board;
    private IPlayer myPlayer;
    private GameScreen gameScreen;

    @Before
    public void setUp() {
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
     * Test if map exist.
     */
    @Test
    public void testMap(){
        assertNotNull(board.getTiledMap());
    }
    /**
     * Test If player is inside map.
     */
    @Test
    public void testIfPlayerIsInsideMap(){
        assertTrue(board.checkOutOfBounds());
    }
    /**
     * Test If player touched a flag.
     */
    @Test
    public void testIfPlayerTouchedFlag(){
        myPlayer.rotatePlayer(-180);
        for(int i = 0; i < 4; i++){
            myPlayer.moveForward();
        }
        myPlayer.rotatePlayer(-90);
        for(int i = 0; i < 8; i++){
            myPlayer.moveForward();
        }
        boolean isTrue = false;
        for (Vector2 loc : board.getFlags()) {
            if(myPlayer.getLocation().equals(loc)){
                isTrue = true;
                break;
            }
        }
        assertTrue(isTrue);
    }
    /**
     * Test If player wins if he has visited the flags in the appropriate order.
     */
    @Test
    public void testPlayerWin(){

        ArrayList<Vector2> flagList = board.getFlags();

        myPlayer.setX(flagList.get(0).x);
        myPlayer.setY(flagList.get(0).y);

        System.out.println("flags collected: "+board.collectedFlags());

        myPlayer.setX(flagList.get(1).x);
        myPlayer.setY(flagList.get(1).y);

        System.out.println("flags collected: "+board.collectedFlags());

        myPlayer.setX(flagList.get(2).x);
        myPlayer.setY(flagList.get(2).y);

        System.out.println("flags collected: "+board.collectedFlags());

        myPlayer.setX(flagList.get(3).x);
        myPlayer.setY(flagList.get(3).y);

        System.out.println("flags collected: "+board.collectedFlags());

        assertTrue(board.checkWin());
    }
}