package inf112.skeleton.app;

import java.awt.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private int id;
    private Robot robot;
    private int flagID;
    private Sprite playerSprite;
    private float posX, posY;
    private Vector2 startX, startY;
    private int visitedFlag;

    public Player(int id, Sprite sprite){
        this.id = id;
        this.playerSprite = sprite;
        //this.robot = robot;
        this.flagID = 0;
        this.visitedFlag = 0;
    }
    public void visitedFlag(){
    }
}
