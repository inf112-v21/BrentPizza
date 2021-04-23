package inf112.skeleton.app.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.RoboRallyGUI;

import java.util.Scanner;

public class ConnectScreen {

    private Stage stage;
    private Viewport viewport;
    private SpriteBatch sb;
    private Label.LabelStyle lStyle;
    private BitmapFont font;
    String ip = "localhost";

    public ConnectScreen(RoboRallyGUI rgb, boolean isTest){
        sb = new SpriteBatch();
        viewport = new FitViewport(1920, 1080, new OrthographicCamera());

        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        font = new BitmapFont();
        font.getData().setScale(2, 2);
        font.setColor(Color.BLACK);

        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("src/main/Resources/white.png")), 10, 10, 10, 10);
        NinePatchDrawable npd = new NinePatchDrawable(patch);
        lStyle = new Label.LabelStyle(font,font.getColor());
        lStyle.background = npd;

        Label label = new Label("Please input server IP in console before pressing connect, type localhost to connect to localhost", new Label.LabelStyle(lStyle));

        Button connectButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/buttons/connect.png"))));
        table.add(label);
        table.add(connectButton).expand(0,0).pad(10);
        stage.addActor(table);


        connectButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                rgb.setGameScreenVisible(ip);
                if(!isTest){
                    setTest();
                }
            }
        });


    }

    public Stage getStage(){
        return this.stage;
    }


    public void setTest() {
        Scanner in = new Scanner(System.in);
        ip = in.nextLine();

    }
}
