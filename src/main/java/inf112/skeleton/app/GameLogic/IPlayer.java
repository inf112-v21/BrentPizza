package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public interface IPlayer {
    int getID();

    Sprite getSprite();

    Vector2 getLocation();

    void moveUp();

    void moveDown();

    void moveLeft();

    void moveRight();

    void moveForward();

    void rotatePlayer(float x);

    void setRoation(float x);

    float getRotation();

    void setX(float x);

    void setY(float y);

    float getX();

    float getY();
}
