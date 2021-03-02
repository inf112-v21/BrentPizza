package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


public class RoboRallyGUI extends ApplicationAdapter {
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;

    BoardLogic boardLogic;
    Player myPlayer;
    int gameOverIf100 = 0;


    @Override
    public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        tiledMap = new TmxMapLoader().load("src/main/Resources/emptyMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        sb = new SpriteBatch();



        try {
            boardLogic = new BoardLogic(tiledMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        while (boardLogic.getPlayers() == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        InputProcess inputProcess = new InputProcess(camera, boardLogic.getMyPlayer(), boardLogic);
        Gdx.input.setInputProcessor(inputProcess);


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
        for (Player player: boardLogic.getPlayers()) {
            player.getSprite().draw(sb);
        }
        //This is not the right way, but the MVP way
        if(boardLogic.getGameOver()){
            if(gameOverIf100 == 100){
                System.exit(1);
            }
            gameOverIf100 +=1;
        }
        sb.end();
    }

}