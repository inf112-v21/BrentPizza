package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.xml.stream.Location;
import java.util.ArrayList;

public class Laser {

    public ArrayList<Sprite> createLaser(TiledMap tiledMap, IBoardLogic boardLogic){
        MapLayer lasers = tiledMap.getLayers().get(tiledMap.getLayers().getIndex("lazers"));


        ArrayList<Sprite> laserSprites = new ArrayList<>();
        for (MapObject laser: lasers.getObjects()) {
            System.out.println(laser.getProperties().get("direction").toString());
            if(laser.getProperties().get("direction").toString().equals("north")){
                for (int i = 0; i < 3; i++) {
                    boolean stopAdding = false;
                    if(stopAdding)
                        break;
                    Sprite laserrr = new Sprite(new Texture(Gdx.files.internal("src/main/Resources/Laser.png")));
                    Float laserX = Float.parseFloat( laser.getProperties().get("x").toString()) + 150/2;
                    Float laserY = Float.parseFloat(laser.getProperties().get("y").toString()) + i*150;
                    laserrr.setX(laserX);
                    laserrr.setY(laserY);
                    laserSprites.add(laserrr);
                    IPlayer pl = boardLogic.getMyPlayer();
                    System.out.println(pl.getLocation().x + "," + pl.getLocation().y);
                    System.out.println(laserX + "," + laserY);

                    Vector2 playerLoc = pl.getLocation();
                    if(playerLoc.x <= laserX+75 && playerLoc.x >= laserX-75 && playerLoc.y <= laserY+75 && playerLoc.y >= laserY-75) {
                        break;
                    }
                }
            }


        }


        return laserSprites;
    }
}
