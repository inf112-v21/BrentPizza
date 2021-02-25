package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import inf112.skeleton.app.Cards.*;

import java.util.ArrayList;
import java.util.Scanner;

public class RoboRally extends ApplicationAdapter implements InputProcessor {
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;

    Player myPlayer;
    int gameOverIf100 = 0;
    boolean gameOver = false;
    private NetworkClient client;
    ArrayList<Player> players;
    private Integer nrOfPlayers;

    @Override
    public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        tiledMap = new TmxMapLoader().load("emptyMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(this);
        sb = new SpriteBatch();

        try{
            client = new NetworkClient(this);
        }catch (Exception e){
            System.out.println(e);
        }

        while(nrOfPlayers == null){
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
        }

        players = new ArrayList<>();
        for (int i = 1; i <= nrOfPlayers; i++) {
            Player playerToAdd = new Player(i, new Sprite(new Texture(Gdx.files.internal("robot" + i + ".png"))));
            this.players.add(playerToAdd);
        }
        System.out.println(client.getId());
        myPlayer = players.get(client.getId()-1);


    }
    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        //draw players
        for (Player player: players) {
            player.getSprite().draw(sb);
        }
        //This is not the right way, but the MVP way
        if(gameOver){
            if(gameOverIf100 == 100){
                System.exit(1);
            }
            gameOverIf100 +=1;
        }
        sb.end();
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
    public boolean keyDown(int keycode) {
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
            myPlayer.moveForward();
        if (keycode == Input.Keys.SPACE)
            myPlayer.rotatePlayer(-90);

        //Checks if player is inside map
        checkOutOfBounds();

        client.sendCords(myPlayer.getLocation());

        // ends game if player exits map
        if(!checkOutOfBounds()){
            System.out.println("Player " + myPlayer.getID() + " fell and died");
            gameOver = true;
        }

        //ends game if player steps on flag

        if(checkWin()){
            client.sendWin();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        camera.zoom += amount * 0.2f;
        return false;
    }

    public boolean checkWin(){
        int index = tiledMap.getLayers().getIndex("flag");
        MapLayer winLayer = tiledMap.getLayers().get(index);
        float flagX = Float.parseFloat(winLayer.getObjects().get("Flag1").getProperties().get("x").toString());
        float flagY = Float.parseFloat(winLayer.getObjects().get("Flag1").getProperties().get("y").toString());
        Vector2 playerLoc = myPlayer.getLocation();
        return playerLoc.x == flagX & playerLoc.y == flagY;
    }
    /**
     * Checks if player is inside map.
     *
     * Returns true if player is inside map.
     * Returns false if player is outside map.
     */
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

    public void movePlayer(float x, float y, int id){
        players.get(id-1).getSprite().setX(x);
        players.get(id-1).getSprite().setY(y);
    }

    public void setNrOfPlayers(Integer nr){
        this.nrOfPlayers = nr;
    }
    public void gameOver(int id){
        System.out.println("Player with ID " + id + " has won");
        gameOver = true;
    }


}
