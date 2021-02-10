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

public class RoboRally extends ApplicationAdapter implements InputProcessor {
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;
    Texture texture1;
    Texture texture2;
    Player myPlayer;
    int gameOverIf100 = 0;
    boolean gameOver = false;

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
        texture1 = new Texture(Gdx.files.internal("robot2.png"));
        texture2 = new Texture(Gdx.files.internal("robot3.png"));

        myPlayer = new Player(1, new Sprite(texture1));
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
        //draw player
        myPlayer.getSprite().draw(sb);
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
        if(keycode == Input.Keys.LEFT)
            myPlayer.moveLeft();
        if(keycode == Input.Keys.RIGHT)
            myPlayer.moveRight();
        if(keycode == Input.Keys.UP)
            myPlayer.moveUp();
        if(keycode == Input.Keys.DOWN)
            myPlayer.moveDown();

        //Checks if player is inside map
        checkOutOfBounds();

        if(checkWin()){
            System.out.println("Player " + myPlayer.getID() + " won");
            gameOver = true;
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
        return false;
    }

    public boolean checkWin(){
        int index = tiledMap.getLayers().getIndex("flag");
        MapLayer winLayer = tiledMap.getLayers().get(index);
        Float flagX = Float.parseFloat(winLayer.getObjects().get("Flag1").getProperties().get("x").toString());
        Float flagY = Float.parseFloat(winLayer.getObjects().get("Flag1").getProperties().get("y").toString());
        Vector2 playerLoc = myPlayer.getLocation();
        if(playerLoc.x == flagX & playerLoc.y == flagY){
            return true;
        }
        return false;
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
        else if(playerLoc.x < 0 || playerLoc.y < 0){
            return false;
        }
        return true;
    }
}
