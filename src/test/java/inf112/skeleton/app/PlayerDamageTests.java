package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.NullCard;
import inf112.skeleton.app.GUI.HUD.Hud;
import inf112.skeleton.app.GUI.RoboRallyGUI;
import inf112.skeleton.app.GUI.Screens.GameScreen;
import inf112.skeleton.app.GameLogic.BoardLogic;
import inf112.skeleton.app.GameLogic.HudLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

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
public class PlayerDamageTests {

    private RoboRallyGUI game;
    private BoardLogic board;
    private IPlayer myPlayer;
    private GameScreen gameScreen;
    private HudLogic hudLog;
    Hud hud;

    @Before
    public void setUp(){
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        this.game = new RoboRallyGUI();
        cfg.setWindowedMode(1920, 1080);
        new Lwjgl3Application(this.game, cfg);

        this.gameScreen = this.game.getGameScreen();

        board = gameScreen.boardLogic;
        myPlayer = board.getMyPlayer();
        hudLog = new HudLogic(board, hud);
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
    public void testPlayerFullDamage(){

        myPlayer.changeDamageTokens(10);
        board.robotFullDamage();

        assertEquals(2, myPlayer.getLifeTokens());
    }
    /**
     * Test if the player gets one less damage token on single repair site.
     */
    @Test
    public void testPlayerRepairOne(){

        myPlayer.changeDamageTokens(2);

        myPlayer.rotatePlayer(-270);
        for(int i = 0; i < 10; i++){
            board.repairRobot();
            myPlayer.moveForward();
        }
        assertSame(1, myPlayer.getDamageTokens());
    }
    /**
     * Test if the player gets two less damage token on double repair site.
     */
    @Test
    public void testPlayerRepairTwo(){

        myPlayer.changeDamageTokens(2);

        myPlayer.rotatePlayer(-180);
        myPlayer.moveForward();
        myPlayer.moveForward();
        myPlayer.rotatePlayer(-90);
        for(int i = 0; i < 4; i++){
            board.repairRobot();
            myPlayer.moveForward();
        }
        assertSame(0, myPlayer.getDamageTokens());
    }
}
