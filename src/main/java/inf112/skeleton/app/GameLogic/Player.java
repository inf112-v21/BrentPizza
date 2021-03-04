package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player implements IPlayer {
    private int id;
    private Sprite playerSprite;

    public Player(int id, Sprite sprite) {
        this.id = id;
        this.playerSprite = sprite;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public Sprite getSprite() {
        return this.playerSprite;
    }

    @Override
    public Vector2 getLocation() {
        Vector2 vector = new Vector2();
        vector.x = playerSprite.getX();
        vector.y = playerSprite.getY();
        return vector;
    }

    @Override
    public void moveForward() {
        if (Math.abs(playerSprite.getRotation()%360) == 180) {
            playerSprite.translate(0, 150);
        }
        if (Math.abs(playerSprite.getRotation()%360) == 270) {
            playerSprite.translate(150, 0);
        }
        if (Math.abs(playerSprite.getRotation()%360) == 0) {
            playerSprite.translate(0, -150);
        }
        if (Math.abs(playerSprite.getRotation()%360) == 90) {
            playerSprite.translate(-150, 0);
        }
    }

    @Override
    public void rotatePlayer(float x){
        playerSprite.rotate(x);
        System.out.println(playerSprite.getRotation());
    }
    @Override
    public void setRoation(float x){
        playerSprite.setRotation(x);
    }
    @Override
    public float getRotation(){
        return playerSprite.getRotation();
    }
    @Override
    public void setX(float x){
        playerSprite.setX(x);
    }
    @Override
    public void setY(float y){
        playerSprite.setY(y);
    }

}
