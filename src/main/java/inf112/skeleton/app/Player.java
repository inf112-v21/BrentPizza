package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private int id;
    private Sprite playerSprite;

    public Player(int id, Sprite sprite){
        this.id = id;
        this.playerSprite = sprite;
    }

    public Sprite getSprite(){
        return this.playerSprite;
    }

    public Vector2 getLocation(){
        Vector2 vector = new Vector2();
        vector.x = playerSprite.getX();
        vector.y = playerSprite.getY();
        return vector;
    }

    public void moveUp(){
        playerSprite.translate(0, 150);
    }
    public void moveDown(){
        playerSprite.translate(0, -150);
    }
    public void moveLeft(){
        playerSprite.translate(-150, 0);
    }
    public void moveRight(){
        playerSprite.translate(150, 0);
    }

    public int getID(){
        return this.id;
    }
}
