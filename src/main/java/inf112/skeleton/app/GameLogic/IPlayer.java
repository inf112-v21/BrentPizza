package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public interface IPlayer {

    /**
     * Gives the ID of the player object. This id reflects where the player is in the players list, and index = id-1
     * @return id of this player
     */
    int getID();

    /**
     * Gives the sprite of this player
     * @return players sprite
     */
    Sprite getSprite();

    /**
     * Gives the current location of the player
     * @return a Vector with x = players x position, y = players y position
     */
    Vector2 getLocation();

    /**
     * Moves the player forward based on the current rotation
     */
    void moveForward();

    /**
     * Rotates the player x degrees
     * @param x float value to represent how many degrees the player should be rotated
     */
    void rotatePlayer(float x);

    /**
     * Sets a new rotation no matter what the previuous rotation was
     * @param x
     */
    void setRoation(float x);

    /**
     * Gives the rotation of the player
     * @return rotation in degrees
     */
    float getRotation();

    /**
     * Sets a new x position no matter where the player was previously
     * @param x
     */
    void setX(float x);

    /**
     * Sets a new y position no matter where the player was previously
     * @param y
     */
    void setY(float y);

    /**
     *
     * @return Players health points
     */
    int getLifeTokens();

    /**
     * Makes changes to the players life tokens
     * @param x - amount you want to change
     */
    void changeLifeTokens(int x);

    /**
     * Returns the players last save point
     * @return
     */
    Vector2 getLastSavePoint();

    /**
     * returns the players last save point
     * @param location
     */
    void setLastSavePoint(Vector2 location);

    /**
     *Returns players dmg tokens
     */
    Integer getDamageTokens();

    /**
     * Edits dmg tokens
     */
    void changeDamageTokens(Integer change);
}
