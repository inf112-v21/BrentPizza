package inf112.skeleton.app.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.Cards.MoveOneCard;
import inf112.skeleton.app.Cards.NullCard;

import java.util.ArrayList;

public class ProgramTableGenerator {

    public Table createCardTable(ArrayList<Image> spriteList, Button readyButton){

        Table table = new Table();
        table.right();
        table.setFillParent(true);


        for (Image image: spriteList) {
            table.add(image).expandY().padBottom(10);
            table.row();
        }

        table.add(readyButton).expandY().padBottom(10);

        return table;

    }
}
