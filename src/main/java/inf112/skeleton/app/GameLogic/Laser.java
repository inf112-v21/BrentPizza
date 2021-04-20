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
import java.util.HashMap;

public class Laser {
    HashMap<Vector2, String> walls;
    IBoardLogic boardLogic;
    public Laser(IBoardLogic boardLogic){
        walls = boardLogic.getWalls();
        this.boardLogic = boardLogic;
    }

    public ArrayList<Sprite> createLaser(TiledMap tiledMap){
        MapLayer lasers = tiledMap.getLayers().get(tiledMap.getLayers().getIndex("lazers"));

        ArrayList<Sprite> laserSprites = new ArrayList<>();
        for (MapObject laser: lasers.getObjects()) {
            System.out.println(laser.getProperties().get("direction").toString());
            if(laser.getProperties().get("direction").toString().equals("north")){
                Integer i = 0;
                while (true){

                    Sprite laserrr = new Sprite(new Texture(Gdx.files.internal("src/main/Resources/Laser.png")));
                    Float laserX = Float.parseFloat( laser.getProperties().get("x").toString()) + 150/2;
                    Float laserY = Float.parseFloat(laser.getProperties().get("y").toString()) + i*150;
                    laserrr.setX(laserX);
                    laserrr.setY(laserY);


                    IPlayer pl = boardLogic.getMyPlayer();
                    System.out.println(pl.getLocation().x + "," + pl.getLocation().y);
                    System.out.println(laserX + "," + laserY);
                    Vector2 playerLoc = pl.getLocation();

                    if(walls.get(new Vector2(laserX-75, laserY)) == "wallNorth"){
                        laserSprites.add(laserrr);
                        break;
                    }
                    if(walls.get(new Vector2(laserX, laserY)) == "wallSouth" || laserY > 1650){
                        System.out.println("Yikes");
                        break;
                    }
                    if(isInSameCell(playerLoc, new Vector2(laserX, laserY))) {
                        break;
                    }
                    laserSprites.add(laserrr);
                    i++;
                }
            }


        }


        return laserSprites;
    }

    private boolean isInSameCell(Vector2 loc1, Vector2 loc2){
        if(Vector2.dst(loc1.x+75, loc1.y+75, loc2.x, loc2.y) < 80){
            return true;
        }
        return false;
    }
}
