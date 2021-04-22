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
    Integer getLifeTokens();

    /**
     * Makes changes to the players life tokens
     * @param x - amount you want to change
     */
    void changeLifeTokens(int x);

    /**
     * Returns the players last save point
     * @return Vector 2 with players location
     */
    Vector2 getLastSavePoint();

    /**
     * Sets current players location as last save point
     * @param location - vector2 position you want as players last save point
     */
    void setLastSavePoint(Vector2 location);

    /**
     * Retrieves players damage tokens
     * @return Integer which represents players damage token
     */
    Integer getDamageTokens();

    /**
     * changes the players damage token
     * @param change - amount you want to change players damage token
     */
    void changeDamageTokens(Integer change);

    /**
     * Change players position to the given location
     * @param location - Vector2 position you want the player to be at
     */
    void setLocation(Vector2 location);
}
