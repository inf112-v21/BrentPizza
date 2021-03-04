package inf112.skeleton.app.GUI;

import com.badlogic.gdx.scenes.scene2d.ui.Button;

import com.badlogic.gdx.scenes.scene2d.ui.Table;


import java.util.ArrayList;

public class HandTableGenerator {


    public Table createHandTable(ArrayList<Button> buttonList){
        Table table = new Table();
        table.bottom();
        table.setFillParent(true);

        for (Button button: buttonList) {
            table.add(button).expand(0, 0).pad(10);
        }
        return table;
    }




}
