package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.GUI.Screens.ConnectScreen;
import inf112.skeleton.app.GUI.Screens.EndScreen;
import inf112.skeleton.app.GUI.Screens.GameScreen;
import inf112.skeleton.app.GUI.Screens.StartScreen;


public class RoboRallyGUI extends Game {

    GameScreen gameScreen;
    StartScreen startScreen;
    EndScreen endScreen;
    ConnectScreen connectScreen;
    boolean startScreenIsVisible = true;
    boolean gameScreenIsVisible = false;
    boolean endScreenIsVisible = false;
    boolean connectScreenIsVisible = false;
    private InputMultiplexer inputMultiplexer;

    @Override
    public void create() {
        startScreen = new StartScreen(this);

        endScreen = new EndScreen(this);

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(startScreen.getStage());

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(startScreenIsVisible){
            startScreen.getStage().draw();
        } else if(gameScreenIsVisible) {
            gameScreen.stageRefresh();
        } else if(endScreenIsVisible){
            endScreen.getStage().draw();
        } else if(connectScreenIsVisible){
            connectScreen.getStage().draw();
        }

    }
    public void setStartScreenVisible(){
        startScreenIsVisible = true;
        gameScreenIsVisible = false;
        endScreenIsVisible = false;
        connectScreenIsVisible = false;

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(startScreen.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
    public void setConnectScreenVisible(){
        connectScreen = new ConnectScreen(this);
        connectScreen.setTest();
        startScreenIsVisible = false;
        gameScreenIsVisible = false;
        endScreenIsVisible = false;
        connectScreenIsVisible = true;
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(connectScreen.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
    public void setGameScreenVisible(String ip){
        try {
            gameScreen = new GameScreen(this, ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startScreenIsVisible = false;
        gameScreenIsVisible = true;
        endScreenIsVisible = false;
        connectScreenIsVisible = false;

        inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(gameScreen.getIInputProcess());
        inputMultiplexer.addProcessor(gameScreen.getHudStage());
        Gdx.input.setInputProcessor(inputMultiplexer);

    }
    public void setEndScreenVisible(){
        startScreenIsVisible = false;
        gameScreenIsVisible = false;
        endScreenIsVisible = true;
        connectScreenIsVisible = false;
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(endScreen.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public ConnectScreen getConnectScreen(){
        return this.connectScreen;
    }
}

