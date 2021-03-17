package inf112.skeleton.app.GUI.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.RoboRallyGUI;

public class StartScreen {
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch sb;

    public StartScreen(RoboRallyGUI rgb){

        sb = new SpriteBatch();
        viewport = new FitViewport(1920, 1080, new OrthographicCamera());

        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Button startButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/ready.png"))));
        table.add(startButton).expand(0,0).pad(10);
        stage.addActor(table);

        startButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                rgb.setGameScreenVisible();
            }
        });
    }

    public Stage getStage() {
        return stage;
    }
}
