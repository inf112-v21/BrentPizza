package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import inf112.skeleton.app.Cards.*;
import inf112.skeleton.app.GameLogic.BoardLogic;
import inf112.skeleton.app.GameLogic.Player;

import java.util.ArrayList;

public class InputProcess implements InputProcessor {

    private OrthographicCamera camera;
    private Player myPlayer;
    private BoardLogic logic;

    public InputProcess( OrthographicCamera camera, Player myPlayer, BoardLogic boardLogic){
        this.camera = camera;
        this.myPlayer = myPlayer;
        this.logic = boardLogic;
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        /**
         if(keycode == Input.Keys.LEFT)
         myPlayer.moveLeft();
         if(keycode == Input.Keys.RIGHT)
         myPlayer.moveRight();
         if(keycode == Input.Keys.UP)
         myPlayer.moveUp();
         if(keycode == Input.Keys.DOWN)
         myPlayer.moveDown();
         */
        if(keycode == Input.Keys.NUM_1){
            MoveOneCard card = new MoveOneCard();
            card.action(myPlayer);
        }
        if(keycode == Input.Keys.NUM_2){
            MoveTwoCard card = new MoveTwoCard();
            card.action(myPlayer);
        }
        if(keycode == Input.Keys.NUM_3){
            MoveThreeCard card = new MoveThreeCard();
            card.action(myPlayer);
        }
        if(keycode == Input.Keys.NUM_4){
            TurnLeftCard card = new TurnLeftCard();
            card.action(myPlayer);
        }
        if(keycode == Input.Keys.NUM_5){
            TurnRightCard card = new TurnRightCard();
            card.action(myPlayer);
        }
        if(keycode == Input.Keys.NUM_6){
            ArrayList<Card> cards = new ArrayList<>();
            cards.add(new MoveOneCard());
            cards.add(new MoveTwoCard());
            cards.add(new MoveThreeCard());
            cards.add(new TurnLeftCard());


            for (Card card: cards) {
                card.action(myPlayer);
            }
        }

        if(keycode == Input.Keys.UP)
            myPlayer.moveForward();
        if (keycode == Input.Keys.SPACE)
            myPlayer.rotatePlayer(-90);

        //Checks if player is inside map
        //checkOutOfBounds();

        logic.sendPlayer(myPlayer);

        // ends game if player exits map

        if(!logic.checkOutOfBounds()){
            System.out.println("Player " + myPlayer.getID() + " fell and died");
            logic.setGameOver(true);
        }


        //ends game if player steps on flag

        if(logic.checkWin()){
            logic.sendWin();
        }

        return false;
    }
    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float x = Gdx.input.getDeltaX();
        float y = Gdx.input.getDeltaY();
        camera.translate(-x,y);
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        camera.zoom += amount * 0.2f;
        return false;
    }
}
