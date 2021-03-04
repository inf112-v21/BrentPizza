package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.InputProcessor;

public interface IInputProcess extends InputProcessor {

    /**
     * Not in use
     * @param i
     * @return
     */
    @Override
    boolean keyDown(int i);

    /**
     * Is used to turn player around with the spacebar and move player forwards with arrow up
     * Sends the new player position to server, checks if the player went outOfBounds and checks if the player won
     * @param keycode
     * @return
     */
    @Override
    boolean keyUp(int keycode);

    /**
     * Not in use
     * @param c
     * @return
     */
    @Override
    boolean keyTyped(char c);

    /**
     * Not in use
     * @param i
     * @param i1
     * @param i2
     * @param i3
     * @return
     */
    @Override
    boolean touchDown(int i, int i1, int i2, int i3);

    /**
     * Not in use
     * @param i
     * @param i1
     * @param i2
     * @param i3
     * @return
     */
    @Override
    boolean touchUp(int i, int i1, int i2, int i3);

    /**
     * Used to move the camera around with the mouse
     * @param screenX
     * @param screenY
     * @param pointer
     * @return
     */
    @Override
    boolean touchDragged(int screenX, int screenY, int pointer);

    /**
     * Not in use
     * @param i
     * @param i1
     * @return
     */
    @Override
    boolean mouseMoved(int i, int i1);

    /**
     * Zooms the camera view in and out
     * @param amount
     * @return
     */
    @Override
    boolean scrolled(int amount);
}
