package inf112.skeleton.app.GUI.HUD;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.lwjgl.system.CallbackI;


import java.util.ArrayList;

public class HandTableGenerator {


    public Table createHandTable(ArrayList<Button> buttonList, ArrayList<Integer> priority){
        Table table = new Table();
        table.bottom();

        //Perhaps fix later, or delete before we deliver assignement
        /**
        Pixmap bgPixmap = new Pixmap(1,1,Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.WHITE);
        bgPixmap.fill();
        //table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap))));
        table.setBounds(0, 0, 1920, 300);
         **/
        table.setFillParent(true);

        for (int i = 0; i < buttonList.size(); i++) {
            table.add(buttonList.get(i)).expand(0, 0).pad(10);
            table.add(new Label(priority.get(i).toString(), new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
        }
        return table;
    }




}
