package inf112.skeleton.app.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Laser {
    HashMap<Vector2, String> walls;
    IBoardLogic boardLogic;
    Texture texture;

    public Laser(IBoardLogic boardLogic, Texture texture){
        this.texture = texture;
        walls = boardLogic.getWalls();
        this.boardLogic = boardLogic;
    }

    public ArrayList<Sprite> createLasers(TiledMap tiledMap){
        MapLayer lasers = tiledMap.getLayers().get(tiledMap.getLayers().getIndex("lazers"));

        ArrayList<Sprite> laserSprites = new ArrayList<>();
        for (MapObject laser: lasers.getObjects()) {
            if(laser.getProperties().get("direction").toString().equals("north"))
                laserSprites.addAll(createLaserVertical("wallNorth", "wallSouth", (float) 60, (float) 150, laser));
            else if(laser.getProperties().get("direction").toString().equals("south"))
                laserSprites.addAll(createLaserVertical("wallSouth", "wallNorth", (float) 60, (float) -150, laser));
            else if(laser.getProperties().get("direction").toString().equals("east"))
                laserSprites.addAll(createLaserHorizontal("wallEast", "wallWest", (float) -150, (float) 0, laser));

        }
        return laserSprites;
    }

    private ArrayList<Sprite> createLaserVertical(String wallToCheck, String wallToCheck1, Float laserFactorX, Float laserFactorY, MapObject laser){
        ArrayList<Sprite> laserSprites = new ArrayList<>();
        Integer i;
        if(laserFactorY == -150){
            i = 1;
        }else{
            i = 0;
        }

        while (true){
            Sprite laserrr = new Sprite(texture);
            Float laserX = Float.parseFloat( laser.getProperties().get("x").toString()) + laserFactorX;
            Float laserY = Float.parseFloat(laser.getProperties().get("y").toString()) + i*laserFactorY;
            laserrr.setX(laserX);
            laserrr.setY(laserY);

            if(walls.get(new Vector2(laserX - laserFactorX, laserY)) == (wallToCheck)){
                laserSprites.add(laserrr);
                return laserSprites;
            }
            else if(walls.get(new Vector2(laserX - laserFactorY, laserY)) == (wallToCheck1) || laserY > 1650 || laserY < -26){
                return laserSprites;
            }
            for (IPlayer pl: boardLogic.getPlayers()) {
                if(isInSameCell(pl.getLocation(), new Vector2(laserX, laserY))) {
                    System.out.println(pl.getDamageTokens());
                    pl.changeDamageTokens(1);
                    System.out.println(pl.getDamageTokens());
                    return laserSprites;
                }
            }

            laserSprites.add(laserrr);
            i++;

        }
    }

    private ArrayList<Sprite> createLaserHorizontal(String wallToCheck, String wallToCheck1, Float laserFactorX, Float laserFactorY, MapObject laser){
        ArrayList<Sprite> laserSprites = new ArrayList<>();
        Integer i;
        if(laserFactorY == -150){
            i = 1;
        }else{
            i = 0;
        }
        while (true){
            Sprite laserrr = new Sprite(texture);
            laserrr.rotate(90);
            Float laserX = Float.parseFloat(laser.getProperties().get("x").toString()) + i*laserFactorX;
            Float laserY = Float.parseFloat(laser.getProperties().get("y").toString()) + laserFactorY;
            laserrr.setX(laserX);
            laserrr.setY(laserY);

            System.out.println("stuck");
            if(walls.get(new Vector2(laserX - laserFactorX, laserY)) == (wallToCheck)){
                laserSprites.add(laserrr);
                return laserSprites;
            }
            else if(walls.get(new Vector2(laserX - laserFactorY, laserY)) == (wallToCheck1) || laserX > 4200 || laserX < 0){
                return laserSprites;
            }
            for (IPlayer pl: boardLogic.getPlayers()) {
                if(isInSameCell(pl.getLocation(), new Vector2(laserX, laserY))) {
                    System.out.println(pl.getDamageTokens());
                    pl.changeDamageTokens(1);
                    System.out.println(pl.getDamageTokens());
                    return laserSprites;
                }
            }
            laserSprites.add(laserrr);
            i++;
        }
    }
    private boolean isInSameCell(Vector2 loc1, Vector2 loc2){

        if(Vector2.dst(loc1.x, loc1.y, loc2.x, loc2.y) <= 77){
            return true;
        }
        return false;
    }
}
