package inf112.skeleton.app.Cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameLogic.IPlayer;

public class NullCard extends Card {

    public NullCard(){
        textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("src/main/Resources/nullCard.png")));
    }

    public void action(IPlayer player) {
        System.out.println("Null card cannot move");
    }

}
