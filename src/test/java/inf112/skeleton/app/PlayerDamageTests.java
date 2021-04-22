package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.NullCard;
import inf112.skeleton.app.GUI.HUD.Hud;
import inf112.skeleton.app.GUI.RoboRallyGUI;
import inf112.skeleton.app.GUI.Screens.GameScreen;

import inf112.skeleton.app.GameLogic.HudLogic;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import inf112.skeleton.app.Server.ServerConnect;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
/**

 * For each test the application will run and open,
 * to let the tests run correctly push the ready button to open the map and then just close the window
 * by pressing on the X in the top right corner.
 *
 */
public class PlayerDamageTests {

    private RoboRallyGUI game;
    private IBoardLogic board;
    private IPlayer myPlayer;
    private GameScreen gameScreen;
    private HudLogic hudLog;
    Hud hud;

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
        hudLog = new HudLogic(board, hud);

        connection.stop();
    }
    /**
     * Test if the player have eight cards too pick from with one damage token.
     */
    @Test
    public void testPlayerDamageOne(){

        myPlayer.changeDamageTokens(1);

        ArrayList<Card> cards = hudLog.getHandLimited();
        int counter = 0;
        for(int i = 0; i<9; i++){
            if(!(cards.get(i) instanceof NullCard)){
                counter++;
            }
        }
        assertEquals(8, counter);
    }
    /**
     * Test if the player have three cards too pick from with six damage token.
     */
    @Test
    public void testPlayerDamageSix(){

        myPlayer.changeDamageTokens(6);

        ArrayList<Card> cards = hudLog.getHandLimited();
        int counter = 0;
        for(int i = 0; i<9; i++){
            if(!(cards.get(i) instanceof NullCard)){
                counter++;
            }
        }
        assertEquals(3, counter);
    }
    /**
     * Test if the player loses one life if he gets ten damage tokens.
     */
    @Test
    public void testRobotFullDamage(){

        Integer two = 2;

        myPlayer.changeDamageTokens(10);
        board.robotFullDamage(myPlayer);

        assertEquals(two, myPlayer.getLifeTokens());
    }
}
