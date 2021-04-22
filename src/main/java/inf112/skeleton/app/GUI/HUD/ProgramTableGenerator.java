package inf112.skeleton.app.GUI.HUD;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.ArrayList;

public class ProgramTableGenerator {

    public Table createCardTable(ArrayList<Image> spriteList, Button readyButton, Button healButtoon){

        Table table = new Table();
        table.right();
        table.setFillParent(true);


        for (Image image: spriteList) {
            table.add(image).expandY().padBottom(1);
            table.row();
        }

        table.add(readyButton).expandY().padBottom(1);
        table.row();
        table.add(healButtoon).expandY().padBottom(1);

        return table;

    }
}
