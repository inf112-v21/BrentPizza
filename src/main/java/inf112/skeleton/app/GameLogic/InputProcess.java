package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class InputProcess implements IInputProcess {

    private OrthographicCamera camera;
    private IPlayer myPlayer;
    private IBoardLogic logic;

    public InputProcess(OrthographicCamera camera, IPlayer myPlayer, IBoardLogic boardLogic){
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
        if(keycode == Input.Keys.UP)
            myPlayer.moveForward();
        if (keycode == Input.Keys.SPACE)
            myPlayer.rotatePlayer(-90);

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
