package inf112.skeleton.app.GUI.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import java.util.ArrayList;

public class HandTableGenerator {

    private Label.LabelStyle lStyle;
    private BitmapFont font;

    public Table createHandTable(ArrayList<Button> buttonList, ArrayList<Integer> priority){
        Table table = new Table();
        table.bottom();

        font = new BitmapFont();
        font.setColor(Color.BLACK);

        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("src/main/Resources/white.png")), 10, 10, 10, 10);
        NinePatchDrawable npd = new NinePatchDrawable(patch);
        lStyle = new Label.LabelStyle(font,font.getColor());
        lStyle.background = npd;

        table.setFillParent(true);

        for (int i = 0; i < buttonList.size(); i++) {
            table.add(buttonList.get(i)).expand(0, 0).pad(10);
            table.add(new Label(priority.get(i).toString(), new Label.LabelStyle(lStyle)));
        }
        return table;
    }

}
