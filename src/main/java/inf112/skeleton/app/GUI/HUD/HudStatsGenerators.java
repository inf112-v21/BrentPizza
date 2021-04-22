package inf112.skeleton.app.GUI.HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import inf112.skeleton.app.GameLogic.IBoardLogic;
import inf112.skeleton.app.GameLogic.IPlayer;
import inf112.skeleton.app.GameLogic.Player;

public class HudStatsGenerators {


    public IPlayer myPlayer;

    private Label.LabelStyle lStyle;
    private BitmapFont font;

    public Table HudStatsGenerators(IBoardLogic boardLogic){

        myPlayer = boardLogic.getMyPlayer();

        Table table =  new Table();
        table.top();
        table.setFillParent(true);

        font = new BitmapFont();
        font.setColor(Color.BLACK);

        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("src/main/Resources/white.png")), 10, 10, 10, 10);
        NinePatchDrawable npd = new NinePatchDrawable(patch);
        lStyle = new Label.LabelStyle(font,font.getColor());
        lStyle.background = npd;

        Label labelDamageTokens = new Label("DamageTokens: " + myPlayer.getDamageTokens().toString(), new Label.LabelStyle(lStyle));
        Label labelLifeTokens = new Label(" LifeTokens: " + myPlayer.getLifeTokens().toString(), new Label.LabelStyle(lStyle));

        table.add(labelDamageTokens,labelLifeTokens);

        return table;
    }
}
