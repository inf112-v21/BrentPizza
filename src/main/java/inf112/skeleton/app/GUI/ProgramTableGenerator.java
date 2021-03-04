package inf112.skeleton.app.GUI;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
