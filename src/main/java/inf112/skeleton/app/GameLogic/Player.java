package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player implements IPlayer {
    private int id;
    private Sprite playerSprite;
    //To be used in the future
    private int collectedFlags;
    private Integer lifeTokens;
    private Vector2 lastSavePoint;

    private Integer damageTokens;

    public Player(int id, Sprite sprite) {
        this.id = id;
        this.playerSprite = sprite;
        this.collectedFlags = 0;
        this.lifeTokens = 3;
        this.lastSavePoint = getLocation();
        this.damageTokens = 0;

    }

    @Override
    public int getID() {
        return this.id;
    }

    public Integer getDamageTokens(){
        return damageTokens;
    }

    @Override
    public void changeDamageTokens(Integer change) {
        this.damageTokens += change;
    }

    @Override
    public Integer getLifeTokens(){
        return this.lifeTokens;
    }

    @Override
    public void changeLifeTokens(int x){
        this.lifeTokens += x;
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

    public Vector2 getLastSavePoint(){
        return this.lastSavePoint;
    }

    public void setLastSavePoint(Vector2 location){
        lastSavePoint = location;
    }

    @Override
    public void moveForward() {
        if ((playerSprite.getRotation()%360) == -180) {
            playerSprite.translate(0, 150);
        }
        else if ((playerSprite.getRotation()%360) == -270) {
            playerSprite.translate(150, 0);
        }
        else if ((playerSprite.getRotation()%360) == 0) {
            playerSprite.translate(0, -150);
        }
        else if ((playerSprite.getRotation()%360) == -90) {
            playerSprite.translate(-150, 0);
        }
        else if ((playerSprite.getRotation()%360) == 180) {
            playerSprite.translate(0, -150);
        }
        else if ((playerSprite.getRotation()%360) == 270) {
            playerSprite.translate(-150, 0);
        }
        else if ((playerSprite.getRotation()%360) == 0) {
            playerSprite.translate(0, 150);
        }
        else if ((playerSprite.getRotation()%360) == 90) {
            playerSprite.translate(150, 0);
        }

    }

    @Override
    public void rotatePlayer(float x){
        playerSprite.rotate(x);
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
    @Override
    public void setLocation(Vector2 location){
        this.getSprite().setX(location.x);
        this.getSprite().setY(location.y);
    }

}
