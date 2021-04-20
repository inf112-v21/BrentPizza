package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.MoveOneCard;
import inf112.skeleton.app.GUI.RoboRallyGUI;
import inf112.skeleton.app.GUI.Screens.GameScreen;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WallTests {

    private RoboRallyGUI game;
    private IBoardLogic board;
    private IPlayer myPlayer;
    private GameScreen gameScreen;

    @Before
    public void setUp() {

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        this.game = new RoboRallyGUI();
        cfg.setWindowedMode(1920, 1080);
        new Lwjgl3Application(this.game, cfg);

        this.gameScreen = this.game.getGameScreen();

        board = gameScreen.boardLogic;

        myPlayer = board.getMyPlayer();
    }
    /**
     * Test if player tries to go in wall direction.
     */
    @Test
    public void testIfWall(){

        myPlayer.getSprite().setX(3600);
        myPlayer.getSprite().setY(1050);

        Card one = new MoveOneCard();

        boolean isTrue = true;

        if(board.checkMove()){
            isTrue = false;
            one.action(myPlayer);
            System.out.println("Obs you went through a wall");
        }
        assertTrue(isTrue);
    }
    /**
     * Test if no wall.
     */
    @Test
    public void testIfNotWall(){

        myPlayer.getSprite().setX(3600);
        myPlayer.getSprite().setY(900);

        Card one = new MoveOneCard();

        boolean isTrue = false;

        if(board.checkMove()){
            isTrue = true;
            one.action(myPlayer);
        }
        assertTrue(isTrue);
    }
    /**
     * Test player location after player tries to go in wall direction.
     */
    @Test
    public void testWallLocation(){

        myPlayer.getSprite().setX(3600);
        myPlayer.getSprite().setY(300);

        Vector2 moved = new Vector2(3600, 300);

        Card one = new MoveOneCard();

        if(board.checkMove()){
            one.action(myPlayer);
        }
        assertEquals(moved, myPlayer.getLocation());
    }
}
