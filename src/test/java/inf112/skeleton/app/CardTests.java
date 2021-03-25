package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.Cards.*;
import inf112.skeleton.app.GUI.HUD.Hud;
import inf112.skeleton.app.GUI.RoboRallyGUI;
import inf112.skeleton.app.GUI.Screens.GameScreen;
import inf112.skeleton.app.GameLogic.HudLogic;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
/**
 * You must run ServerStart before running the tests. Max players 11.
 *
 * You have to run the test one by one with the maximum of eleven test before having to restart the server.
 *
 * For each test the application will run and open,
 * to let the tests run correctly push the ready button to open the map and then just close the window
 * by pressing on the X in the top right corner.
 *
 */
public class CardTests {

    private RoboRallyGUI game;
    private IBoardLogic board;
    private IPlayer myPlayer;
    private HudLogic hudLog;
    private GameScreen gameScreen;
    Hud hud;

    @Before
    public void setUp(){
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        this.game = new RoboRallyGUI();
        cfg.setWindowedMode(1920, 1080);
        new Lwjgl3Application(this.game, cfg);

        this.gameScreen = this.game.getGameScreen();

        board = gameScreen.boardLogic;

        hudLog = new HudLogic(board, hud);
        myPlayer = board.getMyPlayer();

        myPlayer.getSprite().setX(500);
        myPlayer.getSprite().setY(500);
    }
    /**
     * Test adding cards to program list.
     */
    @Test
    public void testAddProgramCard(){
        Button button;
        Card card;
        button = hudLog.getHandButtonList().get(0);
        card = hudLog.getHand().get(0);
        hudLog.addToProgramButtonList(button);

        assertEquals(card, hudLog.getProgramCards().get(0));
    }
    /**
     * Test if all program cards is null card after clear.
     */
    @Test
    public void testClearProgramCards(){

        hudLog.clearProgramCards();
        ArrayList<Card> cards = hudLog.getProgramCards();
        boolean isTrue = true;
        for(int i = 0; i<5; i++){
            if(!(cards.get(i) instanceof NullCard)){
                isTrue = false;
                break;
            }
        }
        assertTrue(isTrue);
    }
    /**
     * Test if player can pick from nine cards.
     */
    @Test
    public void testIfPlayerCanPickFromNineCards(){
        assertEquals(hudLog.getHandButtonList().size(), 9);
    }
    /**
     * Test if player holds five cards.
     */
    @Test
    public void testIfPlayerGetCorrectAmountOfCards(){
        assertEquals(hudLog.getProgramImageList().size(), 5);
    }
    /**
     * Test if MoveOneCard moves player one forward.
     */
    @Test
    public void testIfCardMovePlayerOne(){
        Card one = new MoveOneCard();
        one.action(myPlayer);

        Vector2 movement = new Vector2(500, 350);

        assertEquals(myPlayer.getLocation(), movement);
    }
    /**
     * Test if MoveTwoCard moves player two forward.
     */
    @Test
    public void testIfCardMovePlayerTwo(){
        Card two = new MoveTwoCard();
        two.action(myPlayer);

        Vector2 movement = new Vector2(500, 200);

        assertEquals(myPlayer.getLocation(), movement);
    }
    /**
     * Test if MoveThreeCard moves player three forward.
     */
    @Test
    public void testIfCardMovePlayerThree(){
        Card three = new MoveThreeCard();
        three.action(myPlayer);

        Vector2 movement = new Vector2(500, 50);

        assertEquals(myPlayer.getLocation(), movement);
    }
    /**
     * Test if TurnLeftCard turn player one left.
     */
    @Test
    public void testIfCardTurnPlayerRight(){
        Card move = new MoveOneCard();
        Card right = new TurnRightCard();

        move.action(myPlayer);
        right.action(myPlayer);

        Vector2 movement = new Vector2(500, 350);

        assertEquals(myPlayer.getLocation(), movement);
    }
    /**
     * Test if TurnRightCard turn player one right.
     */
    @Test
    public void testIfCardTurnPlayerLeft(){
        Card move = new MoveOneCard();
        Card left = new TurnLeftCard();

        left.action(myPlayer);
        move.action(myPlayer);

        Vector2 movement = new Vector2(350, 500);

        assertEquals(myPlayer.getLocation(), movement);
    }

    /**
     * Test if TurnRightCard turn player one right.
     */
    @Test
    public void testTextureGetter(){
        TextureGetter textureGetter = new TextureGetter();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new TurnRightCard());
        cards.add(new TurnLeftCard());
        cards.add(new MoveTwoCard());
        cards.add(new MoveThreeCard());
        cards.add(new MoveOneCard());
        cards.add(new uTurnCard());
        cards.add(new MoveBackCard());
        cards.add(new NullCard());
        ArrayList<TextureRegionDrawable> textureRegions = new ArrayList<>();
        textureRegions.add(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/rightTurn.png"))));
        textureRegions.add(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/leftTurn.png"))));
        textureRegions.add(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/moveTwo.png"))));
        textureRegions.add(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/moveThree.png"))));
        textureRegions.add(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/moveOne.png"))));
        textureRegions.add(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/uTurn.png"))));
        textureRegions.add(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/moveBack.png"))));
        textureRegions.add(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/nullCard.png"))));

        for (int i = 0; i < cards.size(); i++) {
            assertEquals(textureGetter.getCardTexture(cards.get(i)).getRegion().toString().equals(textureRegions.get(i).getRegion().toString()), true);
        }

    }
}
