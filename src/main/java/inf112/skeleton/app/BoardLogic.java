package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import inf112.skeleton.app.Cards.*;

import java.util.ArrayList;

public class BoardLogic implements InputProcessor {

    ArrayList<Player> players;
    NetworkClient networkClient;
    Player myPlayer;
    Integer nrOfPlayers;
    RoboRally roboRally;
    TiledMap tiledMap;
    OrthographicCamera camera;


    public BoardLogic(TiledMap tiledMap, OrthographicCamera camera, NetworkClient networkClient) throws Exception {
        this.roboRally = new RoboRally();
        this.networkClient = networkClient;
        this.tiledMap = tiledMap;
        this.camera = camera;

    }

    public void Initialize() throws InterruptedException {
        if(nrOfPlayers == null){
            Thread.sleep(1000);
        }
        myPlayer = players.get(networkClient.getId()-1);

    }

    public boolean checkWin(){
        int index = tiledMap.getLayers().getIndex("flag");
        MapLayer winLayer = tiledMap.getLayers().get(index);
        float flagX = Float.parseFloat(winLayer.getObjects().get("Flag1").getProperties().get("x").toString());
        float flagY = Float.parseFloat(winLayer.getObjects().get("Flag1").getProperties().get("y").toString());
        Vector2 playerLoc = myPlayer.getLocation();
        return playerLoc.x == flagX & playerLoc.y == flagY;
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        /**
         if(keycode == Input.Keys.LEFT)
         myPlayer.moveLeft();
         if(keycode == Input.Keys.RIGHT)
         myPlayer.moveRight();
         if(keycode == Input.Keys.UP)
         myPlayer.moveUp();
         if(keycode == Input.Keys.DOWN)
         myPlayer.moveDown();
         */
        if(keycode == Input.Keys.NUM_1){
            MoveOneCard card = new MoveOneCard();
            card.action(myPlayer);
        }
        if(keycode == Input.Keys.NUM_2){
            MoveTwoCard card = new MoveTwoCard();
            card.action(myPlayer);
        }
        if(keycode == Input.Keys.NUM_3){
            MoveThreeCard card = new MoveThreeCard();
            card.action(myPlayer);
        }
        if(keycode == Input.Keys.NUM_4){
            TurnLeftCard card = new TurnLeftCard();
            card.action(myPlayer);
        }
        if(keycode == Input.Keys.NUM_5){
            TurnRightCard card = new TurnRightCard();
            card.action(myPlayer);
        }
        if(keycode == Input.Keys.NUM_6){
            ArrayList<Card> cards = new ArrayList<>();
            cards.add(new MoveOneCard());
            cards.add(new MoveTwoCard());
            cards.add(new MoveThreeCard());
            cards.add(new TurnLeftCard());


            for (Card card: cards) {
                card.action(myPlayer);
            }
        }

        if(keycode == Input.Keys.UP)

            if (keycode == Input.Keys.SPACE)
                myPlayer.rotatePlayer(-90);

        //Checks if player is inside map
        checkOutOfBounds();

        networkClient.sendPlayer(myPlayer);
        roboRally.updatePlayers(players);

        // ends game if player exits map
        if(!checkOutOfBounds()){
            System.out.println("Player " + myPlayer.getID() + " fell and died");
            roboRally.setGameOver();
        }

        //ends game if player steps on flag

        if(checkWin()){
            networkClient.sendWin();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float x = Gdx.input.getDeltaX();
        float y = Gdx.input.getDeltaY();
        camera.translate(-x,y);
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    public boolean scrolled(int amount) {
        camera.zoom += amount * 0.2f;
        return false;
    }

    public boolean checkOutOfBounds() {

        MapProperties prop = tiledMap.getProperties();

        // Get tiledMap width and height.
        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        // Get tile pixel width and height.
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);
        // Calculate map width and height
        int mapPixelWidth = (mapWidth * tilePixelWidth)-150;
        int mapPixelHeight = (mapHeight * tilePixelHeight)-150;

        Vector2 playerLoc = myPlayer.getLocation();

        if(playerLoc.x > mapPixelWidth || playerLoc.y > mapPixelHeight) {
            return false;
        }
        else return !(playerLoc.x < 0) && !(playerLoc.y < 0);
    }
    public void changePlayer(float x, float y, int id, float rotation){
        Player curPlayer = players.get(id-1);
        curPlayer.setX(x);
        curPlayer.setY(y);
        curPlayer.setRoation(rotation);
        players.set(id-1, curPlayer);
        roboRally.updatePlayers(players);
    }

    public void setNrOfPlayers(Integer nrOfPlayers) {
        this.nrOfPlayers = nrOfPlayers;
    }

    public void gameOver(int id){
        System.out.println("Player with id " + id + " has won!");
        roboRally.setGameOver();
    }

    public Integer getNrOfPlayers(){
        return this.nrOfPlayers;
    }

    public void setPlayers(ArrayList<Player> players){
        this.players = players;
    }
}
