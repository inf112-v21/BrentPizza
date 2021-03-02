package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player{
    private int id;
    private Sprite playerSprite;

    public Player(int id, Sprite sprite) {
        this.id = id;
        this.playerSprite = sprite;
    }

    public int getID() {
        return this.id;
    }

    public Sprite getSprite() {
        return this.playerSprite;
    }

    public Vector2 getLocation() {
        Vector2 vector = new Vector2();
        vector.x = playerSprite.getX();
        vector.y = playerSprite.getY();
        return vector;
    }

    public void moveUp() {
        playerSprite.translate(0, 150);
    }

    public void moveDown() {
        playerSprite.translate(0, -150);
    }

    public void moveLeft() {
        playerSprite.translate(-150, 0);
    }

    public void moveRight() {
        playerSprite.translate(150, 0);
    }

    public void moveForward() {
        if (Math.abs(playerSprite.getRotation()%360) == 180) {
            moveUp();
        }
        if (Math.abs(playerSprite.getRotation()%360) == 270) {
            moveRight();
        }
        if (Math.abs(playerSprite.getRotation()%360) == 0) {
            moveDown();
        }
        if (Math.abs(playerSprite.getRotation()%360) == 90) {
            moveLeft();
        }
    }

    public void rotatePlayer (float x){
        playerSprite.rotate(x);
        System.out.println(playerSprite.getRotation());
    }
    public void setRoation(float x){
        playerSprite.setRotation(x);
    }
    public float getRotation(){
        return playerSprite.getRotation();
    }
    public void setX(float x){
        playerSprite.setX(x);
    }
    public void setY(float y){
        playerSprite.setY(y);
    }
    public float getX(){
        return playerSprite.getX();
    }
    public float getY(){
        return playerSprite.getY();
    }

}
