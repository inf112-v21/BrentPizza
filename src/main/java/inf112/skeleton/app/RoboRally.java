package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
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

public class RoboRally extends ApplicationAdapter {
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;

    Player myPlayer;
    int gameOverIf100 = 0;
    boolean gameOver = false;
    private ArrayList<Player> players;
    BoardLogic boardLogic;

    @Override
    public void create () {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        tiledMap = new TmxMapLoader().load("src/main/Resources/emptyMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);


        try {
            boardLogic = new BoardLogic(tiledMap, camera);
            Gdx.input.setInputProcessor(boardLogic);
            boardLogic.Initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }


        while(boardLogic.getNrOfPlayers() == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        players = new ArrayList<>();
        for (int i = 1; i <= boardLogic.getNrOfPlayers(); i++) {
            Player playerToAdd = new Player(i, new Sprite(new Texture(Gdx.files.internal("src/main/Resources/robot" + i + ".png"))));
            this.players.add(playerToAdd);
        }
        boardLogic.setPlayers(players);

        sb = new SpriteBatch();



        while(players == null){
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
        }


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



    /**
     * Checks if player is inside map.
     *
     * Returns true if player is inside map.
     * Returns false if player is outside map.
     */

    public void setGameOver(){
        gameOver = true;
    }

    public TiledMap getTiledMap(){
        return this.tiledMap;
    }
    public OrthographicCamera getCamera(){
        return this.camera;
    }
    public void updatePlayers(ArrayList<Player> players){
        this.players = players;
    }

}
