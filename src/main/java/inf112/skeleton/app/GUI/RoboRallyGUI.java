package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.GameLogic.*;


public class RoboRallyGUI extends Game {

    public TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;
    SpriteBatch sbHud;
    IBoardLogic boardLogic;
    int gameOverIf100 = 0;
    Hud hud;

    @Override
    public void create() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);

        //camera.update();
        tiledMap = new TmxMapLoader().load("src/main/Resources/emptyMap.tmx");

        camera.update();

        //tiledMap = new TmxMapLoader().load("src/main/Resources/emptyMap.tmx");
        tiledMap = new TmxMapLoader().load("src/main/Resources/roboRallyMap.tmx");


        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        sb = new SpriteBatch();
        sbHud = new SpriteBatch();

        try {
            boardLogic = new BoardLogic(tiledMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        while (boardLogic.getPlayers() == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        IInputProcess IInputProcess = new InputProcess(camera, boardLogic.getMyPlayer(), boardLogic);


        hud = new Hud(sbHud, boardLogic);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(IInputProcess);
        inputMultiplexer.addProcessor(hud.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);


    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        sb.setProjectionMatrix(camera.combined);


        sbHud.setProjectionMatrix(hud.getStage().getCamera().combined);

        sb.begin();
        //draw players
        for (IPlayer player : boardLogic.getPlayers()) {
            player.getSprite().draw(sb);
        }

        //This is not the right way, but the MVP way
        if (boardLogic.getGameOver()) {
            if (gameOverIf100 == 100) {
                System.exit(1);
            }
            gameOverIf100 += 1;
        }

        sb.end();
        hud.getStage().draw();
    }
}
